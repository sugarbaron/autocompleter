package ru.sugarbaron_bicycles.autocompleter;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of <code>Dictionary</code>, based on TreeSet
 * for storing words and their frequencies
 */
final class DictionaryModule implements Dictionary {
    private TreeSet<Record> records;

    DictionaryModule() {
        records = new TreeSet<>();
        return;
    }

    public void addRecord(String word, int frequency) {
        Record newRecord = new Record(word, frequency);
        records.add(newRecord);
        return;
    }

    public List<String> getVariantsFor(String prefix) {
        List<String> variants;
        try {
            variants = tryToGetVariantsFor(prefix);
        } catch (IllegalArgumentException noSuchWordsInDictionary) {
            variants = new LinkedList<>();
        }
        return variants;
    }

    private List<String> tryToGetVariantsFor(String prefix) {
        Set<Record> headless = decapitate(prefix);
        Set<Record> requiredRecords = getRequiredRecords(headless, prefix);
        List<String> variants = extractWords(requiredRecords);
        restoreComparatorsIn(requiredRecords);
        return variants;
    }

    private Set<Record> decapitate(String prefix) {
        final int anyNumber = 0;
        Record marker = new Record(prefix, anyNumber);
        Set<Record> headless = records.tailSet(marker);
        return headless;
    }

    private Set<Record> getRequiredRecords(Set<Record> headless, String prefix) {
        final int variantsMaximum = 10;
        int variantsQuantity = 0;
        Set<Record> requiredRecords = new TreeSet<>();
        Iterator<Record> accessor = headless.iterator();
        boolean needMore = accessor.hasNext();

        while (needMore) {
            Record currentRecord = accessor.next();
            String currentWord = currentRecord.getWord();
            boolean isVariant  = currentWord.startsWith(prefix);
            if (isVariant) {
                currentRecord.activateFrequenciesComparator();
                requiredRecords.add(currentRecord);
                variantsQuantity++;
            }
            needMore = ( isVariant && (variantsQuantity < variantsMaximum) && accessor.hasNext() );
        }
        return requiredRecords;
    }

    private void restoreComparatorsIn(Set<Record> requiredRecords) {
        for (Record everyRecord: requiredRecords) {
            everyRecord.activateWordsComparator();
        }
        return;
    }

    private List<String> extractWords(Set<Record> records) {
        List<String> words = new LinkedList<>();
        String currentWord;
        for (Record currentRecord: records) {
            currentWord = currentRecord.getWord();
            words.add(currentWord);
        }
        return words;
    }
}