package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;

public class Buffalo extends Herbivore {
    public Buffalo(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.BUFFALO.getIcon();
    }
}
