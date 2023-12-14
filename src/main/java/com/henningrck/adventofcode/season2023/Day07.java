package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.day.Day;
import com.henningrck.adventofcode.common.input.InputParser;

import java.util.Arrays;
import java.util.List;

public class Day07 implements Day<List<Day07.Hand>, Integer> {
    public record Hand(char[] cards, int bid) {
    }

    @Override
    public List<Hand> cookInput(String rawInput) {
        return Arrays.stream(InputParser.toLines(rawInput)).map(line -> {
            String[] cardsAndBid = line.split(" ");
            char[] cards = cardsAndBid[0].toCharArray();
            int bid = Integer.parseInt(cardsAndBid[1]);
            return new Hand(cards, bid);
        }).toList();
    }

    @Override
    public Integer solvePart1(List<Hand> hands) {
        return 0;
    }

    @Override
    public Integer solvePart2(List<Hand> hands) {
        return 0;
    }
}
