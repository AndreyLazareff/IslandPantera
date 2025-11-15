package com.javarush.island.lazarev.entity.organisms.animals;

import com.javarush.island.lazarev.config.Settings;
import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.organisms.Organizm;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organizm {
    public double saturation;
    public double maxSaturation;
    protected final int maxSpeed;
    protected double weight;

    protected Animal(Species species) {
        super(species);
        this.maxSaturation = species.getMaxSaturation();
        this.saturation = maxSaturation * 0.6;
        this.maxSpeed = species.getMaxSpeed();
        this.weight = species.getWeight();
    }

    @Override
    public void move() {
        if (!isAlive() || gameMap == null) return;
        if (ThreadLocalRandom.current().nextDouble() >= Settings.MOVE_CHANCE) {
            decreaseSaturation(Settings.SATURATION_DECREASE * 0.1);
            return;
        }

        int dx = ThreadLocalRandom.current().nextInt(-maxSpeed, maxSpeed + 1);
        int dy = ThreadLocalRandom.current().nextInt(-maxSpeed, maxSpeed + 1);

        int toX = Math.max(0, Math.min(gameMap.getWidth() - 1, x + dx));
        int toY = Math.max(0, Math.min(gameMap.getHeight() - 1, y + dy));

        gameMap.moveOrganism(this, toX, toY);
        decreaseSaturation(Settings.SATURATION_DECREASE);
    }

    public void starveStep(double hungerFactor) {
        saturation -= hungerFactor;
        if (saturation < 0) saturation = 0;
        double loss = hungerFactor * 0.5;
        weight -= loss;
        if (weight <= 0 || saturation <= 0) {
            death();
        }
    }

    public void decreaseSaturation(double v) {
        saturation -= v;
        weight -= v * 0.2;
        if (saturation <= 0 || weight <= 0) death();
    }

    public void increaseSaturation(double v) {
        saturation = Math.min(maxSaturation, saturation + v);
        weight = Math.min(species.getWeight(), weight + v * 0.2);
    }

    @Override
    public String getSymbol() {
        return isAlive() ? species.getIcon() : "";
    }
}



