package com.heroes.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Method;

import static sun.awt.util.ThreadGroupUtils.getRootThreadGroup;

public class Sountracks {

    private static MediaPlayer mainTheme;
    private static MediaPlayer battleTheme;

    private File mainThemeFile = new File("resources/soundtracks/HOMM3_main_theme.mp3");
    private File battleThemeFile = new File("resources/soundtracks/HOMM3_battle_theme.mp3");

//    private Themes actualTheme;

    public Sountracks() {
        this.loadSounds();
//        this.actualTheme = theme;
    }

    public static MediaPlayer getMainTheme() {
        return mainTheme;
    }

    public static MediaPlayer getBattleTheme() {
        return battleTheme;
    }

    private void loadSounds() {
        Media mainTheme = new Media("file:///" + mainThemeFile.getAbsolutePath());
        this.mainTheme = new MediaPlayer(mainTheme);

        Media battleTheme = new Media("file:///" + battleThemeFile.getAbsolutePath());
        this.battleTheme = new MediaPlayer(battleTheme);
        }


    public static void chooseSoundtrack(Themes theme){
        if (theme == Themes.MAIN){
            playMainTheme();
        } else if (theme == Themes.BATTLE){
            playBattleTheme();
        }
    }

    public static void chooseSoundtrackToStop(Themes theme){
        if (theme == Themes.MAIN){
            stopMainTheme();
        } else if (theme == Themes.BATTLE){
            stopBattleTheme();
        }
    }


    private static void stopMainTheme(){
        getMainTheme().stop();
    }

    private static void stopBattleTheme(){
        getBattleTheme().stop();
    }

    private static void playMainTheme(){
        getMainTheme().play();
    }

    private static void playBattleTheme(){
        getBattleTheme().play();
    }

//    public static void playTheme (Sountracks.Themes theme){
//        switch(theme){
//            case MAIN:
//                getMainTheme().play();
//            case BATTLE:
//                getBattleTheme().play();
//        }

//        new Thread(() -> {
//            try {
//                if (theme == Themes.MAIN){
//                    getMainTheme().play();
//                } else if (theme == Themes.BATTLE){
//                    getBattleTheme().play();
//                }
//            } catch (Exception e) {
//                System.err.println(e);
//            }
//        }).start();


//    }

//    @Override
//    public void run() {
//        chooseSoundtrack();
//    }

    public enum Themes {
        MAIN, BATTLE
    }



//
//    public static Thread[] getAllThreads( ) {
//        final ThreadGroup root = getRootThreadGroup( );
//        final ThreadMXBean thbean = ManagementFactory.getThreadMXBean( );
//        int nAlloc = thbean.getThreadCount( );
//        int n = 0;
//        Thread[] threads;
//        do {
//            nAlloc *= 2;
//            threads = new Thread[ nAlloc ];
//            n = root.enumerate( threads, true );
//        } while ( n == nAlloc );
//        return java.util.Arrays.copyOf( threads, n );
//    }
//
//
//    public static Thread getThread( final String name ) {
//        if ( name == null )
//            throw new NullPointerException( "Null name" );
//        final Thread[] threads = getAllThreads( );
//        for ( Thread thread : threads )
//            if ( thread.getName( ).equals( name ) )
//                return thread;
//        return null;
//    }

    }



