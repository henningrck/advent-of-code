package com.henningstorck.adventofcode.common.day;

public interface Day<Input, Result> {
    Input cookInput(String rawInput);

    Result solvePart1(Input input);

    Result solvePart2(Input input);
}
