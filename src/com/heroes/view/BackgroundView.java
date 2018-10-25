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
    private static Stage primaryStage;


    public BackgroundView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public String getPathToFieldMap() {
        return pathToFieldMap;
    }


    public static void changeBackground(BackgroundView background){
        background.setTableBackground(new Image(pathToFieldMap));
        background.addStyle(background);
    }



    public void setTableBackground(Image tableBackground) {
        setBackground(new javafx.scene.layout.Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    private void addStyle(BackgroundView background){
        background.setStyle("-fx-background-image: url(" +
                "'map/Map1.png'" +
                "); " +
                "-fx-background-size: 100% 100% ;" +
                "-fx-background-position: center center;"+
                "-fx-background-repeat: stretch;");
    }

}
