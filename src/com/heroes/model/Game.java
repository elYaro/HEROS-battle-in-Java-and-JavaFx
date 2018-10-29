package com.heroes.model;

import com.heroes.audio.UnitSounds;
import com.heroes.view.BackgroundView;
import com.heroes.view.UnitView;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Comparator;


import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Game extends Pane {

    private List<Square> squaresList = FXCollections.observableArrayList();
    private Player P1;
    private Player P2;
    private Player[] arrayOfPlayers = new Player[2];
    private ArrayList<Unit> unitsInTheGame;
    private int iterUnit;
    private BackgroundView gameBackground;


    /**
     * Game Constructor
     * It calls up methods that create board of standable squares, players and their units,
     * and list of all units in the game which is used for for assigning order.
     * */
    public Game(BackgroundView gameBackground) throws IOException {
        this.gameBackground = gameBackground;
        createSquares();
        createPlayersAndTheirsUnits();


        whereCanMove(P1.getUnitList().get(5));
        createArrayListOfAllUnitsInTheGame();

    }


    /**
     * @author Yaro
     * event handler for specific unit in the ArrayList od all units in the game.
     * After mouse click it increments the iter variable by one. Iter is used to pick specific unit from the Array of all units
     */
    private EventHandler<MouseEvent> onMouseClickedHandler = e -> {
        Square square = (Square) e.getSource();
        this.unitsInTheGame.get(iterUnit).getUnitSound().playSound(this.unitsInTheGame.get(iterUnit), UnitSounds.UnitSound.MOVE);
        MouseUtils.moveToSquare(this.unitsInTheGame.get(iterUnit), square);
        if (iterUnit < 13) {
            iterUnit++;
        } else iterUnit = 0;
    };


    /**
     * Method that create players. First it creates two instances of players, then assign them to an array.
     * In a loop it creates units, set their starting position(seeding), attach photos to them and corrects excatly where
     * they should spawn(setUnitSeeding method only assigns square object to unit not its coordinates).
     */
    private void createPlayersAndTheirsUnits() throws IOException {
        this.P1 = new Player("P1", true, "Castle");
        this.P2 = new Player("P2", false, "Inferno");
        arrayOfPlayers[0] = P1;
        arrayOfPlayers[1] = P2;
        for (Player player : arrayOfPlayers) {
            player.createUnitsObjects(player);
            player.setUnitSeeding(squaresList);
            UnitView.attachPhoto(player, this.gameBackground);
            UnitView.refineStartingCoords(player, this);
        }
    }


    /**
     * Method that adds event handlers to squares. Important!!!
     */

    private void addMouseEventHandlers(Square gamesquare) {
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


//      Entire board is created here. Notice that event handlers are attached to squares at this point.

    private void createSquares() {
        for (int listHeigth = 1; listHeigth <= 11; listHeigth++) {
            for (int listWidth = 1; listWidth <= 15; listWidth++) {
                Square gameSquare = new Square(listWidth, listHeigth);
                gameSquare.setBlurredBackground();
                gameSquare.setLayoutX(((listWidth - 1) * 55) + 270);
                gameSquare.setLayoutY(((listHeigth - 1) * 55) + 140);
                gameSquare.setId(gameSquare.getName());
                addMouseEventHandlers(gameSquare);
                squaresList.add(gameSquare);
                this.gameBackground.getChildren().add(gameSquare);
            }
        }
    }


    /**
     * author Yaro
     * creates the ArrayList containing unit objects. Includes units of both Players.
     * Then the ArrayList is sorted by initiative attribute descending
     */
    private void createArrayListOfAllUnitsInTheGame() {
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



