package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;

public class Horse extends Herbivore {
    public Horse(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.HORSE.getIcon();
    }
}
