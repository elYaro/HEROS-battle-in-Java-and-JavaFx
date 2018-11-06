package com.heroes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shooter extends Unit {
    public Shooter(HashMap<String,String> UnitParameters,Player player){
        name = UnitParameters.get("name");
        shooter = Boolean.parseBoolean(UnitParameters.get("shooter"));
        owner = player;
        town = UnitParameters.get("town");
        attackPower = Integer.parseInt(UnitParameters.get("attackPower"));
        defencePower = Integer.parseInt(UnitParameters.get("defencePower"));
        minAttackDamage = Integer.parseInt(UnitParameters.get("minAttackDamage"));
        maxAttackDamage = Integer.parseInt(UnitParameters.get("maxAttackDamage"));
        healthPoints = Integer.parseInt(UnitParameters.get("healthPoints"));
        healthPointsLeft = Integer.parseInt(UnitParameters.get("healthPointsLeft"));
        moveRange = Integer.parseInt(UnitParameters.get("moveRange"));
        initiative = Integer.parseInt(UnitParameters.get("initiative"));
        quantity = Integer.parseInt(UnitParameters.get("quantity"));
        x = Integer.parseInt(UnitParameters.get("x"));
        y = Integer.parseInt(UnitParameters.get("y"));
        isDefending = Boolean.parseBoolean(UnitParameters.get("isDefending"));
        isDead = Boolean.parseBoolean(UnitParameters.get("isDead"));
    }

    @Override
    public List<Unit> createArrayOfUnitsToAttack(Unit movingUnit, List<Unit> unitsInTheGame) {
        List<Unit> unitsToAttack = new ArrayList<>();
        for (Unit unit : unitsInTheGame) {
            if (!(unit.getTown().equals(movingUnit.getTown())) && !(unit.isDead)) {
                unitsToAttack.add(unit);
            }
        }
        return unitsToAttack;
    }
}
