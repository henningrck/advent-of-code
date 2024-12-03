package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;

import java.util.ArrayList;
import java.util.List;

public class Day11 implements Day<char[][], Long> {
    private final int factor;

    public Day11(int factor) {
        this.factor = factor;
    }

    public record Galaxy(long x, long y) {
        public Galaxy add(long x, long y) {
            return new Galaxy(this.x + x, this.y + y);
        }

        public long getManhattanDistance(Galaxy other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y);
        }
    }

    @Override
    public char[][] cookInput(String rawInput) {
        return InputParser.splitChars(rawInput);
    }

    @Override
    public Long solvePart1(char[][] input) {
        return solve(input, 2);
    }

    @Override
    public Long solvePart2(char[][] input) {
        return solve(input, factor);
    }

    private long solve(char[][] input, int factor) {
        List<Galaxy> galaxies = extractGalaxies(input);
        galaxies = new ArrayList<>(expandUniverse(galaxies, factor));
        long result = 0;

        for (Galaxy firstGalaxy : new ArrayList<>(galaxies)) {
            galaxies.remove(firstGalaxy);

            for (Galaxy secondGalaxy : galaxies) {
                result += firstGalaxy.getManhattanDistance(secondGalaxy);
            }
        }

        return result;
    }

    private List<Galaxy> extractGalaxies(char[][] input) {
        List<Galaxy> galaxies = new ArrayList<>();

        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) {
                if (input[y][x] == '#') {
                    galaxies.add(new Galaxy(x, y));
                }
            }
        }

        return galaxies;
    }

    private List<Galaxy> expandUniverse(List<Galaxy> galaxies, int factor) {
        long maxX = galaxies.stream().mapToLong(Galaxy::x).max().orElse(0);
        long maxY = galaxies.stream().mapToLong(Galaxy::y).max().orElse(0);
        long x = 0;
        long y = 0;

        while (x < maxX) {
            if (isAnyGalaxyInColumn(galaxies, x)) {
                x++;
                continue;
            }

            galaxies = moveGalaxiesHorizontally(galaxies, x, factor);
            x += factor;
            maxX += factor - 1;
        }

        while (y < maxY) {
            if (isAnyGalaxyInRow(galaxies, y)) {
                y++;
                continue;
            }

            galaxies = moveGalaxiesVertically(galaxies, y, factor);
            y += factor;
            maxY += factor - 1;
        }

        return galaxies;
    }

    private boolean isAnyGalaxyInColumn(List<Galaxy> galaxies, long x) {
        return galaxies.stream().anyMatch(galaxy -> galaxy.x() == x);
    }

    private boolean isAnyGalaxyInRow(List<Galaxy> galaxies, long y) {
        return galaxies.stream().anyMatch(galaxy -> galaxy.y() == y);
    }

    private List<Galaxy> moveGalaxiesHorizontally(List<Galaxy> galaxies, long x, int factor) {
        return galaxies.stream()
                .map(galaxy -> {
                    if (galaxy.x() > x) {
                        return galaxy.add(factor - 1, 0);
                    }

                    return galaxy;
                })
                .toList();
    }

    private List<Galaxy> moveGalaxiesVertically(List<Galaxy> galaxies, long y, int factor) {
        return galaxies.stream()
                .map(galaxy -> {
                    if (galaxy.y() > y) {
                        return galaxy.add(0, factor - 1);
                    }

                    return galaxy;
                })
                .toList();
    }
}
