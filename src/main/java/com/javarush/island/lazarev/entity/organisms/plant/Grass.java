package com.javarush.island.lazarev.entity.organisms.plant;

import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.organisms.Organizm;

public class Grass extends Organizm {
    public Grass() { super(Species.GRASS); }

    @Override public void move() {}
    @Override public void eat() {}
    @Override public String getSymbol() { return Species.GRASS.getIcon(); }
}
