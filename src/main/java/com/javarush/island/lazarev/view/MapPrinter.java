package com.javarush.island.lazarev.view;

import com.javarush.island.lazarev.entity.map.Cell;
import com.javarush.island.lazarev.entity.map.GameMap;
import com.javarush.island.lazarev.entity.organisms.Organizm;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapPrinter {

    private static final int CELL_SIZE = 8;

    public static void print(GameMap map) {
        int width = map.getWidth();
        int height = map.getHeight();

        String horizontalBorder = "+" + "-".repeat(CELL_SIZE);
        for (int x = 0; x < width; x++) System.out.print(horizontalBorder);
        System.out.println("+");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell c = map.getGrid()[x][y];
                String content = getCellContent(c);
                System.out.print("|" + padCenter(content, CELL_SIZE));
            }
            System.out.println("|");
            for (int x = 0; x < width; x++) System.out.print(horizontalBorder);
            System.out.println("+");
        }
    }

    private static String getCellContent(Cell cell) {
        List<Organizm> organisms = cell.getOrganismsSnapshot();

        Map<String, Long> counts = organisms.stream()
                .filter(Organizm::isAlive)
                .collect(Collectors.groupingBy(Organizm::getSymbol, Collectors.counting()));

        List<String> top = counts.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (counts.containsKey("🌿")) top.add("🌿");

        if (top.isEmpty()) return "·";

        return String.join("", top);
    }

    private static String padCenter(String s, int width) {
        int padding = width - s.length();
        int padLeft = padding / 2;
        int padRight = padding - padLeft;
        return " ".repeat(padLeft) + s + " ".repeat(padRight);
    }
}



