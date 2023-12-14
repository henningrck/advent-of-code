package com.henningrck.adventofcode.season2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {
    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("""
                        32T3K 765
                        T55J5 684
                        KK677 28
                        KTJJT 220
                        QQQJA 483
                        """, 6440, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void testSolve(String rawInput, int expectedPart1, int expectedPart2) {
        Day07 day07 = new Day07();
        List<Day07.Hand> input = day07.cookInput(rawInput);
        assertEquals(expectedPart1, day07.solvePart1(input));
        assertEquals(expectedPart2, day07.solvePart2(input));
    }
}
