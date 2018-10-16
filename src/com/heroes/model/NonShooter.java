package com.heroes.model;

import java.util.HashMap;

public class NonShooter extends Unit{
    public NonShooter(HashMap<String,String> UnitParameters,Player player){
        name = UnitParameters.get("name");
        shooter = Boolean.parseBoolean(UnitParameters.get("shooter"));
        owner = player;
        town = UnitParameters.get("town");
        attackPower = Integer.parseInt(UnitParameters.get("attackPower"));
        defencePower = Integer.parseInt(UnitParameters.get("defencePower"));
        minAttackDomage = Integer.parseInt(UnitParameters.get("minAttackDomage"));
        maxAttackDomage = Integer.parseInt(UnitParameters.get("maxAttackDomage"));
        healthPoints = Integer.parseInt(UnitParameters.get("healthPoints"));
        healthPointsLeft = Integer.parseInt(UnitParameters.get("healthPointsLeft"));
        moveRange = Integer.parseInt(UnitParameters.get("moveRange"));
        initiative = Integer.parseInt(UnitParameters.get("initiative"));
        quantity = Integer.parseInt(UnitParameters.get("quantity"));
        x = Integer.parseInt(UnitParameters.get("x"));
        y = Integer.parseInt(UnitParameters.get("y"));
        isDefending = Boolean.parseBoolean(UnitParameters.get("isDefending"));
        isDead = Boolean.parseBoolean(UnitParameters.get("isDead"));
//        unitview = new UnitView(name);

    }
}
