package ru.sugarbaron_bicycles.autocompleter;

import java.io.InputStream;
import java.util.List;

/**
 * Simple implementation of IncomingDataProcessor,
 * based on serial reading of tokens from given input
 */
final class IncomingDataProcessorModule implements IncomingDataProcessor {
    private Dictionary dictionary;
    private List<String> prefixes;
    private TokenExtractor extractor;

    IncomingDataProcessorModule(Dictionary dictionary, List<String> prefixes) {
        this.dictionary = dictionary;
        this.prefixes = prefixes;
        return;
    }

    @Override
    public void readIncomingData(InputStream incomingData) {
        extractor = new TokenExtractor(incomingData);
        fillDictionary();
        fillPrefixes();
        return;
    }

    private void fillDictionary() {
        int wordsQuantity = extractor.nextTokenAsNumber();
        String currentWord;
        int currentFrequency;
        for (int wordNumber = 0; wordNumber < wordsQuantity; wordNumber++) {
            currentWord = extractor.nextTokenAsText();
            currentFrequency = extractor.nextTokenAsNumber();
            dictionary.addRecord(currentWord, currentFrequency);
        }
        return;
    }

    private void fillPrefixes() {
        int prefixesQuantity = extractor.nextTokenAsNumber();
        String currentPrefix;
        for (int prefixNumber = 0; prefixNumber < prefixesQuantity; prefixNumber++) {
            currentPrefix = extractor.nextTokenAsText();
            prefixes.add(currentPrefix);
        }
        return;
    }
}