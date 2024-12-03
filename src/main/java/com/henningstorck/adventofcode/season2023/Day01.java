package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Day01 implements Day<List<String>, Integer> {
    public static final Map<String, String> REPLACEMENTS = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    @Override
    public List<String> cookInput(String rawInput) {
        return InputParser.splitLines(rawInput);
    }

    @Override
    public Integer solvePart1(List<String> input) {
        int result = 0;

        for (String line : input) {
            int[] numbers = line.chars()
                    .map(letter -> (char) letter)
                    .filter(Character::isDigit)
                    .map(Character::getNumericValue)
                    .toArray();

            if (numbers.length > 0) {
                int first = numbers[0];
                int last = numbers[numbers.length - 1];
                result += first * 10 + last;
            }
        }

        return result;
    }

    @Override
    public Integer solvePart2(List<String> input) {
        input = (input).stream()
                .map(this::applyReplacements).toList();

        return solvePart1(input);
    }

    private String applyReplacements(String oldLine) {
        StringBuilder newLine = new StringBuilder();

        // oneight -> 1on8eight
        while (!oldLine.isEmpty()) {
            Optional<Map.Entry<String, String>> replacement = findReplacement(oldLine);
            replacement.ifPresent(stringStringEntry -> newLine.append(stringStringEntry.getValue()));
            newLine.append(oldLine.charAt(0));
            oldLine = oldLine.substring(1);
        }

        return newLine.toString();
    }

    private Optional<Map.Entry<String, String>> findReplacement(String line) {
        return REPLACEMENTS.entrySet().stream()
                .filter(eachReplacement -> line.startsWith(eachReplacement.getKey()))
                .findAny();
    }
}
