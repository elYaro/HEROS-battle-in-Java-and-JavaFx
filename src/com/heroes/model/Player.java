package com.heroes.model;

import com.heroes.view.*;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player extends Pane {

    private String name;
    private String town;
    private Boolean canMove;
    private List<Unit> unitList = new ArrayList<Unit>();
    private int leftUnits;
    private boolean startsOnLeftSide;
    public int[] P1seeding = {0, 31, 45, 76, 105, 121, 150};
    private int[] P2seeding = {14, 43, 59, 88, 119, 133, 164};

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
            if (unitProperties.get("town").equals(this.town)) {
                if (unitProperties.get("shooter").equals("true")) {
                    Unit unit = new Shooter(unitProperties, player);
                    unitList.add(unit);
                    UnitView unitView = new UnitView(unit);
                    unit.setUnitView(unitView);
                }if (unitProperties.get("shooter").equals("false")){
                    Unit unit = new NonShooter(unitProperties, player);
                    unitList.add(unit);
                    UnitView unitView = new UnitView(unit);
                    unit.setUnitView(unitView);
                }
            }
        }
    }


    public void setUnitSeeding(List<Square> squaresList){
        if (this.startsOnLeftSide){
            for (int i = 1; i <= this.unitList.size(); i++){
                unitList.get(i-1).setPosition(squaresList.get(P1seeding[i-1]));
                squaresList.get(P1seeding[i-1]).setStandable(false);
            }
        } else {
            for (int i = 1; i <= this.unitList.size(); i++){
                unitList.get(i-1).setPosition(squaresList.get(P2seeding[i-1]));
                squaresList.get(P2seeding[i-1]).setStandable(false);
            }
        }
    }

//    public void attachPhoto(Player player){
//        for(Unit unit : player.getUnitList()){
//            unit.getPosition().getChildren().add(unit.getUnitView().getDefaultPhoto());
//            unit.getPosition().getChildren().get(0).getParent().toFront();
//            unit.getUnitView().getDefaultPhoto().setTranslateX(-170);
//            unit.getUnitView().getDefaultPhoto().setTranslateY(-220);
//            if(unit.getTown().equals("Inferno")){
//                unit.getUnitView().getDefaultPhoto().setTranslateX(-230);
//                unit.getUnitView().getDefaultPhoto().setRotationAxis(Rotate.Y_AXIS);
//                unit.getUnitView().getDefaultPhoto().setRotate(180);
//            }
//        }
//    }


//    public void addDefaultPhoto(Player player){
//        for (Unit unit : player.getUnitList()){
//            unit
//        }
//    }


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
