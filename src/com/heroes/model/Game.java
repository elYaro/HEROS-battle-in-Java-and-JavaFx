package com.heroes.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.io.IOException;
import java.util.*;

public class Game extends Pane {

    private List<Square> squaresList = FXCollections.observableArrayList();
    private Player P1;
    private Player P2;


    public Game() throws IOException {
        createSquares();
        createPlayersAndTheirsUnits();
    }

    private EventHandler<MouseEvent> onMouseClickedHandler = e -> {
        Square square = (Square) e.getSource();
//        System.out.println(square.getLocationX());
//        System.out.println(square.getLocationY());
        System.out.println(square.getName());
    };

    public void createPlayersAndTheirsUnits() throws IOException {
        this.P1 = new Player("P1",true,"Castle");
        this.P1.createUnitsObjects(this.P1);
        this.P1.setUnitSeeding(squaresList);
        System.out.println(this.P1.getUnitList().get(0).getPosition().getName());
        System.out.println(this.P1.getUnitList().get(6).getPosition().getName());


        this.P2 = new Player("P2",false,"Inferno");
        this.P2.createUnitsObjects(this.P2);
        this.P2.setUnitSeeding(squaresList);
        System.out.println(this.P2.getUnitList().get(0).getPosition().getName());
        System.out.println(this.P2.getUnitList().get(6).getPosition().getName());
//        System.out.println(this.P1.getUnitList().get(0).getPosition());


    }


    public void addMouseEventHandlers(Square gamesquare) {
        gamesquare.setOnMouseClicked(onMouseClickedHandler);
    }


    private void createSquares(){
        for(int listWidth = 0; listWidth < 15; listWidth++) {
            for (int listHeigth = 0; listHeigth < 11; listHeigth++) {
                Square gameSquare = new Square(listWidth, listHeigth);
                gameSquare.setBlurredBackground();
                gameSquare.setLayoutX((listWidth * 55) + 270);
                gameSquare.setLayoutY((listHeigth * 55) + 140);
                addMouseEventHandlers(gameSquare);
                squaresList.add(gameSquare);
                getChildren().add(gameSquare);
            }
        }
    }


    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }



}
