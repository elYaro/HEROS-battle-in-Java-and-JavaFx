package com.heroes.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.effect.*;
import javafx.scene.shape.*;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Square extends Pane {

    private boolean isStandable = false;
    private int squareHeigth = 53;
    private int squareWidth = 53;
    private int locationX;
    private int locationY;
    private String name;


    public Square(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.setName();
    }


    public boolean getIsStandable() {
        return isStandable;
    }

    public int getSqureHeigth() {
        return squareHeigth;
    }

    public int getSquareWidth() {
        return squareWidth;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = String.valueOf(this.locationX) + "/" + String.valueOf(this.locationY);
    }

    public void setStandable(boolean standable) {
        isStandable = standable;
    }

    public void setBlurredBackground() {
        setPrefSize(squareHeigth, squareWidth);
        BackgroundFill backgroundFill = new BackgroundFill(Color.gray(0.1, 0.4), null, null);
        Background background = new Background(backgroundFill);
        GaussianBlur gaussianBlur = new GaussianBlur(10);
        setBackground(background);
//        setEffect(gaussianBlur);
    }


}
