package com.javarush.island.lazarev.config;

import java.util.HashMap;
import java.util.Map;

//Характеристики хищников
public enum Species {
    WOLF("🐺",  500, 3, 3, 80),
    SNAKE("🐍",  150, 3, 1, 30),
    FOX("🦊",  80, 3, 2, 20),
    BEAR("🐻",  500, 5, 2, 80),
    EAGLE("🦅",  60, 2, 3, 10),

    //Характеристики травоядных
    HORSE("🐎",  4000, 2, 4, 600),
    DEER("🦌",  3000, 2, 4, 500),
    RABBIT("🐇",  30, 10, 2, 7),
    MOUSE("🐁",  3, 10, 1, 1),
    GOAT("🐐",  840, 10, 3, 140),
    SHEEP("🐑",  980, 10, 3, 210),
    BOAR("🐗",  4000, 5, 2, 500),
    BUFFALO("🐃",  3500, 2, 3, 500),
    DUCK("🦆",  20, 10, 4, 3),
    CATERPILLAR("🐛",  1, 20, 1, 1),

    //Характеристика травы
    GRASS("🌿",  20, 10, 0, 0);

    private final String icon;
    private final double weight;
    private final double maxOnCell;
    private final int maxSpeed;
    private final double maxSaturation;

    private final Map<Species, Integer> diet = new HashMap<>();

