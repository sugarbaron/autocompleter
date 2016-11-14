package ru.sugarbaron_bicycles.autocompleter.exceptions;

/**
 * Reports about errors in code. It must be fixed!
 */
public final class NeedFixCode extends RuntimeException {
    public NeedFixCode(String message) {
        super("{NeedFixCode} " + message);
        return;
    }
}