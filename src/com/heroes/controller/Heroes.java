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
    private static final double WINDOW_WIDTH = 1000;
    private static final double WINDOW_HEIGHT = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        game.setTableBackground(new Image("map/Map1.png"));
        primaryStage.setTitle("Heroes");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
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
        System.out.println(screen.getVisualBounds());


    }


}
