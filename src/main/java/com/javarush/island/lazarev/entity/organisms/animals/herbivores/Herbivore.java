package com.javarush.island.lazarev.entity.organisms.animals.herbivores;

import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.map.Cell;
import com.javarush.island.lazarev.entity.organisms.Organizm;
import com.javarush.island.lazarev.entity.organisms.animals.Animal;
import com.javarush.island.lazarev.entity.organisms.plant.Grass;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(Species species) { super(species); }

    @Override
    public void eat() {
        if (!isAlive() || gameMap == null) return;
        Cell cell = gameMap.getGrid()[x][y];
        if (cell == null) return;
        List<Organizm> snapshot = cell.getOrganismsSnapshot();
        for (Organizm o : snapshot) {
            if (o == this || !o.isAlive()) continue;
            if (o instanceof Grass) {
                o.death();
                cell.removeOrganism(o);
                increaseSaturation(species.getMaxSaturation());
                return;
            }
        }
        decreaseSaturation(0.05);
    }
}

