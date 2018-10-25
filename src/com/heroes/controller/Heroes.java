package com.heroes.controller;
import com.heroes.model.Game;
import com.heroes.view.BackgroundView;
import com.heroes.view.UnitView;

import com.heroes.view.UnitView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

import java.io.IOException;


public class Heroes extends Application {

    private static final double WINDOW_WIDTH = 1280;
    private static final double WINDOW_HEIGHT = 720;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        BackgroundView gameBackground = new BackgroundView(primaryStage);
        gameBackground.changeBackground(gameBackground);

        Game game = new Game(gameBackground);

        primaryStage.setTitle("Heroes");
        primaryStage.setScene(new Scene(gameBackground, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }


}
