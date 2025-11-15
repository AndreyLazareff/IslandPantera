package com.javarush.island.lazarev.Factory;

import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.organisms.Organizm;
import com.javarush.island.lazarev.entity.organisms.animals.herbivores.*;
import com.javarush.island.lazarev.entity.organisms.animals.predators.*;
import com.javarush.island.lazarev.entity.organisms.plant.Grass;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class OrganismFactory {
    private static final Map<Species, Supplier<Organizm>> registry = new HashMap<>();

    static {
        registry.put(Species.WOLF, () -> new Wolf(Species.WOLF));
        registry.put(Species.SNAKE, () -> new Snake(Species.SNAKE));
        registry.put(Species.FOX, () -> new Fox(Species.FOX));
        registry.put(Species.BEAR, () -> new Bear(Species.BEAR));
        registry.put(Species.EAGLE, () -> new Eagle(Species.EAGLE));
        registry.put(Species.HORSE, () -> new Horse(Species.HORSE));
        registry.put(Species.DEER, () -> new Deer(Species.DEER));
        registry.put(Species.RABBIT, () -> new Rabbit(Species.RABBIT));
        registry.put(Species.MOUSE, () -> new Mouse(Species.MOUSE));
        registry.put(Species.GOAT, () -> new Goat(Species.GOAT));
        registry.put(Species.SHEEP, () -> new Sheep(Species.SHEEP));
        registry.put(Species.BOAR, () -> new Boar(Species.BOAR));
        registry.put(Species.BUFFALO, () -> new Buffalo(Species.BUFFALO));
        registry.put(Species.DUCK, () -> new Duck(Species.DUCK));
        registry.put(Species.CATERPILLAR, () -> new Caterpillar(Species.CATERPILLAR));
        registry.put(Species.GRASS, () -> new Grass());
    }

    public static Organizm create(Species species) {
        Supplier<Organizm> s = registry.get(species);
        if (s == null) throw new IllegalArgumentException("Unknown species: " + species);
        return s.get();
    }

    public static Organizm createRandom() {
        Species[] vals = registry.keySet().stream()
                .filter(s -> s != Species.GRASS)
                .toArray(Species[]::new);
        Species pick = vals[ThreadLocalRandom.current().nextInt(vals.length)];
        return create(pick);
    }
}
