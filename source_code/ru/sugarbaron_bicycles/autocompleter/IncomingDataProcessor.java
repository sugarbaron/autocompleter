package ru.sugarbaron_bicycles.autocompleter;

import java.io.InputStream;
import ru.sugarbaron_bicycles.autocompleter.exceptions.WrongIncomingDataFormat;

/**
 * Provides initial processing of incoming data.
 */
interface IncomingDataProcessor {
    /**
     * Receive incoming data with initial processing of it.
     * @param incomingData - incoming data.
     * @throws WrongIncomingDataFormat - in case of wrong incoming data format
     */
    public void readIncomingData(InputStream incomingData);
}