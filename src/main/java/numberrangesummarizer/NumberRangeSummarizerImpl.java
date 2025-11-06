package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    /**
     * Collects and converts the input string into a sorted list of integers.
     */
    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
    }


     // Summarizes a sorted collection of integers into a range format.
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) return "";


        List<Integer> numbers = new ArrayList<>(input);
        Collections.sort(numbers);

        StringBuilder result = new StringBuilder();
        int rangeStart = numbers.get(0);
        int rangeEnd = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            int current = numbers.get(i);
            // If not consecutive, end the current range
            if (current > rangeEnd + 1) {
                appendRange(result, rangeStart, rangeEnd);
                rangeStart = current;
            }
            rangeEnd = current;
        }

        // Append the last range
        appendRange(result, rangeStart, rangeEnd);

        return result.toString();
    }


     // Helper to append ranges or single numbers.
    private void appendRange(StringBuilder result, int rangeStart, int rangeEnd) {
        if (result.length() > 0) {
            result.append(", ");
        }

        if (rangeStart == rangeEnd) {
            result.append(rangeStart);
        } else {
            result.append(rangeStart + "-" + rangeEnd);
        }
    }
}
