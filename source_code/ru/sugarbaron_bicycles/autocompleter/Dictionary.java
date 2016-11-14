package ru.sugarbaron_bicycles.autocompleter;

import java.util.List;

/**
 * Provides a storage for words and their frequencies
 */
interface Dictionary {
    /**
     * Adds record (pair: word, frequency) to dictionary
     * @param word - word to write
     * @param frequency - frequency for given word
     */
    public void addRecord(String word, int frequency);

    /**
     * Returns a sorted (by frequency) set
     * of autocompletion variants for specified prefix.
     * @param prefix - prefix for autocompletion.
     * @return sorted (by frequency) set of autocompletion
     * variants for specified prefix.
     */
    public List<String> getVariantsFor(String prefix);
}