package com.heroes.model;

import com.heroes.view.*;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player extends Pane {

    public int[] P1seeding = {0, 31, 45, 76, 105, 121, 150};
    /**
     * name - name of the player: P1 or P2.
     * town - one of the nine fractions available in homm3. In this case Castle or Inferno.
     * canMove - boolean that determines if at certain point unit can move or not.
     * unitlist - list containing Unit objects. Each player has a full set(7 units) of every unit available at his naitve town.
     * leftUnits - int variable determining how many units remain alive
     * startsOnLeftSide - starts on left or right
     * seeding lists - list of indices in square list, specifying at which squares player's units should spawn.
     */

    private String name;
    private String town;
    private Boolean canMove;
    private List<Unit> unitList = new ArrayList<Unit>();
    private int leftUnits;
    private boolean startsOnLeftSide;
    private int[] P2seeding = {14, 43, 59, 88, 119, 133, 164};


    Player(String name, boolean leftSide, String town) {
        this.town = town;
        this.name = name;
        this.leftUnits = 7;
        this.startsOnLeftSide = leftSide;
    }

    public String getName() {
        return name;
    }

    public boolean getIfCanMove() {
        return canMove;
    }

    public void setIfCanMove(boolean yORn) {
        this.canMove = yORn;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> listOfUnits) {
        this.unitList = listOfUnits;
    }

    public int getLeftUnits() {
        return leftUnits;
    }

    public void setLeftUnits(int numb) {
        this.leftUnits = numb;
    }

    public boolean getIfStartsOnLeftSide() {
        return startsOnLeftSide;
    }


    public void createUnitsObjects(Player player) throws IOException {
        Utils utils = new Utils();
        List<HashMap> ListOfUnitsProperties = utils.fileRead(utils.createPath());
        for (HashMap<String, String> unitProperties : ListOfUnitsProperties) {
            if (unitProperties.get("town").equals(this.town)) {
                if (unitProperties.get("shooter").equals("true")) {
                    Unit unit = new Shooter(unitProperties, player);
                    unitList.add(unit);
                    UnitView unitView = new UnitView(unit);
                    unit.setUnitView(unitView);
                }
                if (unitProperties.get("shooter").equals("false")) {
                    Unit unit = new NonShooter(unitProperties, player);
                    unitList.add(unit);
                    UnitView unitView = new UnitView(unit);
                    unit.setUnitView(unitView);
                }
            }
        }
    }


    public void setUnitSeeding(List<Square> squaresList) {
        if (this.startsOnLeftSide) {
            for (int i = 1; i <= this.unitList.size(); i++) {
                unitList.get(i - 1).setPosition(squaresList.get(P1seeding[i - 1]));
                squaresList.get(P1seeding[i - 1]).setStandable(false);
            }
        } else {
            for (int i = 1; i <= this.unitList.size(); i++) {
                unitList.get(i - 1).setPosition(squaresList.get(P2seeding[i - 1]));
                squaresList.get(P2seeding[i - 1]).setStandable(false);
            }
        }
    }


}
