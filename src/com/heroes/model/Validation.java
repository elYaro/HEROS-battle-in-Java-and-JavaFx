package com.heroes.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Class including different validation methods
 */
public class Validation {

    /**
     * @author Yaro & Karol
     * @param unit
     * @param squaresList
     * @return ArrayList<Square>
     */
    public static List<Square> createArrayOfSquareToMove(Unit unit, List<Square> squaresList){
        List<Square>squaresToMove = new ArrayList<>();
        for (Square square : squaresList){
            if ((Math.abs(square.getLocationX() - unit.getX()) + Math.abs(square.getLocationY()-unit.getY()) <= unit.getMoveRange()) && square.getIsStandable()){
                squaresToMove.add(square);
            }
        }
//        System.out.println(unit.getName());                             //testing and debuging
//        for (int i = 0; i < squaresToMove.size(); i++){                 //testing and debuging
//            System.out.println(squaresToMove.get(i).getName());         //testing and debuging
//        }
        return squaresToMove;
    }

    public static List<Unit> createArrayOfUnitsToAttack(Unit movingUnit, List<Unit> unitsInTheGame){
        List<Unit> unitsToAttack = new ArrayList<>();
        for (Unit unit : unitsInTheGame){
            if (Math.abs(unit.getX() - movingUnit.getX()) <= 1 && Math.abs(unit.getY() - movingUnit.getY()) <= 1 && !(unit.getTown().equals(movingUnit.getTown()))) {
                unitsToAttack.add(unit);
            }
        }
//        System.out.println(movingUnit.getName() + "is attacker");                             //testing and debuging
//        for (int i = 0; i < unitsToAttack.size(); i++){                 //testing and debuging
//            System.out.println(unitsToAttack.get(i).getName());         //testing and debuging
//        }
        return unitsToAttack;

    }
}
