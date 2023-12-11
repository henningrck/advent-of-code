package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.day.Day;
import com.henningrck.adventofcode.common.input.InputParser;

import java.util.*;

public class Day03 implements Day<char[][], Integer> {
    public static final Position[] NEIGHBOUR_MODIFIERS = new Position[]{
            new Position(-1, -1),
            new Position(0, -1),
            new Position(1, -1),
            new Position(1, 0),
            new Position(1, 1),
            new Position(0, 1),
            new Position(-1, 1),
            new Position(-1, 0)
    };

    public record Position(int x, int y) {
        public Position add(int x, int y) {
            return new Position(this.x + x, this.y + y);
        }
    }

    public record EngineSchematic(List<Integer> partNumbers, Collection<Gear> gears) {
    }

    public record Gear(List<Integer> partNumbers) {
        public int gearRatio() {
            if (partNumbers.size() != 2) {
                return 0;
            }

            return partNumbers.get(0) * partNumbers.get(1);
        }
    }

    @Override
    public char[][] cookInput(String rawInput) {
        return InputParser.toCharArray(rawInput);
    }

    @Override
    public Integer solvePart1(char[][] chars) {
        EngineSchematic engineSchematic = parseEngineSchematic(chars);
        return engineSchematic.partNumbers().stream()
                .mapToInt(partNumber -> partNumber)
                .sum();
    }

    @Override
    public Integer solvePart2(char[][] chars) {
        EngineSchematic engineSchematic = parseEngineSchematic(chars);
        return engineSchematic.gears().stream()
                .mapToInt(Gear::gearRatio)
                .sum();
    }

    private EngineSchematic parseEngineSchematic(char[][] chars) {
        List<Integer> partNumbers = new ArrayList<>();
        Map<Position, Gear> gears = new HashMap<>();

        for (int y = 0; y < chars.length; y++) {
            int partNumber = 0;
            boolean validPartNumber = false;
            Set<Position> gearCandidates = new HashSet<>();

            for (int x = 0; x < chars[y].length; x++) {
                char currentChar = chars[y][x];
                int currentValue = Character.getNumericValue(currentChar);

                if (Character.isDigit(currentChar)) {
                    partNumber = extendPartNumber(partNumber, currentValue);

                    for (Position neighbourModifier : NEIGHBOUR_MODIFIERS) {
                        Position neighbourPosition = neighbourModifier.add(x, y);
                        char neighbour = getCharSafe(chars, neighbourPosition);

                        if (!Character.isDigit(neighbour) && neighbour != '.') {
                            validPartNumber = true;
                        }

                        if (getCharSafe(chars, neighbourPosition) == '*') {
                            gearCandidates.add(neighbourPosition);
                        }
                    }
                } else {
                    if (validPartNumber) {
                        partNumbers.add(partNumber);
                        addGears(gears, gearCandidates, partNumber);
                        gearCandidates.clear();
                    }

                    partNumber = 0;
                    validPartNumber = false;
                }
            }

            if (validPartNumber) {
                partNumbers.add(partNumber);
                addGears(gears, gearCandidates, partNumber);
                gearCandidates.clear();
            }
        }

        return new EngineSchematic(partNumbers, gears.values());
    }

    private int extendPartNumber(int partNumber, int extension) {
        partNumber *= 10;
        partNumber += extension;
        return partNumber;
    }

    private char getCharSafe(char[][] chars, Position position) {
        if (position.y() < 0 || position.y() >= chars.length) {
            return '.';
        }

        if (position.x() < 0 || position.x() >= chars[position.y()].length) {
            return '.';
        }

        return chars[position.y()][position.x()];
    }

    private void addGears(Map<Position, Gear> gears, Set<Position> gearCandidates, int partNumber) {
        for (Position gearCandidate : gearCandidates) {
            if (gears.containsKey(gearCandidate)) {
                gears.get(gearCandidate).partNumbers().add(partNumber);
            } else {
                gears.put(gearCandidate, new Gear(new ArrayList<>(List.of(partNumber))));
            }
        }
    }
}
