package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {
    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day11/example.txt"), 10, 374, 1030),
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day11/example.txt"), 100, 374, 8410),
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day11/henning.txt"), 1000000, 9545480, 406725732046L)
        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void testSolve(String rawInput, int factor, long expectedPart1, long expectedPart2) {
        var day = new Day11(factor);
        var input = day.cookInput(rawInput);
        assertEquals(expectedPart1, day.solvePart1(input));
        assertEquals(expectedPart2, day.solvePart2(input));
    }
}
