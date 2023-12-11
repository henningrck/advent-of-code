package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {
    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("""
                        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                        """, 8, 2286),
                Arguments.of(InputReader.readRawInput(2023, 2), 2085, 79315)
        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void testSolve(String rawInput, int expectedPart1, int expectedPart2) {
        Day02 day02 = new Day02();
        Day02.Game[] input = day02.cookInput(rawInput);
        assertEquals(expectedPart1, day02.solvePart1(input));
        assertEquals(expectedPart2, day02.solvePart2(input));
    }
}