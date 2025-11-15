package com.javarush.island.lazarev.entity.map;

import com.javarush.island.lazarev.config.Species;
import com.javarush.island.lazarev.entity.organisms.Organizm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    private final List<Organizm> organisms = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final ConcurrentHashMap<Species, Boolean> reproduced = new ConcurrentHashMap<>();

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public void addOrganism(Organizm o) {
        if (o == null) return;
        lock();
        try {
            organisms.add(o);
        } finally {
            unlock();
        }
    }

    public void removeOrganism(Organizm o) {
        if (o == null) return;
        lock();
        try {
            organisms.remove(o);
        } finally {
            unlock();
        }
    }

    public List<Organizm> getOrganismsSnapshot() {
        lock();
        try {
            return new ArrayList<>(organisms);
        } finally {
            unlock();
        }
    }

    public boolean tryMarkReproduced(Species species) {
        if (species == null) return false;
        return reproduced.putIfAbsent(species, Boolean.TRUE) == null;
    }

    public void resetReproducedFlags() {
        lock();
        try {
            reproduced.clear();
        } finally {
            unlock();
        }
    }

    public void cleanupDead() {
        lock();
        try {
            organisms.removeIf(o -> o == null || !o.isAlive());
        } finally {
            unlock();
        }
    }

    public void removeDead() {
        cleanupDead();
    }

    @Override
    public String toString() {
        lock();
        try {
            StringBuilder sb = new StringBuilder();
            for (Organizm o : organisms) if (o.isAlive()) sb.append(o.getSymbol());
            return sb.toString();
        } finally {
            unlock();
        }
    }
}



