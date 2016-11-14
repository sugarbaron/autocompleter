package ru.sugarbaron_bicycles.autocompleter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.InputStream;
import java.io.PrintStream;

public final class ModeTest {
    @Test
    public void getInput() {
        InputStream expected = System.in;
        PrintStream output = System.out;
        Mode consoleMode = new Mode(expected, output);

        InputStream actual = consoleMode.getInput();
        boolean  isCorrect = (actual == expected);
        assertTrue(isCorrect);
        return;
    }

    @Test
    public void getOutput() {
        InputStream input = System.in;
        PrintStream expected = System.out;
        Mode consoleMode = new Mode(input, expected);

        PrintStream actual = consoleMode.getOutput();
        boolean  isCorrect = (actual == expected);
        assertTrue(isCorrect);
        return;
    }
}