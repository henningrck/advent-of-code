package com.henningrck.adventofcode.season2023;

import com.henningrck.adventofcode.common.input.InputReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
    public static Stream<Arguments> inputsPart1() {
        return Stream.of(
                Arguments.of("""
                        .....
                        .S-7.
                        .|.|.
                        .L-J.
                        .....
                        """, 4),
                Arguments.of("""
                        ..F7.
                        .FJ|.
                        SJ.L7
                        |F--J
                        LJ...
                        """, 8),
                Arguments.of(InputReader.readRawInput(2023, 10), 6846)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsPart1")
    void testSolvePart1(String rawInput, int expected) {
        Day10 day10 = new Day10();
        char[][] input = day10.cookInput(rawInput);
        assertEquals(expected, day10.solvePart1(input));
    }

    public static Stream<Arguments> inputsPart2() {
        return Stream.of(
                Arguments.of("""
                        ...........
                        .S-------7.
                        .|F-----7|.
                        .||.....||.
                        .||.....||.
                        .|L-7.F-J|.
                        .|..|.|..|.
                        .L--J.L--J.
                        ...........
                        """, 4),
                Arguments.of("""
                        .F----7F7F7F7F-7....
                        .|F--7||||||||FJ....
                        .||.FJ||||||||L7....
                        FJL7L7LJLJ||LJ.L-7..
                        L--J.L7...LJS7F-7L7.
                        ....F-J..F7FJ|L7L7L7
                        ....L7.F7||L7|.L7L7|
                        .....|FJLJ|FJ|F7|.LJ
                        ....FJL-7.||.||||...
                        ....L---J.LJ.LJLJ...
                        """, 8),
                Arguments.of("""
                        FF7FSF7F7F7F7F7F---7
                        L|LJ||||||||||||F--J
                        FL-7LJLJ||||||LJL-77
                        F--JF--7||LJLJ7F7FJ-
                        L---JF-JLJ.||-FJLJJ7
                        |F|F-JF---7F7-L7L|7|
                        |FFJF7L7F-JF7|JL---7
                        7-L-JL7||F7|L7F-7F7|
                        L.L7LFJ|||||FJL7||LJ
                        L7JLJL-JLJLJL--JLJ.L
                        """, 10),
                Arguments.of(InputReader.readRawInput(2023, 10), 325)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsPart2")
    void testSolvePart2(String rawInput, int expected) {
        Day10 day10 = new Day10();
        char[][] input = day10.cookInput(rawInput);
        assertEquals(expected, day10.solvePart2(input));
    }
}