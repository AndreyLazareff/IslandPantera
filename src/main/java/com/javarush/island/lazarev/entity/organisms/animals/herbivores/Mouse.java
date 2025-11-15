package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;

public class Mouse extends Herbivore {
    public Mouse(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.MOUSE.getIcon();
    }
}
