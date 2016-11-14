package ru.sugarbaron_bicycles.autocompleter.exceptions;

import org.junit.Test;

public final class NeedFixCodeTest {
    @Test(expected = NeedFixCode.class)
    public void throwing() {
        throw new NeedFixCode("testing exception throwing");
    }
}