package com.henningstorck.adventofcode.season2024;

import com.henningstorck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {
    public static Stream<Arguments> inputsPart1() {
        return Stream.of(
                Arguments.of(InputReader.readRawInput("/inputs/season2024/day03/example1.txt"), 161),
                Arguments.of(InputReader.readRawInput("/inputs/season2024/day03/henning.txt"), 190604937)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsPart1")
    void testSolvePart1(String rawInput, int expected) {
        Day03 day = new Day03();
        var input = day.cookInput(rawInput);
        assertEquals(expected, day.solvePart1(input));
    }

    public static Stream<Arguments> inputsPart2() {
        return Stream.of(
                Arguments.of(InputReader.readRawInput("/inputs/season2024/day03/example2.txt"), 48),
                Arguments.of(InputReader.readRawInput("/inputs/season2024/day03/henning.txt"), 82857512)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsPart2")
    void testSolvePart2(String rawInput, int expected) {
        var day = new Day03();
        var input = day.cookInput(rawInput);
        assertEquals(expected, day.solvePart2(input));
    }
}
