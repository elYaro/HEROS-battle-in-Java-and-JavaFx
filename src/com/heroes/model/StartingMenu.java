package com.heroes.model;

import com.heroes.controller.Heroes;
import com.heroes.view.BackgroundView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class StartingMenu {
    private static final String STANDARD_BUTTON_STYLE = "-fx-effect: dropshadow(gaussian, #4682b4 , 10, 0.5, 1, 1 );";
    private static final String HOVERED_BUTTON_STYLE = "-fx-opacity: 0.8;";
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private boolean iWantMenu = true;
    private BackgroundView startingMenu;
    private String pathToNewGame = new String("menu/buttons/heroes_new_game.png");
    private String pathToCredits = new String("menu/buttons/heroes_credits.png");
    private String pathToQuit = new String("menu/buttons/heroes_quit.png");

    /**
     * Constructor for StartingMenu.
     * It adds a property listener that calls up a Heroes.startGame method that loads next state of app.
     * This method also calls up a createButtons method that creates a vBox with images that serves as a buttons.
     * */
    public StartingMenu(BackgroundView startingMenu) {
        pcs.addPropertyChangeListener(this::propertyChange);
        this.startingMenu = startingMenu;
        createButtons();
    }


    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Heroes.startGame(Heroes.getGameBackground());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean getiWantMenu() {
        return iWantMenu;
    }


    public void setiWantMenu(Boolean iDontWantMenu) {
        Boolean iWantMenu = this.iWantMenu;
        this.iWantMenu = iDontWantMenu;
        this.pcs.firePropertyChange("value", iWantMenu, iDontWantMenu);
    }

    /**
     * This is a method that is called up when a startGame button is pressed. First it removes
     * everything that was created in createButtons then it changes the value of iWantMenu to false which
     * triggers propertyListener.
     * */
    private void exitStartingMenu() {
        removeButtons();
        setiWantMenu(false);
    }

    private void removeButtons() {
        this.startingMenu.getChildren().clear();
    }


    private void createButtons() {
        final ImageView startGameButton = new ImageView(new Image(pathToNewGame));
        startGameButton.setOnMouseClicked(event -> exitStartingMenu());

        final ImageView creditsButton = new ImageView(new Image(pathToCredits));
        creditsButton.setOnMouseClicked(event -> createCredits());

        final ImageView quitButton = new ImageView(new Image(pathToQuit));
        quitButton.setOnMouseClicked(event -> exitApp());

        ImageView[] images = {startGameButton, creditsButton, quitButton};

        VBox vbox = new VBox(65);
        vbox.setPadding(new Insets(50, 50, 50, 50));
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.setLayoutX(900);
        this.startingMenu.getChildren().add(vbox);

        for (ImageView img : images) {
            img.styleProperty().bind(
                    Bindings
                            .when(img.hoverProperty())
                            .then(
                                    new SimpleStringProperty(HOVERED_BUTTON_STYLE)
                            )
                            .otherwise(
                                    new SimpleStringProperty(STANDARD_BUTTON_STYLE)
                            )
            );
            vbox.getChildren().add(img);
        }
    }


    private void createCredits(){
        ImageView creditsFrame = new ImageView(new Image("menu/buttons/heroes_small_menu_plain.png"));
        creditsFrame.setLayoutX(450);
        creditsFrame.setLayoutY(250);
        creditsFrame.setFitWidth(500);
        creditsFrame.setPreserveRatio(true);
        this.startingMenu.getChildren().add(creditsFrame);

        Label credits = new Label("" +
                "Heroes of Might and Magic III is a turn-based strategy game developed by Jon Van Caneghem " +
                "through New World Computing originally released for Microsoft Windows by the 3DO Company in 1999." +
                "\n\n" +
                "However this game was developed by HEROESEX team in order to pay tribute to the original HOMM game" +
                " and learn basics of Java language. We aimed to recreate the battle between Castle and Inferno." +
                " We hope you like the result." +
                "\n\n" +
                "Enjoy!");

        credits.setStyle("-fx-text-fill: #ffd700; -fx-font-size: 14px; -fx-font-weight: 800;");
        credits.setWrapText(true);
        credits.setPrefWidth(creditsFrame.getLayoutX() - 10);
        credits.setTextAlignment(TextAlignment.JUSTIFY);
        credits.setLayoutX(creditsFrame.getLayoutX() + 30);
        credits.setLayoutY(creditsFrame.getLayoutY() + 30);
        this.startingMenu.getChildren().add(credits);

        ImageView creditsQuitButton = new ImageView(new Image("menu/buttons/heroes_refuse.png"));
        creditsQuitButton.setLayoutX(creditsFrame.getLayoutX() + 390);
        creditsQuitButton.setLayoutY(creditsFrame.getLayoutY() + 230);
        creditsQuitButton.setFitWidth(80);
        creditsQuitButton.setPreserveRatio(true);
        creditsQuitButton.setOnMouseClicked(event -> startingMenu.getChildren().removeAll(creditsFrame, credits, creditsQuitButton));
        this.startingMenu.getChildren().add(creditsQuitButton);
    }


        public static void exitApp(){
        System.exit(0);
    }

}
