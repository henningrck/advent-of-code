package com.henningstorck.adventofcode.common.input;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class InputReader {
    public static String readRawInput(String fileName) {
        try {
            String rawInput = IOUtils.resourceToString(fileName, StandardCharsets.UTF_8);
            rawInput = rawInput.replaceAll("\\r\\n", "\n");
            rawInput = rawInput.replaceAll("\\r", "\n");
            return rawInput;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
