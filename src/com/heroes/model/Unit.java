package com.heroes.model;

import com.heroes.view.*;
import javafx.scene.Node;




import java.lang.reflect.Array;

public abstract class Unit extends Node {
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

    protected Square position;

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

    public UnitView getUnitView() {
        return unitView;
    }

    public Square getPosition() {
        return position;
    }



    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public void move(){ }
    public int calculateAttackBonus(){
        return 69;
    }
//    public void attack(bonus){}
    public void defend(){ }
}
