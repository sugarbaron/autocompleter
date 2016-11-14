package ru.sugarbaron_bicycles.autocompleter.exceptions;

import org.junit.Test;

public final class CriticalOperationFailedTest {
    @Test(expected = CriticalOperationFailed.class)
    public void throwingBasedOnException() {
        Exception baseException = new Exception();
        throw new CriticalOperationFailed(baseException);
    }

    @Test(expected = CriticalOperationFailed.class)
    public void throwing() {
        throw new CriticalOperationFailed("testing exception throwing");
    }
}