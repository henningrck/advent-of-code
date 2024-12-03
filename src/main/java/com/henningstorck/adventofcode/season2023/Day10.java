package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class Day10 implements Day<char[][], Integer> {
    private static final char NOTHING = '.';
    private static final char START = 'S';
    private static final char TOP_LEFT = 'F';
    private static final char TOP_RIGHT = '7';
    private static final char BOTTOM_LEFT = 'L';
    private static final char BOTTOM_RIGHT = 'J';
    private static final char HORIZONTAL = '-';
    private static final char VERTICAL = '|';

    private static final char[] LEFT_CONNECTING = new char[]{TOP_RIGHT, BOTTOM_RIGHT, HORIZONTAL};
    private static final char[] RIGHT_CONNECTING = new char[]{TOP_LEFT, BOTTOM_LEFT, HORIZONTAL};
    private static final char[] TOP_CONNECTING = new char[]{BOTTOM_LEFT, BOTTOM_RIGHT, VERTICAL};
    private static final char[] BOTTOM_CONNECTING = new char[]{TOP_LEFT, TOP_RIGHT, VERTICAL};

    public record LoopSegment(int x, int y) {
        public LoopSegment add(int x, int y) {
            return new LoopSegment(this.x + x, this.y + y);
        }
    }

    @Override
    public char[][] cookInput(String rawInput) {
        return InputParser.splitChars(rawInput);
    }

    @Override
    public Integer solvePart1(char[][] input) {
        List<LoopSegment> loopSegments = walkLoop(input);
        return loopSegments.size() / 2;
    }

    @Override
    public Integer solvePart2(char[][] input) {
        List<LoopSegment> loopSegments = walkLoop(input);
        int result = 0;

        for (int y = 0; y < input.length; y++) {
            boolean inside = false;
            boolean isLine = false;
            boolean fromTop = false;

            for (int x = 0; x < input[y].length; x++) {
                LoopSegment loopSegment = new LoopSegment(x, y);
                char current = getCharSafe(input, loopSegment);

                if (!loopSegments.contains(loopSegment)) {
                    current = NOTHING;
                }

                switch (current) {
                    case BOTTOM_LEFT, TOP_LEFT -> {
                        isLine = true;
                        fromTop = current == BOTTOM_LEFT;
                    }
                    case BOTTOM_RIGHT, TOP_RIGHT -> {
                        isLine = false;
                        if ((fromTop && current == TOP_RIGHT) || (!fromTop && current == BOTTOM_RIGHT)) {
                            inside = !inside;
                        }
                    }
                    case VERTICAL -> inside = !inside;
                    case NOTHING -> {
                        if (!isLine && inside) {
                            result++;
                        }
                    }
                }
            }
        }

        return result;
    }

    private List<LoopSegment> walkLoop(char[][] input) {
        LoopSegment start = findStart(input);
        LoopSegment previous = start;
        LoopSegment current = start;
        List<LoopSegment> loopSegments = new ArrayList<>();

        do {
            LoopSegment[] neighbours = getNeighbours(input, current);
            LoopSegment neighbour = neighbours[0];

            if (neighbour.equals(previous)) {
                neighbour = neighbours[1];
            }

            loopSegments.add(neighbour);
            previous = current;
            current = neighbour;
        } while (!current.equals(start));

        return loopSegments;
    }

    private LoopSegment findStart(char[][] input) {
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) {
                if (input[y][x] == START) {
                    return new LoopSegment(x, y);
                }
            }
        }

        throw new IllegalStateException();
    }

    private LoopSegment[] getNeighbours(char[][] input, LoopSegment loopSegment) {
        return switch (getCharSafe(input, loopSegment)) {
            case TOP_LEFT -> new LoopSegment[]{loopSegment.add(1, 0), loopSegment.add(0, 1)};
            case TOP_RIGHT -> new LoopSegment[]{loopSegment.add(-1, 0), loopSegment.add(0, 1)};
            case BOTTOM_LEFT -> new LoopSegment[]{loopSegment.add(1, 0), loopSegment.add(0, -1)};
            case BOTTOM_RIGHT -> new LoopSegment[]{loopSegment.add(-1, 0), loopSegment.add(0, -1)};
            case HORIZONTAL -> new LoopSegment[]{loopSegment.add(-1, 0), loopSegment.add(1, 0)};
            case VERTICAL -> new LoopSegment[]{loopSegment.add(0, -1), loopSegment.add(0, 1)};
            default -> throw new IllegalStateException();
        };
    }

    private char getCharSafe(char[][] input, LoopSegment loopSegment) {
        if (loopSegment.y() < 0 || loopSegment.y() >= input.length) {
            return NOTHING;
        }

        if (loopSegment.x() < 0 || loopSegment.x() >= input[loopSegment.y()].length) {
            return NOTHING;
        }

        char value = input[loopSegment.y()][loopSegment.x()];

        if (value == START) {
            return replaceStart(input, loopSegment);
        }

        return value;
    }

    private char replaceStart(char[][] input, LoopSegment start) {
        char left = getCharSafe(input, start.add(-1, 0));
        char right = getCharSafe(input, start.add(1, 0));
        char top = getCharSafe(input, start.add(0, -1));
        char bottom = getCharSafe(input, start.add(0, 1));

        if (ArrayUtils.contains(LEFT_CONNECTING, right) && ArrayUtils.contains(TOP_CONNECTING, bottom)) {
            return TOP_LEFT;
        } else if (ArrayUtils.contains(RIGHT_CONNECTING, left) && ArrayUtils.contains(TOP_CONNECTING, bottom)) {
            return TOP_RIGHT;
        } else if (ArrayUtils.contains(LEFT_CONNECTING, right) && ArrayUtils.contains(BOTTOM_CONNECTING, top)) {
            return BOTTOM_LEFT;
        } else if (ArrayUtils.contains(RIGHT_CONNECTING, left) && ArrayUtils.contains(BOTTOM_CONNECTING, top)) {
            return BOTTOM_RIGHT;
        } else if (ArrayUtils.contains(LEFT_CONNECTING, right) && ArrayUtils.contains(RIGHT_CONNECTING, left)) {
            return HORIZONTAL;
        } else if (ArrayUtils.contains(TOP_CONNECTING, bottom) && ArrayUtils.contains(BOTTOM_CONNECTING, top)) {
            return VERTICAL;
        }

        throw new IllegalStateException();
    }
}
