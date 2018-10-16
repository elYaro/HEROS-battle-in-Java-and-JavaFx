package com.heroes.view;

import com.heroes.model.Square;

import com.heroes.model.Unit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.FXCollections;

import java.io.File;
import java.util.List;


public class UnitView extends ImageView {

    public Image getDefaultPhoto() {
        return defaultPhoto;
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

    private Image defaultPhoto;
    private Image deathphoto;
    private List<Image> moveAnimation = FXCollections.observableArrayList();
    private List<Image> hitAnimation = FXCollections.observableArrayList();
    private List<Image> deathAnimation = FXCollections.observableArrayList();
    private List<Image> attackAnimation = FXCollections.observableArrayList();
    private List<Image> shootAnimation = FXCollections.observableArrayList();

    private Unit unit;

    public UnitView(Unit unit){
        this.unit = unit;
    }



    public void loadAnimations() {
        String pathToUnitVisualFiles = "resources/units/" + unit.getTown() + unit.getName() + "/visual";
        File file = new File(pathToUnitVisualFiles);
        File[] files = file.listFiles();
        if (files != null) {
            for (File child : files) {
                File innerfile = new File(child.getPath());
                File[] innerfiles = innerfile.listFiles();
                for (File innerchild : innerfiles) {
                    System.out.println(innerchild.getParent());
                    Image img = new Image(innerchild.getPath().substring(10));
                    switch (innerchild.getParent()) {
                        case "resources/units/inferno/imp/visual/attack":
                            attackAnimation.add(img);
                            break;
                        case "resources/units/inferno/imp/visual/death":
                            deathAnimation.add(img);
                            break;
                        case "resources/units/inferno/imp/visual/hit":
                            hitAnimation.add(img);
                            break;
                        case "resources/units/inferno/imp/visual/move":
                            moveAnimation.add(img);
                            break;
                        case "resources/units/inferno/imp/visual/shoot":
                            shootAnimation.add(img);
                            break;
                        case "resources/units/inferno/imp/visual/defaultphoto":
                            defaultPhoto = img;
                            break;
                        case "resources/units/inferno/imp/visual/deathphoto":
                            deathphoto = img;
                            break;

                    }
                }
            }
        }
    }
}

