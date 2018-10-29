package com.heroes.view;

import com.heroes.model.Game;
import com.heroes.model.Player;
import com.heroes.model.Unit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.FXCollections;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.io.File;

import javafx.util.Duration;

import java.util.Collection;
import java.util.List;


public class UnitView extends ImageView {

    private ImageView defaultPhoto;
    private Image standPhoto;
    private Image deathphoto;
    private List<Image> moveAnimation = FXCollections.observableArrayList();
    private List<Image> hitAnimation = FXCollections.observableArrayList();
    private List<Image> deathAnimation = FXCollections.observableArrayList();
    private List<Image> attackAnimation = FXCollections.observableArrayList();
    private List<Image> shootAnimation = FXCollections.observableArrayList();

    private Unit unit;


    public UnitView(Unit unit) {
        this.unit = unit;
        this.loadAnimations(unit);
        this.defaultPhoto = new ImageView(standPhoto);
    }


    public ImageView getDefaultPhoto() {
        return defaultPhoto;
    }

    public Image getStandPhoto() {
        return standPhoto;
    }

    public Image getDeathphoto() {
        return deathphoto;
    }

    public List<Image> getMoveAnimation() {
        return moveAnimation;
    }

    public List<Image> getHitAnimation() {
        return hitAnimation;
    }

    public List<Image> getDeathAnimation() {
        return deathAnimation;
    }

    public List<Image> getAttackAnimation() {
        return attackAnimation;
    }

    public List<Image> getShootAnimation() {
        return shootAnimation;
    }

    public Unit getUnit() {
        return unit;
    }


    private void loadAnimations(Unit unit) {
        String pathToUnitVisualFiles = "resources/units/" + unit.getTown().toLowerCase() + "/" + unit.getName().toLowerCase() + "/visual";
        File file = new File(pathToUnitVisualFiles);
        File[] files = file.listFiles();
        if (files != null) {
            for (File child : files) {
                File innerfile = new File(child.getPath());
                File[] innerfiles = innerfile.listFiles();
                for (File innerchild : innerfiles) {
                    Image img = new Image(innerchild.getPath().substring(10));
                    if (innerchild.getParent().substring(innerchild.getParent().length() - 6).equals("attack")) {
                        attackAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 5).equals("death")) {
                        deathAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 3).equals("hit")) {
                        hitAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 4).equals("move")) {
                        moveAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 5).equals("shoot")) {
                        shootAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 12).equals("defaultphoto")) {
                        standPhoto = img;
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 10).equals("deathphoto")) {
                        deathphoto = img;
                    }


                }
            }
        }
    }

    public static void attachPhoto(Player player, BackgroundView gameBackground) {
        for (Unit unit : player.getUnitList()) {
            gameBackground.getChildren().add(unit.getUnitView().getDefaultPhoto());
        }
    }

    public static void refineStartingCoords(Player player, Game game) {
        for (Unit unit : player.getUnitList()) {

            double squarePositionX = unit.getPosition().getLayoutX();
            double squarePositionY = unit.getPosition().getLayoutY();
            unit.getUnitView().getDefaultPhoto().relocate(squarePositionX - 170, squarePositionY - 220);
            if (unit.getTown().equals("Inferno")) {
                unit.getUnitView().getDefaultPhoto().setTranslateX(-60);
                unit.getUnitView().getDefaultPhoto().setRotationAxis(Rotate.Y_AXIS);
                unit.getUnitView().getDefaultPhoto().setRotate(180);
            }
        }
    }
}





