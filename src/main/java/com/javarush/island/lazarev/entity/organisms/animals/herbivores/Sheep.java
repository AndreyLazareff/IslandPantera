package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;

public class Sheep extends Herbivore {
    public Sheep(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.SHEEP.getIcon();
    }
}
