package com.heroes.model;



import java.lang.reflect.Array;

public abstract class Unit {
    protected String name;
    protected String town;
    protected boolean shooter;
    protected Player owner;
    protected int attackPower;
    protected int defencePower;
    protected int minAttackDomage;
    protected int maxAttackDomage;
    protected int healthPoints;
    protected int healthPointsLeft;
    protected int moveRange;
    protected int initiative;
    protected int quantity;
    protected int x;
    protected int y;
    protected boolean isDefending;
    protected boolean isDead;
//    protected UnitView unitView;
//
//    public void setUnitView(UnitView arg){
//        unitView = arg;
//    }
    public void move(){
//        return "i can move";
    }
    public int calculateAttackBonus(){
        return 69;
    }
//    public void attack(bonus){}
    public void defend(){

    }


//    public Unit(String name) {
//        this.name = name;
//        this.town = town;
//        this.owner = owner;
//    }
}
