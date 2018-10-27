package com.heroes.controller;
import com.heroes.model.Game;
import com.heroes.model.StartingMenu;
import com.heroes.view.BackgroundView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Heroes extends Application{

    private static final double WINDOW_WIDTH = 1280;
    private static final double WINDOW_HEIGHT = 720;
    private static BackgroundView gameBackground;

    public static void main(String[] args) {
        launch(args);
    }

    public static BackgroundView getGameBackground() {
        return gameBackground;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        BackgroundView gameBackground = new BackgroundView(primaryStage);
        this.gameBackground = gameBackground;

        gameBackground.changeBackground(gameBackground, gameBackground.getPathToStartingMenuBackground());

        StartingMenu startingMenu = new StartingMenu(gameBackground);

        primaryStage.setTitle("Heroes");
        primaryStage.setScene(new Scene(gameBackground, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void startGame(BackgroundView gameBackground) throws IOException {
        Game game = new Game(gameBackground);
        gameBackground.changeBackground(gameBackground, gameBackground.getPathToFieldMap());
    }


}
