package com.heroes.controller;
import com.heroes.model.Game;
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
        Game game = new Game();
        game.setTableBackground(new Image("map/Map1.png"));
        primaryStage.setTitle("Heroes");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        game.setStyle(
                "-fx-background-image: url(" +
                        "'map/Map1.png'" +
                        "); " +
                        "-fx-background-size: 100% 100% ;" +
                        "-fx-background-position: center center;"+
                        "-fx-background-repeat: stretch;"
        );
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }


}
