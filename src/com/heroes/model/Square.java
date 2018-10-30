package com.heroes.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.effect.*;
import javafx.scene.shape.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class Square extends Pane {

    private boolean isStandable = true;
    private int squareHeigth = 53;
    private int squareWidth = 53;
    private int locationX;
    private int locationY;
    private String name;

    private static final HashMap <String, Double> squareOpacityValues = new HashMap<String, Double>(){{
        put("Normal",0.4);
        put("Highlight",0.7);
    }};


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

    public static HashMap<String, Double> getSquareOpacityValues() {
        return squareOpacityValues;
    }

    public void setName() {
        this.name = String.valueOf(this.locationX) + "/" + String.valueOf(this.locationY);
    }


    public void setStandable(boolean standable) {
        isStandable = standable;
    }

    public void setSquareOpacityValues() {
        this.squareOpacityValues.put("Normal", 0.4);
        this.squareOpacityValues.put("Normal", 0.7);
    }

    public void setBlurredBackground() {
        setPrefSize(squareHeigth, squareWidth);
        BackgroundFill backgroundFill = new BackgroundFill(Color.gray(0.1, 0.4), new CornerRadii(5), null);
        Background background = new Background(backgroundFill);
        setBackground(background);
    }

    private void highlightSquare(Double value){
            BackgroundFill backgroundFill = new BackgroundFill(Color.gray(0.1, value), new CornerRadii(5), null);
            Background background = new Background(backgroundFill);
            setBackground(background);
    }

    public static void highlightStandableSquares(List<Square> standableSquaresArray, Double value){
        for(Square square : standableSquaresArray){
            square.highlightSquare(value);
        }
    }



}
