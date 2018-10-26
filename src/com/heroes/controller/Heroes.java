package com.heroes.controller;
import com.heroes.model.Game;
import com.heroes.model.StartingMenu;
import com.heroes.model.StartingMenuObserver;
import com.heroes.view.BackgroundView;
import com.heroes.view.UnitView;

import com.heroes.view.UnitView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

import java.io.IOException;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;


public class Heroes extends Application{

    private static final double WINDOW_WIDTH = 1280;
    private static final double WINDOW_HEIGHT = 720;
    private StartingMenu startingMenuUpdate;
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

        StartingMenu start = new StartingMenu(gameBackground);

        StartingMenuObserver startObserver = new StartingMenuObserver();

        start.addObserver(startObserver);

        primaryStage.setTitle("Heroes");
        primaryStage.setScene(new Scene(gameBackground, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }


    public static void startGame(BackgroundView gameBackground) throws IOException {
        System.out.println("yes");
        Game game = new Game(gameBackground);
        gameBackground.changeBackground(gameBackground, gameBackground.getPathToFieldMap());
    }


}
