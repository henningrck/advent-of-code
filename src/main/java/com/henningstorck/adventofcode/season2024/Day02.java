package com.henningstorck.adventofcode.season2024;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day02 implements Day<List<Day02.Report>, Integer> {
    public static final Set<Integer> ALLOWED_INCREMENTS = Set.of(1, 2, 3);
    public static final Set<Integer> ALLOWED_DECREMENTS = Set.of(-1, -2, -3);

    public record Report(List<Integer> levels) {
    }

    @Override
    public List<Report> cookInput(String rawInput) {
        return InputParser.splitLines(rawInput).stream().map(line -> {
            List<Integer> levels = InputParser.splitWhitespace(line).stream()
                    .map(Integer::parseInt)
                    .toList();

            return new Report(levels);
        }).toList();
    }

    @Override
    public Integer solvePart1(List<Report> input) {
        return (int) input.stream()
                .filter(report -> areLevelsValid(report.levels(), false))
                .count();
    }

    @Override
    public Integer solvePart2(List<Report> input) {
        return (int) input.stream()
                .filter(report -> areLevelsValid(report.levels(), true) || areLevelsValid(report.levels().reversed(), true))
                .count();
    }

    private boolean areLevelsValid(List<Integer> levels, boolean withTolerance) {
        Integer lastLevel = null;
        Set<Integer> diffs = new HashSet<>();
        int badLevels = 0;

        for (int level : levels) {
            if (lastLevel != null) {
                diffs.add(level - lastLevel);
            }

            if (withTolerance && !diffs.isEmpty() && !areDiffsValid(diffs)) {
                badLevels++;

                if (badLevels <= 1) {
                    diffs.remove(level - lastLevel);
                    continue;
                }
            }

            lastLevel = level;
        }

        return areDiffsValid(diffs);
    }

    private boolean areDiffsValid(Set<Integer> diffs) {
        return ALLOWED_INCREMENTS.containsAll(diffs) || ALLOWED_DECREMENTS.containsAll(diffs);
    }
}
