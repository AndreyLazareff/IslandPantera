package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;

public class Goat extends Herbivore {
    public Goat(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.GOAT.getIcon();
    }
}
