package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;

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
        return InputParser.splitChars(rawInput);
    }

    @Override
    public Integer solvePart1(char[][] input) {
        EngineSchematic engineSchematic = parseEngineSchematic(input);
        return engineSchematic.partNumbers().stream()
                .mapToInt(partNumber -> partNumber)
                .sum();
    }

    @Override
    public Integer solvePart2(char[][] input) {
        EngineSchematic engineSchematic = parseEngineSchematic(input);
        return engineSchematic.gears().stream()
                .mapToInt(Gear::gearRatio)
                .sum();
    }

    private EngineSchematic parseEngineSchematic(char[][] input) {
        List<Integer> partNumbers = new ArrayList<>();
        Map<Position, Gear> gears = new HashMap<>();

        for (int y = 0; y < input.length; y++) {
            int partNumber = 0;
            boolean validPartNumber = false;
            Set<Position> gearCandidates = new HashSet<>();

            for (int x = 0; x < input[y].length; x++) {
                char currentChar = input[y][x];
                int currentValue = Character.getNumericValue(currentChar);

                if (Character.isDigit(currentChar)) {
                    partNumber = extendPartNumber(partNumber, currentValue);

                    for (Position neighbourModifier : NEIGHBOUR_MODIFIERS) {
                        Position neighbourPosition = neighbourModifier.add(x, y);
                        char neighbour = getCharSafe(input, neighbourPosition);

                        if (!Character.isDigit(neighbour) && neighbour != '.') {
                            validPartNumber = true;
                        }

                        if (getCharSafe(input, neighbourPosition) == '*') {
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

    private char getCharSafe(char[][] input, Position position) {
        if (position.y() < 0 || position.y() >= input.length) {
            return '.';
        }

        if (position.x() < 0 || position.x() >= input[position.y()].length) {
            return '.';
        }

        return input[position.y()][position.x()];
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
