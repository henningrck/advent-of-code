package com.henningstorck.adventofcode.common.input;

import java.util.Arrays;
import java.util.List;

public final class InputParser {
    private InputParser() {
    }

    public static List<String> splitLines(String rawInput) {
        return Arrays.stream(rawInput.split("\n")).toList();
    }

    public static char[][] splitChars(String rawInput) {
        return Arrays.stream(rawInput.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static List<String> splitChunks(String rawInput) {
        return Arrays.stream(rawInput.split("\n\n"))
                .map(String::trim)
                .toList();
    }

    public static List<String> splitWhitespace(String rawInput) {
        return Arrays.stream(rawInput.split("( )+")).toList();
    }
}
