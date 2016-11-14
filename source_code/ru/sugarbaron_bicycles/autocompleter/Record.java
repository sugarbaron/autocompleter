package ru.sugarbaron_bicycles.autocompleter;

import java.util.Comparator;

/**
 * Record is a union of word and it's frequency
 */
final class Record implements Comparable {
    private String word;
    private int frequency;
    private Comparator<Record> orderDefiner;

    Record(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
        activateWordsComparator();
        return;
    }

    void activateWordsComparator() {
        setComparator(this::wordsComparator);
        return;
    }

    void activateFrequenciesComparator() {
        setComparator(this::frequenciesComparator);
        return;
    }

    private void setComparator(Comparator<Record> comparator) {
        orderDefiner = comparator;
        return;
    }

    private int wordsComparator(Record left, Record right) {
        int result = left.word.compareTo(right.word);
        return result;
    }

    private int frequenciesComparator(Record left, Record right) {
        // logic below is for ensuring a reverse order of comparison
        int result;
        if (left.frequency < right.frequency) {
            result = 1;
        } else if (left.frequency > right.frequency) {
            result = -1;
        } else {
            result = left.word.compareTo(right.word);
        }
        return result;
    }

    @Override
    public int compareTo(Object other) {
        int result = orderDefiner.compare(this, (Record)other);
        return result;
    }

    String getWord() {
        return word;
    }

    int getFrequency() {
        return frequency;
    }
}