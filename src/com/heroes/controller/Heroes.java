package com.heroes.controller;
import com.heroes.audio.Sountracks;
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
    private static Thread activeThread;

    public static void main(String[] args) {
        launch(args);
    }

    public static Thread getActiveThread() {
        return activeThread;
    }

    public static BackgroundView getGameBackground() {
        return gameBackground;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        BackgroundView gameBackground = new BackgroundView(primaryStage);
        this.gameBackground = gameBackground;

        gameBackground.changeBackground(gameBackground, gameBackground.getPathToStartingMenuBackground());

        Sountracks sountrack = new Sountracks();
//        sountrack.chooseSoundtrack(Sountracks.Themes.MAIN);

        Sountracks.chooseSoundtrack(Sountracks.Themes.MAIN);

//        Thread soundtrack = new Thread(new Sountracks(Sountracks.Themes.MAIN), "soundtrack");
//        this.activeThread = soundtrack;
//        soundtrack.start();

        StartingMenu startingMenu = new StartingMenu(gameBackground);

        primaryStage.setTitle("Heroes");
        primaryStage.setScene(new Scene(gameBackground, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void startGame(BackgroundView gameBackground ) throws IOException {
//        oldThread.destroy();
        Sountracks.chooseSoundtrackToStop(Sountracks.Themes.MAIN);
        Sountracks.chooseSoundtrack(Sountracks.Themes.BATTLE);
        Game game = new Game(gameBackground);
        gameBackground.changeBackground(gameBackground, gameBackground.getPathToFieldMap());
    }



}
