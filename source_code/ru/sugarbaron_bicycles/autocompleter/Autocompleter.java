package ru.sugarbaron_bicycles.autocompleter;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import ru.sugarbaron_bicycles.autocompleter.exceptions.CriticalOperationFailed;
import ru.sugarbaron_bicycles.autocompleter.exceptions.WrongIncomingDataFormat;

/**
 * Contains an entry point of an application.
 * Builds an application and starts it.
 */
final class Autocompleter {
    private InputStream input;
    private PrintStream output;
    private Dictionary dictionary;
    private List<String> prefixes;
    private IncomingDataProcessor inputReceiver;

    public static void main(String[] parameters) {
        try {
            Mode mode = defineMode(parameters);
            Autocompleter autocompleter = new Autocompleter(mode);
            autocompleter.start();
        } catch (Exception error) {
            String diagnostics = error.getMessage();
            System.out.println(diagnostics);
        }
        return;
    }

    private static Mode defineMode(String[] parameters) {
        InputStream input;
        PrintStream output;
        boolean noArgumentsSpecified = (parameters.length == 0);
        if (noArgumentsSpecified) {
            input  = System.in;
            output = System.out;
        } else {
            String incomingDataFile = parameters[0];
            String resultFile = "result_for_" + incomingDataFile;
            input  = openInputStreamFor(incomingDataFile);
            output = openOutputStreamFor(resultFile);
        }
        Mode mode = new Mode(input, output);
        return mode;
    }

    private static InputStream openInputStreamFor(String filename) {
        InputStream input;
        try {
            input = new FileInputStream(filename);
        } catch (FileNotFoundException exception) {
            String diagnostics = "wrong file name, or ";
            diagnostics += filename;
            diagnostics += " is absent in a directory with autocompleter.jar";
            throw new WrongIncomingDataFormat(diagnostics);
        }
        return input;
    }

    private static PrintStream openOutputStreamFor(String filename) {
        PrintStream output;
        try {
            OutputStream stream = new FileOutputStream(filename);
            output = new PrintStream(stream);
        } catch (FileNotFoundException exception) {
            String diagnostics = "result file can not be created";
            throw new CriticalOperationFailed(diagnostics);
        }
        return output;
    }

    private Autocompleter(Mode mode) {
        input  = mode.getInput();
        output = mode.getOutput();
        dictionary = new DictionaryModule();
        prefixes = new LinkedList<>();
        inputReceiver = new IncomingDataProcessorModule(dictionary, prefixes);
        return;
    }

    private void start() {
        inputReceiver.readIncomingData(input);
        ensureInputFileStreamIsClosed();
        List<String> currentVariants;
        for (String currentPrefix: prefixes) {
            currentVariants = dictionary.getVariantsFor(currentPrefix);
            giveOutResult(currentVariants);
        }
        ensureOutputFileStreamIsClosed();
        return;
    }

    private void ensureInputFileStreamIsClosed() {
        boolean fileStreamIsOpened = (input != System.in);
        if (fileStreamIsOpened) {
            tryToCloseInput();
        }
        return;
    }

    private void tryToCloseInput() {
        try {
            input.close();
        } catch (IOException exception) {
            throw new CriticalOperationFailed(exception);
        }
        return;
    }

    private void giveOutResult(List<String> variants) {
        for (String variant: variants) {
            output.println(variant);
        }
        output.println();
        return;
    }

    private void ensureOutputFileStreamIsClosed() {
        boolean fileStreamIsOpened = (output != System.out);
        if (fileStreamIsOpened) {
            output.close();
        }
        return;
    }
}