package com.javarush.island.lazarev.services;

import com.javarush.island.lazarev.Factory.OrganismFactory;
import com.javarush.island.lazarev.config.Settings;
import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.map.Cell;
import com.javarush.island.lazarev.entity.map.GameMap;
import com.javarush.island.lazarev.entity.organisms.Organizm;
import com.javarush.island.lazarev.entity.organisms.animals.Animal;
import com.javarush.island.lazarev.entity.organisms.plant.Grass;
import com.javarush.island.lazarev.view.MapPrinter;

import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationEngine {
    private final GameMap map;
    private final int steps;
    private final Phaser phaser = new Phaser(1);
    private volatile boolean running = true;

    public SimulationEngine(GameMap map, int steps) {
        this.map = map;
        this.steps = steps;
    }

    public void start() {

        phaser.register();
        Thread t1 = new Thread(new MoveWorker(), "move");
        t1.setDaemon(true);
        t1.start();
        phaser.register();
        Thread t2 = new Thread(new EatWorker(), "eat");
        t2.setDaemon(true);
        t2.start();
        phaser.register();
        Thread t3 = new Thread(new HungerWorker(), "hunger");
        t3.setDaemon(true);
        t3.start();
        phaser.register();
        Thread t4 = new Thread(new ReproduceWorker(), "repro");
        t4.setDaemon(true);
        t4.start();

        for (int step = 1; step <= steps && running; step++) {
            System.out.println("\n=== Step " + step + " ===");
            for (int tx = 0; tx < map.getWidth(); tx++) {
                for (int ty = 0; ty < map.getHeight(); ty++) {
                    Cell c = map.getGrid()[tx][ty];
                    c.resetReproducedFlags();
                }
            }
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();

            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();

            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();

            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();

            map.growGrass(() -> {
                Grass g = (Grass) OrganismFactory.create(Species.GRASS);
                return g;
            }, 10);

            map.cleanupDead();
            MapPrinter.print(map);
            LoggerService.logStatistics(map, step);

            if (allAnimalsDead()) {
                System.out.println("\u2620" + "Все животные вымерли!" + "\u2620");
                running = false;
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {
            }
        }

        phaser.arriveAndDeregister();
    }

    private boolean allAnimalsDead() {
        for (int x = 0; x < map.getWidth(); x++)
            for (int y = 0; y < map.getHeight(); y++) {
                for (Organizm o : map.getGrid()[x][y].getOrganismsSnapshot()) {
                    if (o.isAlive() && o instanceof Animal) return false;
                }
            }
        return true;
    }

    private class MoveWorker implements Runnable {
        public void run() {
            while (phaser.getRegisteredParties() > 1) {
                phaser.arriveAndAwaitAdvance();
                map.forEachOrganism(o -> {
                    if (o instanceof Animal a && a.isAlive()) a.move();
                });
                phaser.arriveAndAwaitAdvance();
            }
        }
    }

    private class EatWorker implements Runnable {
        public void run() {
            while (phaser.getRegisteredParties() > 1) {
                phaser.arriveAndAwaitAdvance();
                map.forEachOrganism(o -> {
                    if (o instanceof Animal a && a.isAlive()) a.eat();
                });
                phaser.arriveAndAwaitAdvance();
            }
        }
    }

    private class HungerWorker implements Runnable {
        public void run() {
            while (phaser.getRegisteredParties() > 1) {
                phaser.arriveAndAwaitAdvance();
                map.forEachOrganism(o -> {
                    if (o instanceof Animal a) {
                        a.starveStep(0.02);
                        if (!a.isAlive()) {
                            int cx = a.getX(), cy = a.getY();
                            if (cx >= 0 && cy >= 0 && cx < map.getWidth() && cy < map.getHeight()) {
                                Cell cell = map.getGrid()[cx][cy];
                                cell.lock();
                                try {
                                    cell.removeOrganism(a);
                                } finally {
                                    cell.unlock();
                                }
                            }
                        }
                    }
                });
                map.cleanupDead();
                phaser.arriveAndAwaitAdvance();
            }
        }
    }

    private class ReproduceWorker implements Runnable {
        public void run() {
            while (phaser.getRegisteredParties() > 1) {
                phaser.arriveAndAwaitAdvance();

                for (int x = 0; x < map.getWidth(); x++)
                    for (int y = 0; y < map.getHeight(); y++) {
                        var c = map.getGrid()[x][y];
                        var snapshot = c.getOrganismsSnapshot();
                        var counts = new java.util.HashMap<Species, Integer>();
                        for (Organizm o : snapshot) if (o.isAlive()) counts.merge(o.getSpecies(), 1, Integer::sum);
                        for (var e : counts.entrySet()) {
                            Species sp = e.getKey();
                            int cnt = e.getValue();
                            if (cnt < 2) continue;
                            int maxOnCell = (int) Math.round(sp.getMaxOnCell());
                            if (cnt >= maxOnCell) continue;
                            Animal candidate = null;
                            for (Organizm o : snapshot) {
                                if (o.isAlive() && o.getSpecies() == sp && o instanceof Animal) {
                                    candidate = (Animal) o;
                                    break;
                                }
                            }
                            if (candidate == null) continue;
                            if (ThreadLocalRandom.current().nextDouble() >= Settings.REPRODUCTION_CHANCE) continue;
                            if (candidate.saturation < candidate.maxSaturation * 0.7) continue;
                            if (!c.tryMarkReproduced(sp)) continue;
                            try {
                                Organizm baby = OrganismFactory.create(sp);
                                baby.setPosition(x, y, map);
                                c.lock();
                                try {
                                    c.addOrganism(baby);
                                } finally {
                                    c.unlock();
                                }
                                candidate.decreaseSaturation(Settings.SATURATION_DECREASE * 2);
                                for (Organizm o : snapshot) {
                                    if (o != candidate && o.isAlive() && o.getSpecies() == sp && o instanceof Animal) {
                                        ((Animal) o).decreaseSaturation(Settings.SATURATION_DECREASE);
                                        break;
                                    }
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                phaser.arriveAndAwaitAdvance();
            }
        }
    }
}


