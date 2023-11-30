package com.henningrck.adventofcode.common.input;

import java.util.Arrays;

public final class InputParser {
    private InputParser() {
    }

    public static String[] toLines(String rawInput) {
        return rawInput.split("\n");
    }

    public static char[][] toCharArray(String rawInput) {
        return Arrays.stream(rawInput.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static String[] toChunks(String rawInput) {
        return Arrays.stream(rawInput.split("\n\n"))
                .map(String::trim)
                .toArray(String[]::new);
    }
}
