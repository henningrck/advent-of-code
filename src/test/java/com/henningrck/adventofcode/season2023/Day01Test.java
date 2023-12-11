package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {
    public static Stream<Arguments> inputsPart1() {
        return Stream.of(
                Arguments.of("""
                        1abc2
                        pqr3stu8vwx
                        a1b2c3d4e5f
                        treb7uchet
                        """, 142),
                Arguments.of(InputReader.readRawInput(2023, 1), 55002)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsPart1")
    void testSolvePart1(String rawInput, int expected) {
        Day01 day01 = new Day01();
        String[] input = day01.cookInput(rawInput);
        assertEquals(expected, day01.solvePart1(input));
    }

    public static Stream<Arguments> inputsPart2() {
        return Stream.of(
                Arguments.of("""
                        two1nine
                        eightwothree
                        abcone2threexyz
                        xtwone3four
                        4nineeightseven2
                        zoneight234
                        7pqrstsixteen
                        """, 281),
                Arguments.of(InputReader.readRawInput(2023, 1), 55093)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsPart2")
    void testSolvePart2(String rawInput, int expected) {
        Day01 day01 = new Day01();
        String[] input = day01.cookInput(rawInput);
        assertEquals(expected, day01.solvePart2(input));
    }
}