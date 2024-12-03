package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {
    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day06/example.txt"), 288, 71503),
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day06/henning.txt"), 170000, 20537782)
        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void testSolve(String rawInput, int expectedPart1, int expectedPart2) {
        var day = new Day06();
        var input = day.cookInput(rawInput);
        assertEquals(expectedPart1, day.solvePart1(input));
        assertEquals(expectedPart2, day.solvePart2(input));
    }
}
