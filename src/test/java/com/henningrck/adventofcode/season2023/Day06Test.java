package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {
    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("""
                        Time:      7  15   30
                        Distance:  9  40  200
                        """, 288, 71503),
                Arguments.of(InputReader.readRawInput(2023, 6), 170000, 20537782)
        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void testSolve(String rawInput, int expectedPart1, int expectedPart2) {
        Day06 day06 = new Day06();
        assertEquals(expectedPart1, day06.solvePart1(rawInput));
        assertEquals(expectedPart2, day06.solvePart2(rawInput));
    }
}