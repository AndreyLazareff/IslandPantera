package com.javarush.island.lazarev.entity.organisms.animals.predators;

import com.javarush.island.lazarev.config.Settings;
import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.map.Cell;
import com.javarush.island.lazarev.entity.organisms.Organizm;
import com.javarush.island.lazarev.entity.organisms.animals.Animal;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {
    public Predator(Species species) { super(species); }

    @Override
    public void eat() {
        if (!isAlive() || gameMap == null) return;
        Cell cell = gameMap.getGrid()[x][y];
        if (cell == null) return;
        List<Organizm> snapshot = cell.getOrganismsSnapshot();
        for (Organizm prey : snapshot) {
            if (prey == this || !prey.isAlive()) continue;
            Integer chance = species.getDiet().get(prey.getSpecies());
            if (chance == null) continue;
            if (ThreadLocalRandom.current().nextDouble() < (chance / 100.0) * 0.7) {
                prey.death();
                cell.removeOrganism(prey);
                increaseSaturation(prey.getSpecies().getWeight() * 0.5);
                return;
            }
        }
        decreaseSaturation(Settings.SATURATION_DECREASE);
    }
}

