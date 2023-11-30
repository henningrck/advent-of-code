package com.henningrck.adventofcode.common.input;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class InputReader {
    public static String readRawInput(int season, int day) {
        return readRawInput(season, day, "");
    }

    public static String readRawInput(int season, int day, String suffix) {
        try {
            String fileName = String.format("/inputs/season%04d/day%02d%s.txt", season, day, suffix);
            String rawInput = IOUtils.resourceToString(fileName, StandardCharsets.UTF_8);
            rawInput = rawInput.replaceAll("\\r\\n", "\n");
            rawInput = rawInput.replaceAll("\\r", "\n");
            return rawInput;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
