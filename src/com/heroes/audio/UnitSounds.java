package com.heroes.audio;

import com.heroes.model.Unit;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;

public class UnitSounds {
    private MediaPlayer attackSound;
    private MediaPlayer moveSound;
    private MediaPlayer hitkSound;
    private MediaPlayer deathSound;
    private MediaPlayer shootSound;

    private String endOfAttackPath = new String("attk.mp3");
    private String endOfDeathPath = new String("kill.mp3");
    private String endOfMovePath = new String("move.mp3");
    private String endOfDefendPath = new String("dfnd.mp3");
    private String endOfShootPath = new String("shoot.mp3");
    private String endOfHitPath = new String("hitt.mp3");

    private Unit unit;

    public UnitSounds(Unit unit) {
        this.unit = unit;
        this.loadSounds(unit);
    }

    public MediaPlayer getMoveSound() {
        return moveSound;
    }

    private void loadSounds(Unit unit) {
        String pathToUnitAudioFiles = "resources/units/" + unit.getTown().toLowerCase() + "/" + unit.getName().toLowerCase() + "/audio";
//        String pathToUnitAudioFiles = "resources/units/castle/angel/audio";
        File file = new File(pathToUnitAudioFiles);
        File[] files = file.listFiles();
        if (files != null) {
            for (File child : files) {
//                System.out.println(child.getPath().substring(child.getPath().length() - 8));
//                System.out.println(child.getPath().substring(child.getPath().length() - 8).toLowerCase());

                if (child.getPath().substring(child.getPath().length() - 8).equals(endOfMovePath.toLowerCase()) ||
                        child.getPath().substring(child.getPath().length() - 8).equals(endOfMovePath.toUpperCase())) {
                    System.out.println(child.getPath().substring(10));
                    Media sound = new Media("file:///" + child.getAbsolutePath());
                    this.moveSound = new MediaPlayer(sound);
                }
            }
        }
    }

    public static void playSound (Unit unit, UnitSound chosenSound){
        switch(chosenSound){
            case MOVE:
                unit.getUnitSound().moveSound.play();
        }
    }

    public enum UnitSound {
        ATTACK, HIT, MOVE, DEATH,
        SHOOT, DEFEND
    }
}


