package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {
    public static final String EXAMPLE = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
            """;

    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of(EXAMPLE, 10, 374, 1030),
                Arguments.of(EXAMPLE, 100, 374, 8410),
                Arguments.of(InputReader.readRawInput(2023, 11), 1000000, 9545480, 406725732046L)
        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void testSolve(String rawInput, int factor, long expectedPart1, long expectedPart2) {
        Day11 day11 = new Day11(factor);
        char[][] input = day11.cookInput(rawInput);
        assertEquals(expectedPart1, day11.solvePart1(input));
        assertEquals(expectedPart2, day11.solvePart2(input));
    }
}