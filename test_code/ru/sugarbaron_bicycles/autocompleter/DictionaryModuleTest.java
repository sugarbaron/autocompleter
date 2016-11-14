package ru.sugarbaron_bicycles.autocompleter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import ru.sugarbaron_bicycles.autocompleter.exceptions.NeedFixCode;
import ru.sugarbaron_bicycles.autocompleter.exceptions.CriticalOperationFailed;

public final class DictionaryModuleTest {
    @Test
    public void addRecord() {
        Dictionary dictionary = new DictionaryModule();

        dictionary.addRecord("word", 5);
        return;
    }

    @Test
    public void getVariantsFor() {
        Dictionary dictionary = new DictionaryModule();
        LinkedList<String> prefixes = new LinkedList<>();
        IncomingDataProcessor inputReceiver = new IncomingDataProcessorModule(dictionary, prefixes);
        readInputFromFile(inputReceiver, "positiveCase.in");

        String  currentPrefix = prefixes.poll();
        List<String> variants = dictionary.getVariantsFor(currentPrefix);

        int variantsQuantity = variants.size();
        assertTrue(variantsQuantity == 4);

        String variant0 = variants.get(0);
        boolean isCorrect = variant0.equals("kanojo");
        assertTrue(isCorrect);

        String variant1 = variants.get(1);
        isCorrect = variant1.equals("kare");
        assertTrue(isCorrect);

        String variant2 = variants.get(2);
        isCorrect = variant2.equals("korosu");
        assertTrue(isCorrect);

        String variant3 = variants.get(3);
        isCorrect = variant3.equals("karetachi");
        assertTrue(isCorrect);

        currentPrefix = prefixes.poll();
        variants = dictionary.getVariantsFor(currentPrefix);

        variantsQuantity = variants.size();
        assertTrue(variantsQuantity == 3);

        variant0  = variants.get(0);
        isCorrect = variant0.equals("kanojo");
        assertTrue(isCorrect);

        variant1  = variants.get(1);
        isCorrect = variant1.equals("kare");
        assertTrue(isCorrect);

        variant2  = variants.get(2);
        isCorrect = variant2.equals("karetachi");
        assertTrue(isCorrect);

        currentPrefix = prefixes.poll();
        variants = dictionary.getVariantsFor(currentPrefix);

        variantsQuantity = variants.size();
        assertTrue(variantsQuantity == 2);

        variant0  = variants.get(0);
        isCorrect = variant0.equals("kare");
        assertTrue(isCorrect);

        variant1  = variants.get(1);
        isCorrect = variant1.equals("karetachi");
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void getVariantsFor_bigData() {
        Dictionary dictionary = new DictionaryModule();
        List<String> prefixes = new LinkedList<>();
        IncomingDataProcessor inputReceiver = new IncomingDataProcessorModule(dictionary, prefixes);
        readInputFromFile(inputReceiver, "test.in");

        for (String everyPrefix: prefixes) {
            dictionary.getVariantsFor(everyPrefix);
        }
        return;
    }

    @Test
    public void getVariantsFor_lessThan10WordsAtTheEnd() {
        Dictionary dictionary = new DictionaryModule();
        List<String> prefixes = new LinkedList<>();
        IncomingDataProcessor inputReceiver = new IncomingDataProcessorModule(dictionary, prefixes);
        readInputFromFile(inputReceiver, "lessThan10WordsAtTheEndCase.in");

        dictionary.getVariantsFor("prefix");
        return;
    }

    @Test
    public void getVariantsFor_prefixIsTheGreatest() {
        Dictionary dictionary = new DictionaryModule();
        List<String> prefixes = new LinkedList<>();
        IncomingDataProcessor inputReceiver = new IncomingDataProcessorModule(dictionary, prefixes);
        readInputFromFile(inputReceiver, "prefixIsTheGreatestCase.in");

        List<String> variants = dictionary.getVariantsFor("prefix");

        int variantsQuantity = variants.size();
        boolean isCorrect = (variantsQuantity == 0);
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void getVariantsFor_equalFrequencies() {
        Dictionary dictionary = new DictionaryModule();
        List<String> prefixes = new LinkedList<>();
        IncomingDataProcessor inputReceiver = new IncomingDataProcessorModule(dictionary, prefixes);
        readInputFromFile(inputReceiver, "equalFrequenciesCase.in");

        List<String> variants = dictionary.getVariantsFor("v");

        String variantA = variants.get(0);
        String variantB = variants.get(1);
        boolean isCorrect = variantA.equals("varianta");
        assertTrue(isCorrect);
        isCorrect = variantB.equals("variantb");
        assertTrue(isCorrect);
        return;
    }

    private void readInputFromFile(IncomingDataProcessor inputReceiver, String filename) {
        try (FileInputStream input = new FileInputStream("test_data/" + filename) ) {
            inputReceiver.readIncomingData(input);
        } catch (FileNotFoundException exception) {
            throw new NeedFixCode("test file is not found");
        } catch (IOException exception) {
            throw new CriticalOperationFailed(exception);
        }
        return;
    }
}