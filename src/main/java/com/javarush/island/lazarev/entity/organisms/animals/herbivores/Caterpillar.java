package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;

public class Caterpillar extends Herbivore {
    public Caterpillar(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.CATERPILLAR.getIcon();
    }
}
