package com.javarush.island.lazarev.entity.organisms.animals.predators;

import com.javarush.island.lazarev.config.Species;

public class Eagle extends Predator {
    public Eagle(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.EAGLE.getIcon();
    }
}
