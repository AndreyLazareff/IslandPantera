package com.javarush.island.lazarev.entity.organisms;

import com.javarush.island.lazarev.api.Eater;
import com.javarush.island.lazarev.api.Mortal;
import com.javarush.island.lazarev.api.Movable;
import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.map.GameMap;

public abstract class Organizm implements Movable, Mortal, Eater {
    protected boolean alive = true;
    protected int x, y;
    protected GameMap gameMap;
    protected Species species;

    protected Organizm(Species species) {
        this.species = species;
    }

    public void setPosition(int x, int y, GameMap map) {
        this.x = x;
        this.y = y;
        this.gameMap = map;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Species getSpecies() {
        return species;
    }

    public boolean isAlive() {
        return alive;
    }

    public void death() {
        alive = false;
    }

    public void move() {
    }

    public void eat() {
    }

    public abstract String getSymbol();
}

