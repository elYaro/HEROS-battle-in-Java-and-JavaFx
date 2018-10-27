package com.heroes.model;

import com.heroes.controller.Heroes;
import com.heroes.view.BackgroundView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class StartingMenu {
    private static final String STANDARD_BUTTON_STYLE = "-fx-effect: dropshadow(gaussian, #4682b4 , 10, 0.5, 1, 1 );";
    private static final String HOVERED_BUTTON_STYLE = "-fx-opacity: 0.5;";
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

        final ImageView quitButton = new ImageView(new Image(pathToQuit));

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

}
