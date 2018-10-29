package com.heroes.model;

import java.util.ArrayList;
import java.util.List;

public class Validation {
    public static void createArrayOfSquareToMove(Unit unit, List<Square> squaresList){
        ArrayList<Square>squaresToMove = new ArrayList<>();
        for (Square square : squaresList){
            if ((Math.abs(square.getLocationX() - unit.getX()) + Math.abs(square.getLocationY()-unit.getY()) <= 2) && square.getIsStandable()){
                squaresToMove.add(square);
            }

        }
        System.out.println(unit.getName());
        for (int i = 0; i < squaresToMove.size(); i++){
            System.out.println(squaresToMove.get(i).getName());
        }


    }
}
