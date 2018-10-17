package com.heroes.view;

import com.heroes.model.Unit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.FXCollections;

import java.io.File;
import java.util.List;


public class UnitView extends ImageView {


    private ImageView defaultPhoto;
    private ImageView deathphoto;
    private List<ImageView> moveAnimation = FXCollections.observableArrayList();
    private List<ImageView> hitAnimation = FXCollections.observableArrayList();
    private List<ImageView> deathAnimation = FXCollections.observableArrayList();
    private List<ImageView> attackAnimation = FXCollections.observableArrayList();
    private List<ImageView> shootAnimation = FXCollections.observableArrayList();

    private Unit unit;

    public UnitView(Unit unit){
        this.unit = unit;
        this.loadAnimations(unit);
    }


    public ImageView getDefaultPhoto() {
        return defaultPhoto;
    }

    public ImageView getDeathphoto() {
        return deathphoto;
    }

    public List<ImageView> getMoveAnimation() {
        return moveAnimation;
    }

    public List<ImageView> getHitAnimation() {
        return hitAnimation;
    }

    public List<ImageView> getDeathAnimation() {
        return deathAnimation;
    }

    public List<ImageView> getAttackAnimation() {
        return attackAnimation;
    }

    public List<ImageView> getShootAnimation() {
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
                    ImageView img = new ImageView( new Image (innerchild.getPath().substring(10)) );
                    if (innerchild.getParent().substring(innerchild.getParent().length() - 6).equals("attack")){
                        attackAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 5).equals("death")){
                        deathAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 3).equals("hit")){
                        hitAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 4).equals("move")){
                        moveAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 5).equals("shoot")){
                        shootAnimation.add(img);
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 12).equals("defaultphoto")){
                        defaultPhoto = img;
                    } else if (innerchild.getParent().substring(innerchild.getParent().length() - 10).equals("deathphoto")){
                        deathphoto = img;
                    }



//
//                    switch (innerchild.getParent()) {
//                        case
//                            break;
//                        case "resources/units/inferno/imp/visual/death":
//                            deathAnimation.add(img);
//                            break;
//                        case "resources/units/inferno/imp/visual/hit":
//                            hitAnimation.add(img);
//                            break;
//                        case "resources/units/inferno/imp/visual/move":
//                            moveAnimation.add(img);
//                            break;
//                        case "resources/units/inferno/imp/visual/shoot":
//                            shootAnimation.add(img);
//                            break;
//                        case "resources/units/inferno/imp/visual/defaultphoto":
//                            defaultPhoto = img;
//                            break;
//                        case "resources/units/inferno/imp/visual/deathphoto":
//                            deathphoto = img;
//                            break;

                    }
                }
            }
        }


//    public void attachPhoto() {
//        this.unit.getPosition().getChildren().add(this.defaultPhoto());
//    }

}

