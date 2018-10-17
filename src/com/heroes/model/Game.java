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
import java.util.stream.IntStream;

public class Game extends Pane {

    private List<Square> squaresList = FXCollections.observableArrayList();
    private Player P1;
    private Player P2;


    public Game() throws IOException {
        createSquares();
        createPlayersAndTheirsUnits();

        whereCanMove(P1.getUnitList().get(5));
    }

    private EventHandler<MouseEvent> onMouseClickedHandler = e -> {
        Square square = (Square) e.getSource();
        System.out.println(square.getName());
    };

    public void createPlayersAndTheirsUnits() throws IOException {
        this.P1 = new Player("P1",true,"Castle");
        this.P1.createUnitsObjects(this.P1);
        this.P1.setUnitSeeding(squaresList);

        this.P2 = new Player("P2",false,"Inferno");
        this.P2.createUnitsObjects(this.P2);
        this.P2.setUnitSeeding(squaresList);
    }


    public void addMouseEventHandlers(Square gamesquare) {
        gamesquare.setOnMouseClicked(onMouseClickedHandler);
    }


    private ArrayList<Object> whereCanMove(Unit unit){
        int range = unit.getMoveRange();
        Square actualPosition = unit.getPosition();
        int actualPositionNumber = ((actualPosition.getLocationY()-1)*15+actualPosition.getLocationX()-1);
        int[] yBorderNumbers = {0, 15, 30, 45, 60, 75, 90, 105, 120 ,135, 150,
                                14, 29, 44, 59, 74, 89, 104, 119, 134, 149, 164};

        int[] xBorderNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                                150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164};

        List<Integer> possibleX = new ArrayList<Integer>();
        boolean contains = IntStream.of(xBorderNumbers).anyMatch(x -> x == 4);
        ArrayList<Object> array = new ArrayList<Object>();
//        System.out.println(actualPosition.getLocationX());
        possibleX.add(actualPosition.getLocationX());
        for(int a = actualPosition.getLocationX()+1; a<=actualPosition.getLocationX()+range ;a++){
            if (a>15) {
                break;
            }
            System.out.println("x za "+a);
            possibleX.add(a);

        }
        for(int a = actualPosition.getLocationX()-1; a>=actualPosition.getLocationX()-range ;a--){
            if (a<1) {
                break;
            }
            System.out.println("x przed "+a);
            possibleX.add(a);

        }
        System.out.println(possibleX);


        return array ;


    }

    private void createSquares(){
        for(int listHeigth = 1; listHeigth <= 11; listHeigth++) {
            for (int listWidth = 1; listWidth <= 15; listWidth++) {
                Square gameSquare = new Square(listWidth, listHeigth);
                gameSquare.setBlurredBackground();
                gameSquare.setLayoutX(((listWidth - 1) * 55) + 270);
                gameSquare.setLayoutY(((listHeigth - 1) * 55) + 140);
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
