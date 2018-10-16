package com.heroes.model;



import java.lang.reflect.Array;

public abstract class Unit {
    protected String name;
    protected String town;
    protected boolean shooter;
    protected Player owner;
    protected int attackPower;
    protected int defencePower;
    protected int minAttackDamage;
    protected int maxAttackDamage;
    protected int healthPoints;
    protected int healthPointsLeft;
    protected int moveRange;
    protected int initiative;
    protected int quantity;
    protected int x;
    protected int y;
    protected boolean isDefending;
    protected boolean isDead;
    protected UnitView unitView;

    public void setUnitView(UnitView arg){
        unitView = arg;
    }

    public boolean isShooter() {
        return shooter;
    }

    public Player getOwner() {
        return owner;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public int getMinAttackDamage() {
        return minAttackDamage;
    }

    public int getMaxAttackDamage() {
        return maxAttackDamage;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getHealthPointsLeft() {
        return healthPointsLeft;
    }

    public int getMoveRange() {
        return moveRange;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDefending() {
        return isDefending;
    }

    public boolean isDead() {
        return isDead;
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public void move(){ }
    public int calculateAttackBonus(){
        return 69;
    }
//    public void attack(bonus){}
    public void defend(){ }
}
