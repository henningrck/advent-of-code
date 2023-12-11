package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {
    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("""
                        467..114..
                        ...*......
                        ..35..633.
                        ......#...
                        617*......
                        .....+.58.
                        ..592.....
                        ......755.
                        ...$.*....
                        .664.598..
                        """, 4361, 467835),
                Arguments.of(InputReader.readRawInput(2023, 3), 525911, 75805607)
        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void testSolve(String rawInput, int expectedPart1, int expectedPart2) {
        Day03 day03 = new Day03();
        char[][] input = day03.cookInput(rawInput);
        assertEquals(expectedPart1, day03.solvePart1(input));
        assertEquals(expectedPart2, day03.solvePart2(input));
    }
}