package com.javarush.island.lazarev.services;

import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.map.Cell;
import com.javarush.island.lazarev.entity.map.GameMap;
import com.javarush.island.lazarev.entity.organisms.Organizm;

import java.util.EnumMap;
import java.util.Map;

public class LoggerService {
    public static void logStatistics(GameMap map, int step) {
        Map<Species, Integer> counts = new EnumMap<>(Species.class);
        for (Species s : Species.values()) counts.put(s, 0);

        for (int x=0; x<map.getWidth(); x++) for (int y=0; y<map.getHeight(); y++) {
            Cell c = map.getGrid()[x][y];
            for (Organizm o : c.getOrganismsSnapshot()) {
                if (o.isAlive()) counts.computeIfPresent(o.getSpecies(), (k,v)->v+1);
            }
        }

        System.out.printf("📈 Шаг %d — статистика:%n", step);
        counts.forEach((sp,cnt) -> System.out.printf("%s %-10s: %d%n", sp.getIcon(), sp.name(), cnt));
    }
}

