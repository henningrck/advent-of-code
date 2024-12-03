package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
    public static Stream<Arguments> inputsPart1() {
        return Stream.of(
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day10/example1.txt"), 4),
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day10/example2.txt"), 8),
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day10/henning.txt"), 6846)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsPart1")
    void testSolvePart1(String rawInput, int expected) {
        var day = new Day10();
        var input = day.cookInput(rawInput);
        assertEquals(expected, day.solvePart1(input));
    }

    public static Stream<Arguments> inputsPart2() {
        return Stream.of(
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day10/example3.txt"), 4),
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day10/example4.txt"), 8),
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day10/example5.txt"), 10),
                Arguments.of(InputReader.readRawInput("/inputs/season2023/day10/henning.txt"), 325)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsPart2")
    void testSolvePart2(String rawInput, int expected) {
        var day = new Day10();
        var input = day.cookInput(rawInput);
        assertEquals(expected, day.solvePart2(input));
    }
}
