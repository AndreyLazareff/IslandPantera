package com.javarush.island.lazarev.entity.organisms.animals.predators;

import com.javarush.island.lazarev.config.Species;

public class Snake extends Predator {
    public Snake(Species species) {
        super(species);
    }

    @Override
    public String getSymbol() {
        return Species.SNAKE.getIcon();
    }
}
