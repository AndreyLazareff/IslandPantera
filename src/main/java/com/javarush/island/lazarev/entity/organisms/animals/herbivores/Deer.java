package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;

public class Deer extends Herbivore {
    public Deer(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.DEER.getIcon();
    }
}
