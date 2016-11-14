package ru.sugarbaron_bicycles.autocompleter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class RecordTest {
    @Test
    public void compareTo() {
        Record smaller = new Record("abc", 5);
        Record greater = new Record("bcd", 5000);

        int result = smaller.compareTo(greater);

        boolean isCorrect = (result < 0);
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void compareTo_equalsWordsCase() {
        Record smaller = new Record("abc", 5000);
        Record greater = new Record("abc", 5);

        int result = smaller.compareTo(greater);

        boolean isCorrect = (result == 0);
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void compareTo_equalsFrequenciesCase() {
        Record smaller = new Record("abc", 5);
        Record greater = new Record("bcd", 5);
        smaller.activateFrequenciesComparator();

        int result = smaller.compareTo(greater);

        boolean isCorrect = (result < 0);
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void activateWordsComparator() {
        Record smaller = new Record("abc", 5);
        Record greater = new Record("bcd", 5000);
        smaller.activateFrequenciesComparator();

        smaller.activateWordsComparator();

        int result = smaller.compareTo(greater);
        boolean isCorrect = (result < 0);
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void activateFrequenciesComparator() {
        Record smaller = new Record("bcd", 5000);
        Record greater = new Record("abc", 5);
        smaller.activateWordsComparator();

        smaller.activateFrequenciesComparator();

        int result = smaller.compareTo(greater);
        boolean isCorrect = (result < 0);
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void getWord() {
        final String expected = "expected" + "_" + "word";
        Record record = new Record("expected_word", 5);

        String actual = record.getWord();

        boolean isCorrect = actual.equals(expected);
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void getFrequency() {
        final int expected = 5;
        Record record = new Record("expected_word", 5);

        int actual = record.getFrequency();

        boolean isCorrect = (actual == expected);
        assertTrue(isCorrect);
        return;
    }
}