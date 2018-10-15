package com.heroes.controller;
import com.heroes.model.Game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.*;


public class Heroes extends Application {
//
//    private static final double WINDOW_WIDTH = 1400;
//    private static final double WINDOW_HEIGHT = 900;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        Game game = new Game();
//        game.setTableBackground(new Image("map/Map1.png"));
//        primaryStage.setTitle("HEROES OF MIGHT & MAGIC");
//        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
//        primaryStage.
//        primaryStage.setFullScreen(true);
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(final Stage stage) {
        // uncomment if you want the stage full screen.
        //stage.setFullScreen(true);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        StackPane root = new StackPane();
        root.setStyle(
                "-fx-background-image: url(" +
                        "'map/Map1.png'" +
                        "); " +
                        "-fx-background-size: cover;"
        );
        stage.setScene(new Scene(root));
        stage.show();
    }
}
