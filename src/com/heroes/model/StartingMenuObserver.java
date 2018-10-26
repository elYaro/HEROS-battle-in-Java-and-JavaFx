package com.heroes.model;

import com.heroes.controller.Heroes;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class StartingMenuObserver implements Observer {

    public void update(Observable obj, Object arg) {
        try {
            Heroes.startGame(Heroes.getGameBackground());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}