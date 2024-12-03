package com.henningstorck.adventofcode.common.utils;

import java.util.Arrays;

public final class ParserUtils {
    private ParserUtils() {
    }

    public static int[] parseIntArray(String value) {
        return parseIntArray(value, " ");
    }

    public static int[] parseIntArray(String value, String regex) {
        return Arrays.stream(value.split(regex))
                .filter(number -> !number.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
