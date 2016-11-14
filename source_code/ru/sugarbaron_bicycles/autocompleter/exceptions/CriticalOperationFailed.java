package ru.sugarbaron_bicycles.autocompleter.exceptions;

/**
 * Reports about some system operation is failed and this fact
 * does not allow continue execution of a program.
 */
public final class CriticalOperationFailed extends RuntimeException {
    public CriticalOperationFailed(Exception exception) {
        super(exception);
        return;
    }

    public CriticalOperationFailed(String message) {
        super("{CriticalOperationFailed} " + message);
        return;
    }
}