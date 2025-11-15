package com.javarush.island.lazarev.entity.organisms.animals.predators;

import com.javarush.island.lazarev.config.Species;

public class Wolf extends Predator {
    public Wolf(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.WOLF.getIcon();
    }
}
