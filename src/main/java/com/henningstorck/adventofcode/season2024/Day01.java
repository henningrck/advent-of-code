package com.henningstorck.adventofcode.season2024;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 implements Day<Day01.Lists, Integer> {
    public record Lists(List<Integer> firstList, List<Integer> secondList) {
    }

    @Override
    public Lists cookInput(String rawInput) {
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        for (String line : InputParser.splitLines(rawInput)) {
            List<String> values = InputParser.splitWhitespace(line);
            firstList.add(Integer.parseInt(values.get(0)));
            secondList.add(Integer.parseInt(values.get(1)));
        }

        Collections.sort(firstList);
        Collections.sort(secondList);

        return new Lists(firstList, secondList);
    }

    @Override
    public Integer solvePart1(Lists input) {
        List<Integer> firstList = input.firstList();
        List<Integer> secondList = input.secondList();
        int result = 0;

        for (int i = 0; i < firstList.size(); i++) {
            int firstValue = firstList.get(i);
            int secondValue = secondList.get(i);
            int diff = Math.abs(firstValue - secondValue);
            result += diff;
        }

        return result;
    }

    @Override
    public Integer solvePart2(Lists input) {
        List<Integer> firstList = input.firstList();
        List<Integer> secondList = input.secondList();
        int result = 0;

        for (int i : firstList) {
            int occurrences = (int) secondList.stream().filter(j -> j == i).count();
            int similarityScore = i * occurrences;
            result += similarityScore;
        }

        return result;
    }
}
