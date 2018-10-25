//package com.heroes.view;
//
//import javafx.scene.image.Image;
//
//
//import javafx.scene.layout.BackgroundImage;
//import javafx.scene.layout.BackgroundPosition;
//import javafx.scene.layout.BackgroundRepeat;
//import javafx.scene.layout.BackgroundSize;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//
//
//public class GameBackground extends Pane {
//
//    private static String pathToFieldMap = new String("map/Map1.png");
//    private static Stage window;
//    private static final double WINDOW_WIDTH = 1280;
//    private static final double WINDOW_HEIGHT = 760;
//
//    public String getPathToFieldMap() {
//        return pathToFieldMap;
//    }
//
//    public GameBackground(Stage primaryStage) {
//        this.window = primaryStage;
//    }
//
//
//    public static void changeBackground(GameBackground background){
//        background.setTableBackground(new Image(pathToFieldMap));
//    }
//
//
//
//    public void setTableBackground(Image tableBackground) {
//        setBackground(new javafx.scene.layout.Background(new BackgroundImage(tableBackground,
//                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
//                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
//    }
//
////    private void addStyle(GameBackground background){
////        background.setStyle("-fx-background-image: url(" +
////                "'map/Map1.png'" +
////                "); " +
////                "-fx-background-size: 100% 100% ;" +
////                "-fx-background-position: center center;"+
////                "-fx-background-repeat: stretch;");
////    }
//
//}
