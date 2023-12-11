package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.day.Day;
import com.henningrck.adventofcode.common.input.InputParser;
import com.henningrck.adventofcode.common.utils.ParserUtils;

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
        return Arrays.stream(InputParser.toLines(rawInput))
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
    public Integer solvePart1(Card[] cards) {
        return Arrays.stream(cards)
                .map(this::getMatches)
                .mapToInt(matches -> (int) Math.pow(2, matches - 1))
                .sum();
    }

    @Override
    public Integer solvePart2(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            Card card = cards[i];
            int matches = getMatches(card);

            for (int j = i + 1; j <= i + matches; j++) {
                cards[j] = cards[j].addCopies(card.copies);
            }
        }

        return Arrays.stream(cards)
                .mapToInt(card -> card.copies)
                .sum();
    }

    private int getMatches(Card card) {
        return (int) Arrays.stream(card.numbersYouHave)
                .filter(numberYouHave -> org.apache.commons.lang3.ArrayUtils.contains(card.winningNumbers, numberYouHave))
                .count();
    }
}
