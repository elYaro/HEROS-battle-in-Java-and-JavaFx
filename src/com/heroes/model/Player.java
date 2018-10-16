package com.heroes.model;

import com.heroes.view.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {

    private String name;
    private String town;
    private Boolean canMove;
    private List<Unit> unitList = new ArrayList<Unit>();
    private int leftUnits;
    private boolean startsOnLeftSide;

//    private List<HashMap> ListOfUnitsProperties =  Utils.fileRead();


    Player(String name, boolean leftSide, String town){
        this.town = town;
        this.name = name;
        this.leftUnits = 7;
        this.startsOnLeftSide = leftSide;
    }

    public void createUnitsObjects(Player player)throws IOException {
        Utils utils = new Utils();
        List<HashMap> ListOfUnitsProperties =  utils.fileRead(utils.createPath());
        for(HashMap<String, String> unitProperties:ListOfUnitsProperties) {
            if (unitProperties.get("town") == this.town) {
                if (unitProperties.get("shooter") == "true") {
                    Unit unit = new Shooter(unitProperties, player);
                    unitList.add(unit);
                    UnitView unitView = new UnitView(unit);
                    unit.setUnitView(unitView);
                }if (unitProperties.get("shooter") == "false"){
                    Unit unit = new NonShooter(unitProperties, player);
                    unitList.add(unit);
                    UnitView unitView = new UnitView(unit);
                    unit.setUnitView(unitView);
                }
            }
        }
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
