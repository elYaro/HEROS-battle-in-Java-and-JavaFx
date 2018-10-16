package com.heroes.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private Boolean canMove;
    private List<Unit> unitList = new ArrayList<Unit>();
    private int leftUnits;
    private boolean startsOnLeftSide;


    Player(String name1,Boolean canMove1, boolean leftSide){
        name = name1;
        canMove = canMove1;
        leftUnits = 7;
        startsOnLeftSide = leftSide;
    }


    public String getName() {
        return name;
    }
    public boolean getIfCanMove() {
        return canMove;
    }
    public List<Unit> getUnitList(){
        return unitList;
    }
    public int getLeftUnits() {
        return leftUnits;
    }
    public boolean getIfStartsOnLeftSide() {
        return startsOnLeftSide;
    }


    public void setIfCanMove(boolean yORn) {
        this.canMove = yORn;
    }
    public void setUnitList(List<Unit> listOfUnits){
        this.unitList = listOfUnits;
    }
    public void setLeftUnits(int numb){
        this.leftUnits = numb;
    }
}
