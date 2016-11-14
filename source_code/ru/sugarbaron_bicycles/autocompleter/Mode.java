package ru.sugarbaron_bicycles.autocompleter;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Encapsulates data, which defines
 * work mode of autocompleter.
 * (Console mode, or file mode)
 */
final class Mode {
    private InputStream input;
    private PrintStream output;

    Mode(InputStream input, PrintStream output) {
        this.input  = input;
        this.output = output;
        return;
    }

    InputStream getInput() {
        return input;
    }

    PrintStream getOutput() {
        return output;
    }
}