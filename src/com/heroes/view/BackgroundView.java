package com.heroes.view;

import javafx.scene.Node;
import javafx.scene.image.Image;


import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class BackgroundView extends Pane {

    private static String pathToFieldMap = new String("map/Map1.png");
    private static String pathToStartingMenuBackground = new String("menu/background/heroes_menu_plain.png");
    private Stage primaryStage;
    private static final double WINDOW_WIDTH = 1280;
    private static final double WINDOW_HEIGHT = 720;

    public BackgroundView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public String getPathToFieldMap() {
        return pathToFieldMap;
    }

    public String getPathToStartingMenuBackground() {
        return pathToStartingMenuBackground;
    }

    public static void changeBackground(BackgroundView background, String path){
        background.setTableBackground(new Image(path));
    }


    public void setTableBackground(Image tableBackground) {
        setBackground(new javafx.scene.layout.Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(WINDOW_WIDTH, WINDOW_HEIGHT, true, true, true, true))));
    }

}
