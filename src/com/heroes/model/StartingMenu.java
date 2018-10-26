package com.heroes.model;

import com.heroes.view.BackgroundView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Observable;


public class StartingMenu extends Observable {
    private Boolean doWeWantToSeeThisScreen = new Boolean(true);
    private BackgroundView startingMenu;
    private String pathToNewGame = new String("menu/buttons/heroes_new_game.png");
    private String pathToCredits = new String("menu/buttons/heroes_credits.png");
    private String pathToQuit = new String("menu/buttons/heroes_quit.png");
    private static final String STANDARD_BUTTON_STYLE =   "-fx-effect: dropshadow(gaussian, #4682b4 , 10, 0.5, 1, 1 );";
    private static final String HOVERED_BUTTON_STYLE  = "-fx-opacity: 0.5;";

    public StartingMenu(BackgroundView startingMenu) {
        this.startingMenu = startingMenu;
        createButtons();
    }

    public Boolean getDoWeWantToSeeThisScreen() {
        return doWeWantToSeeThisScreen;
    }

    public void setDoWeWantToSeeThisScreen(Boolean doWeWantToSeeThisScreen) {
        this.doWeWantToSeeThisScreen = doWeWantToSeeThisScreen;
        setChanged();
        notifyObservers(this.doWeWantToSeeThisScreen);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }
    }

    private void exitStartingMenu(){
        removeButtons();
        setDoWeWantToSeeThisScreen(false);
    }

    private void createButtons(){
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

        for(ImageView img : images) {
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

    private void removeButtons(){
        this.startingMenu.getChildren().clear();
    }
}
