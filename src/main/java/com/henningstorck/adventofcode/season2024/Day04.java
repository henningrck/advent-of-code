package com.henningstorck.adventofcode.season2024;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;

import java.util.List;

public class Day04 implements Day<char[][], Integer> {
    public static final List<Direction> DIRECTIONS_PART_1 = List.of(
            new Direction(-1, -1),
            new Direction(-1, 0),
            new Direction(-1, 1),

            new Direction(0, -1),
            new Direction(0, 1),

            new Direction(1, -1),
            new Direction(1, 0),
            new Direction(1, 1)
    );

    public static final List<Direction> DIRECTIONS_PART_2 = List.of(
            new Direction(1, 1),
            new Direction(1, -1)
    );

    public record Direction(int x, int y) {
    }

    @Override
    public char[][] cookInput(String rawInput) {
        return InputParser.splitChars(rawInput);
    }

    @Override
    public Integer solvePart1(char[][] input) {
        int matches = 0;

        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                char letter = input[y][x];

                if (letter == 'X') {
                    for (Direction direction : DIRECTIONS_PART_1) {
                        String word = walkDirection(input, x, y, direction, 0, 1, 2, 3);

                        if (word.equals("XMAS")) {
                            matches++;
                        }
                    }
                }
            }
        }

        return matches;
    }

    @Override
    public Integer solvePart2(char[][] input) {
        int matches = 0;

        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                char letter = input[y][x];

                if (letter == 'A') {
                    boolean match = true;

                    for (Direction direction : DIRECTIONS_PART_2) {
                        String word = walkDirection(input, x, y, direction, -1, 0, 1);

                        if (!word.equals("SAM") && !word.equals("MAS")) {
                            match = false;
                        }
                    }

                    if (match) {
                        matches++;
                    }
                }
            }
        }

        return matches;
    }

    private String walkDirection(char[][] input, int x, int y, Direction direction, int... multipliers) {
        StringBuilder word = new StringBuilder();

        for (int multiplier : multipliers) {
            int newX = x + direction.x() * multiplier;
            int newY = y + direction.y() * multiplier;

            if (newY >= 0 && newY < input.length && newX >= 0 && newX < input[0].length) {
                word.append(input[newY][newX]);
            }
        }

        return word.toString();
    }
}
