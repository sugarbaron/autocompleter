package ru.sugarbaron_bicycles.autocompleter;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import ru.sugarbaron_bicycles.autocompleter.exceptions.NeedFixCode;
import ru.sugarbaron_bicycles.autocompleter.exceptions.CriticalOperationFailed;
import ru.sugarbaron_bicycles.autocompleter.exceptions.WrongIncomingDataFormat;

public final class IncomingDataProcessorModuleTest {
    @Test
    public void readIncomingData() {
        readInputFromFile("positiveCase.in");
        return;
    }

    @Test
    public void readIncomingData_bigData() {
        readInputFromFile("test.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_wordsQuantityIsMissing() {
        readInputFromFile("wordsQuantityIsMissingCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_wrongWordsQuantity() {
        readInputFromFile("wrongWordsQuantityCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_someWordIsMissing() {
        readInputFromFile("someWordIsMissingCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_tooLongWord() {
        readInputFromFile("tooLongWordCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_wrongDelimiter() {
        readInputFromFile("wrongDelimiterCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_someFrequencyIsMissing() {
        readInputFromFile("someFrequencyIsMissingCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_spacesAfterFrequency() {
        readInputFromFile("spacesAfterFrequencyCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_prefixesQuantityIsMissing() {
        readInputFromFile("prefixesQuantityIsMissingCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_wrongPrefixesQuantity() {
        readInputFromFile("wrongPrefixesQuantityCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_somePrefixIsMissing() {
        readInputFromFile("somePrefixIsMissingCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_tooLongPrefix() {
        readInputFromFile("tooLongPrefixCase.in");
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void readIncomingData_spacesAfterPrefix() {
        readInputFromFile("spacesAfterPrefixCase.in");
        return;
    }

    private void readInputFromFile(String filename) {
        List<String> prefixes = new LinkedList<>();
        Dictionary dictionary = new DictionaryModule();
        IncomingDataProcessor inputReceiver = new IncomingDataProcessorModule(dictionary, prefixes);

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