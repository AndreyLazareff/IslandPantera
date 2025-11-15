package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;

public class Duck extends Herbivore {
    public Duck(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.DUCK.getIcon();
    }
}
