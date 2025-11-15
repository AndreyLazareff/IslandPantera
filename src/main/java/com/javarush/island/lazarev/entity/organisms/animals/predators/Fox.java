package com.javarush.island.lazarev.entity.organisms.animals.predators;

import com.javarush.island.lazarev.config.Species;

public class Fox extends Predator {
    public Fox(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.FOX.getIcon();
    }
}
