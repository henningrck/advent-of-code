package com.henningstorck.adventofcode.season2023;

import com.henningstorck.adventofcode.common.day.Day;
import com.henningstorck.adventofcode.common.input.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 implements Day<Day05.Almanac, Long> {
    public final static Pattern MAPPING_ENTRY_PATTERN = Pattern.compile("(\\d+) (\\d+) (\\d+)");

    public record Almanac(long[] seeds, MappingGroup[] mappingGroups) {
    }

    public record MappingGroup(MappingEntry[] mappingEntries) {
    }

    public record MappingEntry(Range destination, Range source) {
        public boolean appliesTo(long value) {
            return value >= source.from() && value <= source.to();
        }

        public boolean appliesTo(Range range) {
            return range.overlaps(source);
        }

        public long applyMapping(long value) {
            long diff = value - source.from();
            return destination.from() + diff;
        }

        public List<Range> applyMapping(Range range) {
            List<Range> ranges = new ArrayList<>();
            long diff = destination.from() - source.from();
            ranges.add(range.intersection(source).offset(diff));

            if (source.from() > range.from()) {
                ranges.add(new Range(range.from(), source.from() - 1));
            }

            if (source.to() < range.to()) {
                ranges.add(new Range(source.to() + 1, range.to()));
            }

            return ranges;
        }
    }

    public record Range(long from, long to) {
        public boolean overlaps(Range other) {
            return to >= other.from && from <= other.to;
        }

        public Range intersection(Range other) {
            long newFrom = Math.max(from, other.from);
            long newTo = Math.min(to, other.to);
            return new Range(newFrom, newTo);
        }

        public Range offset(long offset) {
            return new Range(from + offset, to + offset);
        }
    }

    @Override
    public Almanac cookInput(String rawInput) {
        List<String> chunks = new ArrayList<>(InputParser.splitChunks(rawInput));
        long[] seeds = parseSeeds(chunks.getFirst());
        chunks.removeFirst();
        MappingGroup[] mappingGroups = parseMappingGroups(chunks);
        return new Almanac(seeds, mappingGroups);
    }

    private long[] parseSeeds(String rawSeeds) {
        rawSeeds = rawSeeds.replaceAll("seeds: ", "");
        return Arrays.stream(rawSeeds.split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    private MappingGroup[] parseMappingGroups(List<String> chunks) {
        return chunks.stream().map(chunk -> {
            MappingEntry[] mappingEntries = InputParser.splitLines(chunk).stream()
                    .map(MAPPING_ENTRY_PATTERN::matcher)
                    .filter(Matcher::matches)
                    .map(matcher -> {
                        long length = Long.parseLong(matcher.group(3));
                        long destinationFrom = Long.parseLong(matcher.group(1));
                        long destinationTo = destinationFrom + length;
                        long sourceFrom = Long.parseLong(matcher.group(2));
                        long sourceTo = sourceFrom + length - 1;
                        return new MappingEntry(new Range(destinationFrom, destinationTo), new Range(sourceFrom, sourceTo));
                    }).toArray(MappingEntry[]::new);
            return new MappingGroup(mappingEntries);
        }).toArray(MappingGroup[]::new);
    }

    @Override
    public Long solvePart1(Almanac input) {
        long result = Long.MAX_VALUE;

        for (long value : input.seeds()) {
            for (MappingGroup mappingGroup : input.mappingGroups()) {
                for (MappingEntry mappingEntry : mappingGroup.mappingEntries()) {
                    if (mappingEntry.appliesTo(value)) {
                        value = mappingEntry.applyMapping(value);
                        break;
                    }
                }
            }

            if (value < result) {
                result = value;
            }
        }

        return result;
    }

    @Override
    public Long solvePart2(Almanac input) {
        long result = Long.MAX_VALUE;

        for (int i = 0; i < input.seeds().length; i += 2) {
            long from = input.seeds[i];
            long to = from + input.seeds[i + 1] - 1;
            List<Range> untouchedRanges = new ArrayList<>(List.of(new Range(from, to)));
            List<Range> processedRanges = new ArrayList<>();

            for (MappingGroup mappingGroup : input.mappingGroups()) {
                for (MappingEntry mappingEntry : mappingGroup.mappingEntries()) {
                    for (Range range : new ArrayList<>(untouchedRanges)) {
                        if (mappingEntry.appliesTo(range)) {
                            untouchedRanges.remove(range);
                            List<Range> newRanges = mappingEntry.applyMapping(range);
                            processedRanges.add(newRanges.getFirst());
                            newRanges.removeFirst();
                            untouchedRanges.addAll(newRanges);
                        }
                    }
                }

                untouchedRanges.addAll(processedRanges);
                processedRanges.clear();
            }

            for (Range range : untouchedRanges) {
                if (range.from() < result) {
                    result = range.from();
                }
            }
        }

        return result;
    }
}
