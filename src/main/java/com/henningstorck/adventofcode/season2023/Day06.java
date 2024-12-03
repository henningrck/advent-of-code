package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.utils.ParserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06 implements Day<String, Integer> {
    public static final Pattern RACE_TABLE_PATTERN = Pattern.compile("Time: ([0-9 ]+)\nDistance: ([0-9 ]+)\n");

    public record Race(long time, long distance) {
    }

    @Override
    public String cookInput(String rawInput) {
        return rawInput;
    }

    @Override
    public Integer solvePart1(String input) {
        return parseAsMultipleRaces(input).stream()
                .map(this::countStrategiesToWin)
                .reduce(1, (a, b) -> a * b);
    }

    @Override
    public Integer solvePart2(String input) {
        return countStrategiesToWin(parseAsSingleRace(input));
    }

    private List<Race> parseAsMultipleRaces(String rawInput) {
        List<Race> races = new ArrayList<>();
        Matcher matcher = RACE_TABLE_PATTERN.matcher(rawInput);
        matcher.matches();
        int[] times = ParserUtils.parseIntArray(matcher.group(1));
        int[] distances = ParserUtils.parseIntArray(matcher.group(2));

        for (int i = 0; i < times.length; i++) {
            races.add(new Race(times[i], distances[i]));
        }

        return races;
    }

    private Race parseAsSingleRace(String rawInput) {
        Matcher matcher = RACE_TABLE_PATTERN.matcher(rawInput);
        matcher.matches();
        long time = Long.parseLong(matcher.group(1).replaceAll(" ", ""));
        long distance = Long.parseLong(matcher.group(2).replaceAll(" ", ""));
        return new Race(time, distance);
    }

    private int countStrategiesToWin(Race race) {
        int result = 0;

        for (int charge = 1; charge < race.time(); charge++) {
            if (calculateDistance(charge, race.time()) > race.distance()) {
                result++;
            }
        }

        return result;
    }

    private long calculateDistance(long charge, long time) {
        return charge * (time - charge);
    }
}
