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

public class Game extends Pane {

    private List<Square> squaresList = FXCollections.observableArrayList();
    private Player P1;
    private Player P2;
    private Player[] arrayOfPlayers = new Player[2];
    private ArrayList<Unit> unitsInTheGame;
    private int iterUnit;
    private BackgroundView gameBackground;

    private List<Square> test = FXCollections.observableArrayList();


    /**
     * Game Constructor
     * It calls up methods that create board of standable squares, players and their units,
     * and list of all units in the game which is used for for assigning order.
     * */
    public Game(BackgroundView gameBackground) throws IOException {
        this.gameBackground = gameBackground;
        createSquares();
        createPlayersAndTheirsUnits();
        createArrayListOfAllUnitsInTheGame();
        List<Square> firstUnitSquaresRange = Validation.createArrayOfSquareToMove(this.unitsInTheGame.get(iterUnit), this.squaresList);
        List<Unit> UnitsToAttackByFirstUnit = Validation.createArrayOfUnitsToAttack(this.unitsInTheGame.get(iterUnit), this.unitsInTheGame);
        Square.highlightStandableSquares(firstUnitSquaresRange, Square.getSquareOpacityValues().get("Highlight"));
    }


    /**
     * @author Yaro
     * event handler for specific unit in the ArrayList of all units in the game.
     * After mouse click it increments the iter variable by one. Iter is used to pick specific unit from the Array of all units
     */
    private EventHandler<MouseEvent> onMouseClickedHandler = e -> {
        Square square = (Square) e.getSource();
        List<Square> previousUnitSquaresRange = Validation.createArrayOfSquareToMove(this.unitsInTheGame.get(iterUnit), this.squaresList);
        Square.highlightStandableSquares(previousUnitSquaresRange, Square.getSquareOpacityValues().get("Normal"));
        this.unitsInTheGame.get(iterUnit).getUnitSound().playSound(this.unitsInTheGame.get(iterUnit), UnitSounds.UnitSound.MOVE);
        MouseUtils.moveToSquare(this.unitsInTheGame.get(iterUnit), square);
        if (iterUnit < 13) {
            iterUnit++;
        } else iterUnit = 0;
        List<Square> nextUnitSquaresRange = Validation.createArrayOfSquareToMove(this.unitsInTheGame.get(iterUnit), this.squaresList);
        List<Unit> UnitsToAttackByFirstUnit = Validation.createArrayOfUnitsToAttack(this.unitsInTheGame.get(iterUnit), this.unitsInTheGame);
        Square.highlightStandableSquares(nextUnitSquaresRange, Square.getSquareOpacityValues().get("Highlight"));
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


    /**
     * Entire board is created here. Notice that event handlers are attached to squares at this point.
     */
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
                if (listHeigth < 3){
                    test.add(gameSquare);
                }
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



