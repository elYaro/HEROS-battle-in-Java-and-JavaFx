package com.heroes.model;

import com.heroes.audio.UnitSounds;
import com.heroes.view.BackgroundView;
import com.heroes.view.UnitView;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


import javax.swing.text.html.ImageView;

import java.awt.event.MouseListener;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class Game extends Pane {

    private List<Square> squaresList = FXCollections.observableArrayList();
    private Player P1;
    private Player P2;
    private Player[] arrayOfPlayers = new Player[2];
    private ArrayList<Unit> unitsInTheGame;
    private int iterUnit;
    private BackgroundView gameBackground;
    private static boolean isMoving = false;
    private static boolean wasMove = false;
    private static boolean canClickSkipTurn = true;
    private List<Square> squaresToMove;
    List<Unit> possibleUnitsToAttack;

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
        addButton();
//        prepare move for first unit
        checkWhereCanMove();
        checkPossibleUnitsToAttack();
        makeSquareShadows();
        UnitView.higlightUnit(unitsInTheGame.get(iterUnit).getUnitView());
    }


    public static void setMoving(boolean moving) {
        isMoving = moving;
    }

    public static void setWasMove(boolean wasMove) {
        Game.wasMove = wasMove;
    }
    public void addButton(){
        Button button = new Button("finish the move");
        button.setLayoutX(100);
        button.setLayoutY(680);
        button.setOnMouseClicked(endMoveButtonClicked);
        this.gameBackground.getChildren().add(button);
    }


    private EventHandler<MouseEvent> endMoveButtonClicked = e -> {
        if (!isMoving) {
            endTurn();
        }
    };

    private void endTurn(){
        deleteSquareShadows();
        UnitView.removeHighlightFromUnit(unitsInTheGame.get(iterUnit).getUnitView());
        UnitView.removeHighlightFromGroupOfUnits(this.possibleUnitsToAttack);
        changeIterUnitToNextUnit();
        UnitView.higlightUnit(unitsInTheGame.get(iterUnit).getUnitView());
        checkWhereCanMove();
        checkPossibleUnitsToAttack();
        UnitView.highlightGroupOfUnits(this.possibleUnitsToAttack);
        makeSquareShadows();
    }



    /**
     * @author Yaro
     * event handler for specific unit in the ArrayList of all units in the game.
     * After mouse click it increments the iter variable by one. Iter is used to pick specific unit from the Array of all units
     */
    private EventHandler<MouseEvent> onMouseClickedHandler = e -> {

        if (!isMoving) {
            if (e.getSource().getClass().getName().equals("com.heroes.model.Square")) {
                if (!wasMove) {
                    Square square = (Square) e.getSource();
                    for (Square squareToMove : this.squaresToMove) {
                        if (squareToMove.getName() == square.getName()) {
                            setMoving(true);
                            setWasMove(true);
                            deleteSquareShadows();
                            move(square);
                            UnitView.removeHighlightFromGroupOfUnits(this.possibleUnitsToAttack);
                            checkPossibleUnitsToAttack();
                            UnitView.highlightGroupOfUnits(this.possibleUnitsToAttack);
                            new Thread(() -> {
                                try {
                                    Thread.sleep(MouseUtils.moveTime);
                                    if (this.possibleUnitsToAttack.size() == 0){
                                        System.out.println("there is no possible attack for "+ this.unitsInTheGame.get(iterUnit).getName());
                                        endTurn();
                                    }
                                } catch (Exception error) {
                                    System.err.println(error);
                                }
                            }).start();
                        }
                    }
                }
            }
            if (e.getSource().getClass().getName().equals("javafx.scene.image.ImageView")) {
                mainLoop:for (Unit unit : unitsInTheGame) {
                    if (e.getTarget().toString().equals(unit.getUnitView().getDefaultPhoto().toString())) {
                        if (!wasMove){
                            if (this.unitsInTheGame.get(iterUnit).getName().equals(unit.getName())) {
                                this.unitsInTheGame.get(iterUnit).setDefending(true);
                                System.out.println(unit.getName() + " is defending");
                                endTurn();
                                break;
                            }
                        }
                        for (Unit unitToAttact : this.possibleUnitsToAttack) {
                            if (unitToAttact.getName().equals(unit.getName())) {
                                unitToAttact.attack(this.unitsInTheGame.get(iterUnit),unit);
                                MouseUtils.universalAnimation(this.unitsInTheGame.get(iterUnit), this.unitsInTheGame.get(iterUnit).getUnitView().getAttackAnimation());
                                if (unit.isDead() == false) {
                                    MouseUtils.universalAnimation(unit, unit.getUnitView().getHitAnimation());
                                } else {
                                    MouseUtils.universalAnimation(unit, unit.getUnitView().getDeathAnimation());
                                }
                                deleteSquareShadows();
                                new Thread(() -> {
                                    try {
                                    Thread.sleep((long) (MouseUtils.attackTime*1.2));
                                        endTurn();
                                        if (P1.getLeftUnits() == 0 || P2.getLeftUnits() == 0) {
//                                    END GAME
                                            System.out.println("GAME OVER");
                                        }
                                    } catch (Exception error) {
                                        System.err.println(error);
                                    }
                                }).start();
                                break mainLoop;
                            }
                        }
                        if (unit.getTown().equals(this.unitsInTheGame.get(iterUnit).getTown())) {
                            System.out.println(unit.getName() + "its your teammate");
                        } else {
                            System.out.println("you are too far away from " + unit.getName());
                        }

                        break;
                    }
                }
            }
        }
    };


    private void changeIterUnitToNextUnit(){
        if (iterUnit < 13 ) {
            do {
                iterUnit++;
            }
            while(this.unitsInTheGame.get(iterUnit).isDead);


        } else iterUnit = 0;
        setWasMove(false);
    }
    private void makeSquareShadows(){
        Square.highlightStandableSquares(squaresToMove, Square.getSquareOpacityValues().get("Highlight"));
    }
    private void deleteSquareShadows(){
        Square.highlightStandableSquares(this.squaresToMove, Square.getSquareOpacityValues().get("Normal"));
    }
    private void checkWhereCanMove(){
        this.squaresToMove = Validation.createArrayOfSquareToMove(this.unitsInTheGame.get(iterUnit), this.squaresList);
    }
    private void checkPossibleUnitsToAttack(){
        this.possibleUnitsToAttack = Validation.createArrayOfUnitsToAttack(this.unitsInTheGame.get(iterUnit), this.unitsInTheGame);
    }
    private void move(Square square){
        this.unitsInTheGame.get(iterUnit).setDefending(false);
        this.unitsInTheGame.get(iterUnit).getUnitSound().playSound(this.unitsInTheGame.get(iterUnit), UnitSounds.UnitSound.MOVE);
        MouseUtils.moveToSquare(this.unitsInTheGame.get(iterUnit), square);
    }


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
            player.createVboxWithUnitCounters(player, this.gameBackground);
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
            }
        }
    }


    /**
     * @author Yaro
     * creates the ArrayList containing unit objects. Includes units of both Players.
     * Then the ArrayList is sorted by initiative attribute descending
     */
    private void createArrayListOfAllUnitsInTheGame() {
        ArrayList<Unit> unitsInTheGame = new ArrayList<>();
        for (Unit unit : P1.getUnitList()) {
            unit.getUnitView().getDefaultPhoto().setOnMouseClicked(onMouseClickedHandler);
            unitsInTheGame.add(unit);       //mamy liste unitow P1
        }
        for (Unit unit : P2.getUnitList()) {
//            unit.setOnMouseClicked(event -> System.out.println("elo"));
            unit.getUnitView().getDefaultPhoto().setOnMouseClicked(onMouseClickedHandler);
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




