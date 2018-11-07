package com.heroes.audio;

import com.heroes.model.Unit;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;

public class UnitSounds {
    private MediaPlayer attackSound;
    private AudioClip moveSound;
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

    public AudioClip getMoveSound() {
        return moveSound;
    }

    private void loadSounds(Unit unit) {
        String pathToUnitAudioFiles = "resources/units/" + unit.getTown().toLowerCase() + "/" + unit.getName().toLowerCase() + "/audio";
        File file = new File(pathToUnitAudioFiles);
        File[] files = file.listFiles();
        if (files != null) {
            for (File child : files) {
                if (child.getPath().substring(child.getPath().length() - 8).equals(endOfMovePath.toLowerCase())) {
                    AudioClip moveSound = new AudioClip("file:///" + child.getAbsolutePath());
                    this.moveSound = moveSound;
                }
            }
        }
    }

    public static void playSound (Unit unit, UnitSound chosenSound){
        switch(chosenSound){
            case MOVE:
//                unit.getUnitSound().moveSound.stop();
                unit.getUnitSound().moveSound.play();
        }
    }

    public enum UnitSound {
        ATTACK, HIT, MOVE, DEATH,
        SHOOT, DEFEND
    }
}


