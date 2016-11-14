package ru.sugarbaron_bicycles.autocompleter.exceptions;

import org.junit.Test;

public final class WrongIncomingDataFormatTest {
    @Test(expected = WrongIncomingDataFormat.class)
    public void throwing() {
        throw new WrongIncomingDataFormat("testing exception throwing");
    }
}