package com.heroes.model;

import java.lang.reflect.Array;

public abstract class Unit {
    private String name;
    private String town;
//    private Player owner;
    private int attackPower;
    private int defencePower;
    private int minAttackDomage;
    private int maxAttackDomage;
    private int healthPoints;
    private int healthPointsLeft;
    private int moveRange;
    private int initiative;
    private int quantity;

    private int[] location;
    private boolean isDefending;
    private boolean isDead;
//    private UnitVeiw unitview;

    public abstract void move();
    public abstract int calculateAttackBonus();
//    public abstract void attack(bonus);
    public abstract void defend();


    public Unit(String name) {
        this.name = name;
        this.town = town;
//        this.owner = owner;
    }
}
