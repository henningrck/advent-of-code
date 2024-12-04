package com.henningstorck.adventofcode.season2024;

import com.henningstorck.adventofcode.common.day.Day;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day<String, Integer> {
    public static final Pattern MUL_PATTERN = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
    public static final Pattern MUL_PATTERN_EXTENDED = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\))");

    @Override
    public String cookInput(String rawInput) {
        return rawInput;
    }

    @Override
    public Integer solvePart1(String input) {
        int result = 0;
        Matcher matcher = MUL_PATTERN.matcher(input);

        while (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            result += a * b;
        }

        return result;
    }

    @Override
    public Integer solvePart2(String input) {
        int result = 0;
        boolean enabled = true;
        Matcher matcher = MUL_PATTERN_EXTENDED.matcher(input);

        while (matcher.find()) {
            if (matcher.group(1).equals("do()")) {
                enabled = true;
            } else if (matcher.group(1).equals("don't()")) {
                enabled = false;
            } else if (enabled) {
                int a = Integer.parseInt(matcher.group(2));
                int b = Integer.parseInt(matcher.group(3));
                result += a * b;
            }
        }

        return result;
    }
}
