package com.javarush.island.lazarev.entity.map;

import com.javarush.island.lazarev.entity.organisms.Organizm;

import java.util.function.Consumer;

public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Cell[width][height];
        for (int x = 0; x < width; x++) for (int y = 0; y < height; y++) grid[x][y] = new Cell();
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveOrganism(Organizm org, int toX, int toY) {
        if (org == null) return;
        int fromX = org.getX();
        int fromY = org.getY();

        int nx = Math.max(0, Math.min(width - 1, toX));
        int ny = Math.max(0, Math.min(height - 1, toY));

        if (nx == fromX && ny == fromY) return;

        Cell a = grid[fromX][fromY];
        Cell b = grid[nx][ny];

        Cell first = a, second = b;
        if (System.identityHashCode(first) > System.identityHashCode(second)) {
            Cell tmp = first;
            first = second;
            second = tmp;
        }

        first.lock();
        second.lock();
        try {
            if (a.getOrganismsSnapshot().contains(org)) a.removeOrganism(org);
            org.setPosition(nx, ny, this);
            b.addOrganism(org);
        } finally {
            second.unlock();
            first.unlock();
        }
    }

    public void forEachCell(Consumer<Cell> action) {
        for (Cell[] row : grid) for (Cell c : row) action.accept(c);
    }

    public void forEachOrganism(Consumer<Organizm> action) {
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                for (Organizm o : grid[x][y].getOrganismsSnapshot()) action.accept(o);
            }
    }

    public void cleanupDead() {
        forEachCell(Cell::removeDead);
    }

    public void growGrass(java.util.function.Supplier<Organizm> grassSupplier, int percent) {
        java.util.concurrent.ThreadLocalRandom rnd = java.util.concurrent.ThreadLocalRandom.current();
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                if (rnd.nextInt(100) < percent) {
                    Organizm g = grassSupplier.get();
                    g.setPosition(x, y, this);
                    grid[x][y].addOrganism(g);
                }
            }
    }
}

