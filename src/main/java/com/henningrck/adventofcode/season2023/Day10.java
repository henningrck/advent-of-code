package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.day.Day;
import com.henningrck.adventofcode.common.input.InputParser;
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
        return InputParser.toCharArray(rawInput);
    }

    @Override
    public Integer solvePart1(char[][] chars) {
        List<LoopSegment> loopSegments = walkLoop(chars);
        return loopSegments.size() / 2;
    }

    @Override
    public Integer solvePart2(char[][] chars) {
        return 0;
    }

    private List<LoopSegment> walkLoop(char[][] chars) {
        LoopSegment start = findStart(chars);
        LoopSegment previous = start;
        LoopSegment current = start;
        List<LoopSegment> loopSegments = new ArrayList<>();

        do {
            LoopSegment[] neighbours = getNeighbours(chars, current);
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

    private LoopSegment findStart(char[][] chars) {
        for (int y = 0; y < chars.length; y++) {
            for (int x = 0; x < chars[y].length; x++) {
                if (chars[y][x] == START) {
                    return new LoopSegment(x, y);
                }
            }
        }

        throw new IllegalStateException();
    }

    private LoopSegment[] getNeighbours(char[][] chars, LoopSegment loopSegment) {
        return switch (getCharSafe(chars, loopSegment)) {
            case TOP_LEFT -> new LoopSegment[]{loopSegment.add(1, 0), loopSegment.add(0, 1)};
            case TOP_RIGHT -> new LoopSegment[]{loopSegment.add(-1, 0), loopSegment.add(0, 1)};
            case BOTTOM_LEFT -> new LoopSegment[]{loopSegment.add(1, 0), loopSegment.add(0, -1)};
            case BOTTOM_RIGHT -> new LoopSegment[]{loopSegment.add(-1, 0), loopSegment.add(0, -1)};
            case HORIZONTAL -> new LoopSegment[]{loopSegment.add(-1, 0), loopSegment.add(1, 0)};
            case VERTICAL -> new LoopSegment[]{loopSegment.add(0, -1), loopSegment.add(0, 1)};
            default -> throw new IllegalStateException();
        };
    }

    private char getCharSafe(char[][] chars, LoopSegment loopSegment) {
        if (loopSegment.y() < 0 || loopSegment.y() >= chars.length) {
            return NOTHING;
        }

        if (loopSegment.x() < 0 || loopSegment.x() >= chars[loopSegment.y()].length) {
            return NOTHING;
        }

        char value = chars[loopSegment.y()][loopSegment.x()];

        if (value == START) {
            return replaceStart(chars, loopSegment);
        }

        return value;
    }

    private char replaceStart(char[][] chars, LoopSegment start) {
        char left = getCharSafe(chars, start.add(-1, 0));
        char right = getCharSafe(chars, start.add(1, 0));
        char top = getCharSafe(chars, start.add(0, -1));
        char bottom = getCharSafe(chars, start.add(0, 1));

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
