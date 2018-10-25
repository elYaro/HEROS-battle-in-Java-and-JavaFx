package com.heroes.model;

import com.heroes.view.SquareView;
import com.heroes.view.UnitView;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.Comparator;


import java.io.IOException;
import java.util.*;

public class Game extends Pane {

    private List<Square> squaresList = FXCollections.observableArrayList();
    private Player P1;
    private Player P2;
    private Player[] arrayOfPlayers = new Player[2];
    private ArrayList<Unit> unitsInTheGame;
    private int iterUnit;


    public Game() throws IOException {  //Game constructor
        createSquares();
//        createPlayersAndTheirsUnits();
//        createArrayListOfAllUnitsInTheGame();
    }


    /**
     * @author Yaro
     * event handler for specific unit in the ArrayList od all units in the game.
     * After mouse click it increments the iter variable by one. Iter is used to pick specific unit from the Array of all units
     */
    private EventHandler<MouseEvent> onMouseClickedHandler = e -> {
        Rectangle squareView = (Rectangle) e.getSource();
        System.out.println(squareView.getParent());
        System.out.println(squareView.getBoundsInParent());
        System.out.println(squareView.getScene());
        System.out.println(squareView.getParent().getParent());



//        MouseUtils.moveToSquare(this.unitsInTheGame.get(iterUnit), square);
//        if (iterUnit < 13) {
//            iterUnit++;
//        } else iterUnit = 0;
    };


    public void createPlayersAndTheirsUnits() throws IOException {
        this.P1 = new Player("P1", true, "Castle");
        this.P2 = new Player("P2", false, "Inferno");
        arrayOfPlayers[0] = P1;
        arrayOfPlayers[1] = P2;
        for (Player player : arrayOfPlayers) {
            player.createUnitsObjects(player);
            player.setUnitSeeding(squaresList);
            UnitView.attachPhoto(player, this);
            UnitView.refineStartingCoords(player, this);
        }


//        P1.getUnitList().get(0).getUnitView().getDefaultPhoto().setLayoutX(0);
//        P1.getUnitList().get(0).getUnitView().getDefaultPhoto().setLayoutY(0);
    }


    public void addMouseEventHandlers(Rectangle gameSquareView) {
        gameSquareView.setOnMouseClicked(onMouseClickedHandler);
    }


    private void createSquares() {
        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setVgap(5);
        grid.setHgap(5);



        for (int listWidth = 1; listWidth <= 15; listWidth++) {
            for (int listHeigth = 1; listHeigth <= 11; listHeigth++) {

                Square gameSquare = new Square(listWidth, listHeigth);

                SquareView gameSquareView = new SquareView(gameSquare);

                gameSquare.setSquareView(gameSquareView);

                GridPane.setConstraints(gameSquareView.getRect(), listWidth, listHeigth);
                grid.getChildren().add(gameSquareView.getRect());
                addMouseEventHandlers(gameSquareView.getRect());

                gameSquareView.setRectView(grid.getChildren().get(grid.getChildren().size() -1));

                squaresList.add(gameSquare);

            }
        }
        grid.setPrefSize(865, 633);

        this.getChildren().add(grid);
        System.out.println(grid.getChildren().get(20).getBoundsInParent());
        grid.setLayoutX((1366 - (15 * 53) - (14 * 5)) / 2);
        grid.setLayoutY(115);


        System.out.println(grid.getChildren().get(9).boundsInParentProperty().toString());







    }

//    private void createSquares() {
//        for (int listHeigth = 1; listHeigth <= 11; listHeigth++) {
//            for (int listWidth = 1; listWidth <= 15; listWidth++) {
//                Square gameSquare = new Square(listWidth, listHeigth);
//                gameSquare.setBlurredBackground();
//                gameSquare.setLayoutX(((listWidth - 1) * 55) + 270);
//                gameSquare.setLayoutY(((listHeigth - 1) * 55) + 140);
//                gameSquare.setId(gameSquare.getName());
//                addMouseEventHandlers(gameSquare);
//                squaresList.add(gameSquare);
//                getChildren().add(gameSquare);
//            }
//        }
//    }

//
    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }


    /**
     * author Yaro
     * creates the ArrayList containing unit objects. Includes units of both Players.
     * Then the ArrayList is sorted by initiative attribute descending
     */
    public void createArrayListOfAllUnitsInTheGame() {
        ArrayList<Unit> unitsInTheGame = new ArrayList<>();
        for (Unit unit : P1.getUnitList()) {
            unitsInTheGame.add(unit);       //mamy liste unitow P1
        }
        for (Unit unit : P2.getUnitList()) {
            unitsInTheGame.add(unit);       //mamy liste unitow P1 + P2
        }

        Collections.sort(unitsInTheGame, new Comparator<>() {   //sort descending
            public int compare(Unit u1, Unit u2) {
                return Integer.valueOf(u2.initiative).compareTo(u1.initiative); //example of decending sort, to have ascending switch to: u1 i u2
            }
        });
        for (int i = 0; i < unitsInTheGame.size(); i++) {
            System.out.println(unitsInTheGame.get(i).name + " posiada inicjatywe = " + unitsInTheGame.get(i).initiative);
        }
        this.unitsInTheGame = unitsInTheGame;
    }
}



