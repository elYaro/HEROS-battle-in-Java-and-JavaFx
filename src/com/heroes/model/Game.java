package com.heroes.model;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.util.Comparator;


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




public ArrayList<Unit> createArrayListOfAllUnitsInTheGame(){   //yaro
        //tworzymy arrayList of unit Objects
        ArrayList<Unit> unitsInTheGame = new ArrayList<>();
        for (Unit unit: P1.getUnitList()) {
            unitsInTheGame.add(unit);       //mamy liste unitow P1
        }
        for (Unit unit: P2.getUnitList()) {
            unitsInTheGame.add(unit);       //mamy liste unitow P1 + P2
        }

        // sortujemy inity w arrayList wg jednego z atrybutow : tu wg inicjatywa
        Collections.sort(unitsInTheGame, new Comparator<>() {
            public int compare(Unit u1, Unit u2) {
                return Integer.valueOf(u2.initiative).compareTo(u1.initiative); //example of decending sort, to have ascending switch to: u1 i u2
            }
        });
        for (int i = 0; i < unitsInTheGame.size();i++){
            System.out.println(unitsInTheGame.get(i).name + " posiada inicjatywe = " + unitsInTheGame.get(i).initiative);
        }
    return unitsInTheGame;
}

public void ruchGracza(){
        ArrayList<Unit>unitsInTheGame = createArrayListOfAllUnitsInTheGame();


}




}
