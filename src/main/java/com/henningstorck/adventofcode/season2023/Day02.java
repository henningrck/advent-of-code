package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 implements Day<Day02.Game[], Integer> {
    public record Game(int id, Hand[] hands) {
    }

    public record Hand(int red, int green, int blue) {
    }

    public static final Pattern GAME_PATTERN = Pattern.compile("^Game (\\d+): (.*)$");
    public static final Pattern COLOR_PATTERN = Pattern.compile("^([0-9]+) (red|green|blue)$");

    @Override
    public Game[] cookInput(String rawInput) {
        return InputParser.splitLines(rawInput).stream()
                .map(this::parseGame)
                .toArray(Game[]::new);
    }

    private Game parseGame(String input) {
        Matcher matcher = GAME_PATTERN.matcher(input);

        if (!matcher.matches()) {
            return null;
        }

        int id = Integer.parseInt(matcher.group(1));
        Hand[] hands = Arrays.stream(matcher.group(2).split("; "))
                .map(this::parseHand)
                .toArray(Hand[]::new);

        return new Game(id, hands);
    }

    private Hand parseHand(String input) {
        List<String> colors = Arrays.stream(input.split(", ")).toList();
        int red = 0;
        int green = 0;
        int blue = 0;

        for (String color : colors) {
            Matcher matcher = COLOR_PATTERN.matcher(color);

            if (!matcher.matches()) {
                break;
            }

            int value = Integer.parseInt(matcher.group(1));

            switch (matcher.group(2)) {
                case "red" -> red += value;
                case "green" -> green += value;
                case "blue" -> blue += value;
            }
        }

        return new Hand(red, green, blue);
    }

    @Override
    public Integer solvePart1(Game[] games) {
        int result = 0;

        for (Game game : games) {
            if (Arrays.stream(game.hands())
                    .noneMatch(hand -> hand.red() > 12 || hand.green() > 13 || hand.blue() > 14)) {
                result += game.id();
            }
        }

        return result;
    }

    @Override
    public Integer solvePart2(Game[] games) {
        int result = 0;

        for (Game game : games) {
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;

            for (Hand hand : game.hands()) {
                if (hand.red() > maxRed) {
                    maxRed = hand.red();
                }

                if (hand.green() > maxGreen) {
                    maxGreen = hand.green();
                }

                if (hand.blue() > maxBlue) {
                    maxBlue = hand.blue();
                }
            }

            result += maxRed * maxGreen * maxBlue;
        }

        return result;
    }
}
