package com.javarush.island.lazarev.entity.organisms.animals.predators;

import com.javarush.island.lazarev.config.Species;

public class Bear extends Predator {
    public Bear(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.BEAR.getIcon();
    }
}
