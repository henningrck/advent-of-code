package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {
    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("""
                        seeds: 79 14 55 13
                                                
                        seed-to-soil map:
                        50 98 2
                        52 50 48
                                                
                        soil-to-fertilizer map:
                        0 15 37
                        37 52 2
                        39 0 15
                                                
                        fertilizer-to-water map:
                        49 53 8
                        0 11 42
                        42 0 7
                        57 7 4
                                                
                        water-to-light map:
                        88 18 7
                        18 25 70
                                                
                        light-to-temperature map:
                        45 77 23
                        81 45 19
                        68 64 13
                                                
                        temperature-to-humidity map:
                        0 69 1
                        1 0 69
                                                
                        humidity-to-location map:
                        60 56 37
                        56 93 4
                        """, 35, 46),
                Arguments.of(InputReader.readRawInput(2023, 5), 318728750, 37384986),
                Arguments.of(InputReader.readRawInput(2023, 5, "julian"), 535088217, 51399228)
        );
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void testSolve(String rawInput, int expectedPart1, int expectedPart2) {
        Day05 day05 = new Day05();
        Day05.Almanac input = day05.cookInput(rawInput);
        assertEquals(expectedPart1, day05.solvePart1(input));
        assertEquals(expectedPart2, day05.solvePart2(input));
    }
}