    Species(String icon, double weight, double maxOnCell, int maxSpeed, double maxSaturation) {
        this.icon = icon;
        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.maxSpeed = maxSpeed;
        this.maxSaturation = maxSaturation;
    }
    static {
        initDiets();
    }
    private static void initDiets(){
        Species.WOLF.addPreyChance(Species.WOLF, 0);
        Species.WOLF.addPreyChance(Species.SNAKE, 0);
        Species.WOLF.addPreyChance(Species.FOX, 0);
        Species.WOLF.addPreyChance(Species.BEAR, 0);
        Species.WOLF.addPreyChance(Species.EAGLE, 0);
        Species.WOLF.addPreyChance(Species.HORSE, 10);
        Species.WOLF.addPreyChance(Species.DEER, 15);
        Species.WOLF.addPreyChance(Species.RABBIT, 60);
        Species.WOLF.addPreyChance(Species.MOUSE, 80);
        Species.WOLF.addPreyChance(Species.GOAT, 60);
        Species.WOLF.addPreyChance(Species.SHEEP, 70);
        Species.WOLF.addPreyChance(Species.BOAR, 15);
        Species.WOLF.addPreyChance(Species.BUFFALO, 10);
        Species.WOLF.addPreyChance(Species.DUCK, 40);
        Species.WOLF.addPreyChance(Species.CATERPILLAR, 0);
        Species.WOLF.addPreyChance(Species.GRASS, 0);

        Species.SNAKE.addPreyChance(Species.WOLF, 0);
        Species.SNAKE.addPreyChance(Species.SNAKE, 0);
        Species.SNAKE.addPreyChance(Species.FOX, 15);
        Species.SNAKE.addPreyChance(Species.BEAR, 0);
        Species.SNAKE.addPreyChance(Species.EAGLE, 0);
        Species.SNAKE.addPreyChance(Species.HORSE, 0);
        Species.SNAKE.addPreyChance(Species.DEER, 0);
        Species.SNAKE.addPreyChance(Species.RABBIT, 20);
        Species.SNAKE.addPreyChance(Species.MOUSE, 40);
        Species.SNAKE.addPreyChance(Species.GOAT, 0);
        Species.SNAKE.addPreyChance(Species.SHEEP, 0);
        Species.SNAKE.addPreyChance(Species.BOAR, 0);
        Species.SNAKE.addPreyChance(Species.BUFFALO, 0);
        Species.SNAKE.addPreyChance(Species.DUCK, 10);
        Species.SNAKE.addPreyChance(Species.CATERPILLAR, 0);
        Species.SNAKE.addPreyChance(Species.GRASS, 0);

        Species.FOX.addPreyChance(Species.WOLF, 0);
        Species.FOX.addPreyChance(Species.SNAKE, 0);
        Species.FOX.addPreyChance(Species.FOX, 0);
        Species.FOX.addPreyChance(Species.BEAR, 0);
        Species.FOX.addPreyChance(Species.EAGLE, 0);
        Species.FOX.addPreyChance(Species.HORSE, 0);
        Species.FOX.addPreyChance(Species.DEER, 0);
        Species.FOX.addPreyChance(Species.RABBIT, 70);
        Species.FOX.addPreyChance(Species.MOUSE, 90);
        Species.FOX.addPreyChance(Species.GOAT, 0);
        Species.FOX.addPreyChance(Species.SHEEP, 0);
        Species.FOX.addPreyChance(Species.BOAR, 0);
        Species.FOX.addPreyChance(Species.BUFFALO, 0);
        Species.FOX.addPreyChance(Species.DUCK, 60);
        Species.FOX.addPreyChance(Species.CATERPILLAR, 40);
        Species.FOX.addPreyChance(Species.GRASS, 0);

        Species.BEAR.addPreyChance(Species.WOLF, 0);
        Species.BEAR.addPreyChance(Species.SNAKE, 80);
        Species.BEAR.addPreyChance(Species.FOX, 0);
        Species.BEAR.addPreyChance(Species.BEAR, 0);
        Species.BEAR.addPreyChance(Species.EAGLE, 0);
        Species.BEAR.addPreyChance(Species.HORSE, 40);
        Species.BEAR.addPreyChance(Species.DEER, 80);
        Species.BEAR.addPreyChance(Species.RABBIT, 90);
        Species.BEAR.addPreyChance(Species.MOUSE, 70);
        Species.BEAR.addPreyChance(Species.GOAT, 70);
        Species.BEAR.addPreyChance(Species.SHEEP, 70);
        Species.BEAR.addPreyChance(Species.BOAR, 50);
        Species.BEAR.addPreyChance(Species.BUFFALO, 20);
        Species.BEAR.addPreyChance(Species.DUCK, 10);
        Species.BEAR.addPreyChance(Species.CATERPILLAR, 0);
        Species.BEAR.addPreyChance(Species.GRASS, 0);

        Species.EAGLE.addPreyChance(Species.WOLF, 0);
        Species.EAGLE.addPreyChance(Species.SNAKE, 0);
        Species.EAGLE.addPreyChance(Species.FOX, 10);
        Species.EAGLE.addPreyChance(Species.BEAR, 0);
        Species.EAGLE.addPreyChance(Species.EAGLE, 0);
        Species.EAGLE.addPreyChance(Species.HORSE, 0);
        Species.EAGLE.addPreyChance(Species.DEER, 0);
        Species.EAGLE.addPreyChance(Species.RABBIT, 90);
        Species.EAGLE.addPreyChance(Species.MOUSE, 90);
        Species.EAGLE.addPreyChance(Species.GOAT, 0);
        Species.EAGLE.addPreyChance(Species.SHEEP, 0);
        Species.EAGLE.addPreyChance(Species.BOAR, 0);
        Species.EAGLE.addPreyChance(Species.BUFFALO, 0);
        Species.EAGLE.addPreyChance(Species.DUCK, 80);
        Species.EAGLE.addPreyChance(Species.CATERPILLAR, 0);
        Species.EAGLE.addPreyChance(Species.GRASS, 0);

        Species.HORSE.addPreyChance(Species.GRASS, 100);
        Species.DEER.addPreyChance(Species.GRASS, 100);
        Species.RABBIT.addPreyChance(Species.GRASS, 100);
        Species.MOUSE.addPreyChance(Species.GRASS, 100);
        Species.MOUSE.addPreyChance(Species.CATERPILLAR, 90);
        Species.GOAT.addPreyChance(Species.GRASS, 100);
        Species.SHEEP.addPreyChance(Species.GRASS, 100);
        Species.BOAR.addPreyChance(Species.GRASS, 100);
        Species.BOAR.addPreyChance(Species.MOUSE, 50);
        Species.BOAR.addPreyChance(Species.CATERPILLAR, 90);
        Species.BUFFALO.addPreyChance(Species.GRASS, 100);
        Species.DUCK.addPreyChance(Species.GRASS, 100);
        Species.DUCK.addPreyChance(Species.CATERPILLAR, 90);
        Species.CATERPILLAR.addPreyChance(Species.GRASS, 100);
    }

    public String getIcon() {
        return icon;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxSaturation() {
        return maxSaturation;
    }

    public double getMaxOnCell() {
        return maxOnCell;
    }

    public Map<Species, Integer> getDiet() {
        return diet;
    }

    public void addPreyChance(Species prey, int chance) {
        diet.put(prey, chance);
    }
}

