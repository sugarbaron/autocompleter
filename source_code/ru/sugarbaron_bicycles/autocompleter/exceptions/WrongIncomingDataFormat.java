package ru.sugarbaron_bicycles.autocompleter.exceptions;

/**
 * Reports about wrong incoming data format
 */
public final class WrongIncomingDataFormat extends RuntimeException {
    public WrongIncomingDataFormat(String message) {
        super("{WrongIncomingDataFormat} " + message);
        return;
    }
}