package com.javarush.island.lazarev;

import com.javarush.island.lazarev.Factory.OrganismFactory;
import com.javarush.island.lazarev.config.Settings;
import com.javarush.island.lazarev.entity.map.Cell;
import com.javarush.island.lazarev.entity.map.GameMap;
import com.javarush.island.lazarev.entity.organisms.Organizm;
import com.javarush.island.lazarev.services.SimulationEngine;

import java.util.concurrent.ThreadLocalRandom;

public class Runner {
    public static void main(String[] args) {
        System.out.printf("🌴 Создаём остров %dx%d...%n", Settings.MAP_WIDTH, Settings.MAP_HEIGHT);
        GameMap map = new GameMap(Settings.MAP_WIDTH, Settings.MAP_HEIGHT);

        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        Cell[][] grid = map.getGrid();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                if (rnd.nextInt(100) < 100) {
                    Organizm org = OrganismFactory.createRandom();
                    org.setPosition(x, y, map);
                    grid[x][y].addOrganism(org);
                }
            }
        }
        System.out.println("🚀 Запуск симуляции...");
        SimulationEngine engine = new SimulationEngine(map, Settings.MAP_STEPS);
        long startTime = System.currentTimeMillis();
        engine.start();
        long duration = System.currentTimeMillis() - startTime;

        System.out.println("\n🏁 Симуляция завершена.");
        System.out.printf("Время работы: %.2f секунд%n", duration / 1000.0);
    }
}



