package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;
import com.henningstorck.adventofcode.common.utils.ParserUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 implements Day<Day04.Card[], Integer> {
    public static final Pattern CARD_PATTERN = Pattern.compile("Card [0-9 ]+: ([0-9 ]+) \\| ([0-9 ]+)");

    public record Card(int[] winningNumbers, int[] numbersYouHave, int copies) {
        public Card addCopies(int copiesToAdd) {
            return new Card(winningNumbers, numbersYouHave, copies + copiesToAdd);
        }
    }

    @Override
    public Card[] cookInput(String rawInput) {
        return InputParser.splitLines(rawInput).stream()
                .map(this::parseCard)
                .toArray(Card[]::new);
    }

    private Card parseCard(String line) {
        Matcher matcher = CARD_PATTERN.matcher(line);

        if (!matcher.matches()) {
            return null;
        }

        int[] winningNumbers = ParserUtils.parseIntArray(matcher.group(1));
        int[] numbersYouHave = ParserUtils.parseIntArray(matcher.group(2));
        return new Card(winningNumbers, numbersYouHave, 1);
    }

    @Override
    public Integer solvePart1(Card[] input) {
        return Arrays.stream(input)
                .map(this::getMatches)
                .mapToInt(matches -> (int) Math.pow(2, matches - 1))
                .sum();
    }

    @Override
    public Integer solvePart2(Card[] input) {
        for (int i = 0; i < input.length; i++) {
            Card card = input[i];
            int matches = getMatches(card);

            for (int j = i + 1; j <= i + matches; j++) {
                input[j] = input[j].addCopies(card.copies);
            }
        }

        return Arrays.stream(input)
                .mapToInt(card -> card.copies)
                .sum();
    }

    private int getMatches(Card card) {
        return (int) Arrays.stream(card.numbersYouHave)
                .filter(numberYouHave -> org.apache.commons.lang3.ArrayUtils.contains(card.winningNumbers, numberYouHave))
                .count();
    }
}
