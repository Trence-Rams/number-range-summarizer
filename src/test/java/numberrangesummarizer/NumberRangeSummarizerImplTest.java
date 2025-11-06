package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class NumberRangeSummarizerImplTest {

    private final NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();

    @Test
    void testCollect_ValidInput() {
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12);
        Collection<Integer> actual = summarizer.collect("1, 3, 6, 7, 8, 12");
        assertEquals(expected, actual);
    }

    @Test
    void testCollect_HandlesSpacesAndEmptyValues() {
        Collection<Integer> expected = Arrays.asList(1, 2, 3);
        Collection<Integer> actual = summarizer.collect(" 1 , , 2 , 3 ");
        assertEquals(expected, actual);
    }

    @Test
    void testCollect_SortsNumbers() {
        Collection<Integer> expected = Arrays.asList(1, 3, 5, 10);
        Collection<Integer> actual = summarizer.collect("10, 3, 5, 1");
        assertEquals(expected, actual);
    }

    @Test
    void testCollect_EmptyInput() {
        Collection<Integer> result = summarizer.collect("");
        assertTrue(result.isEmpty());
    }

    @Test
    void testCollect_InvalidCharacters() {
        assertThrows(NumberFormatException.class, () -> summarizer.collect("1, a, 3"));
    }

    @Test
    void testSummarizeCollection_ContinuousRanges() {
        List<Integer> input = Arrays.asList(1, 2, 3, 5, 6, 7);
        String expected = "1-3, 5-7";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    void testSummarizeCollection_SingleNumbers() {
        List<Integer> input = Arrays.asList(1, 4, 6);
        String expected = "1, 4, 6";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    void testSummarizeCollection_EmptyCollection() {
        List<Integer> input = new ArrayList<>();
        String result = summarizer.summarizeCollection(input);
        assertEquals("", result);
    }

    @Test
    void testSummarizeCollection_WithDuplicates() {
        List<Integer> input = Arrays.asList(1, 2, 2, 3, 5);
        String expected = "1-3, 5";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    void testIntegration_CollectThenSummarize() {
        Collection<Integer> input = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String result = summarizer.summarizeCollection(input);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        assertEquals(expected, result);
    }

    @Test
    void testIntegration_UnorderedInput() {
        Collection<Integer> input = summarizer.collect("10,1,2,3,7,8,9");
        String result = summarizer.summarizeCollection(input);
        String expected = "1-3, 7-10";
        assertEquals(expected, result);
    }
}
