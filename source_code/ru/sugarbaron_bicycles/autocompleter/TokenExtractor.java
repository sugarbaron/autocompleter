package ru.sugarbaron_bicycles.autocompleter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import ru.sugarbaron_bicycles.autocompleter.exceptions.NeedFixCode;
import ru.sugarbaron_bicycles.autocompleter.exceptions.CriticalOperationFailed;
import ru.sugarbaron_bicycles.autocompleter.exceptions.WrongIncomingDataFormat;

/**
 * A tool for serial extracting tokens from <code>InputStream</code>
 */
final class TokenExtractor {
    private InputStreamReader inputStream;

    TokenExtractor(InputStream input) {
        inputStream = new InputStreamReader(input);
        return;
    }

    int nextTokenAsNumber() {
        String token = nextTokenAsText();
        int tokenAsNumber = convertToken(token);
        return tokenAsNumber;
    }

    String nextTokenAsText() {
        final int maxTokenSize = 15;
        final int bufferSize = maxTokenSize + 1; // +1 is because of delimiter after token
        char[] tokenBuffer = new char[bufferSize];
        final int beginIndex = 0;
        String token;

        for (int currentIndex = 0; currentIndex < bufferSize; currentIndex++) {
            char receivedItem = readItem();
            if ( (receivedItem == ' ')||(receivedItem == '\n') ) {
                token = String.valueOf(tokenBuffer, beginIndex, currentIndex);
                return token;
            }
            tokenBuffer[currentIndex] = receivedItem;
        }
        throw new WrongIncomingDataFormat("token is too long");
    }

    /** read character with carriage return handling */
    private char readItem() {
        char receivedItem;
        receivedItem = readCharacter();
        if (receivedItem == '\r') {
            // considering, that next character always will be '\n'
            receivedItem = readCharacter();
        }
        return receivedItem;
    }

    private char readCharacter() {
        char receivedCharacter;
        try {
            receivedCharacter = tryToReadCharacter();
        } catch(IOException sorrow) {
            throw new CriticalOperationFailed(sorrow);
        }
        return receivedCharacter;
    }

    private char tryToReadCharacter() throws IOException {
        final int offset = 0;
        final int expectedIncomingQuantity = 1;
        final int inputBufferSize = 1;
        char[] inputBuffer = new char[inputBufferSize];

        int actualIncomingQuantity = inputStream.read(inputBuffer, offset, expectedIncomingQuantity);

        checkIncomingQuantity(actualIncomingQuantity);
        final int receivedItemIndex = 0;
        return inputBuffer[receivedItemIndex];
    }

    private void checkIncomingQuantity(int incomingQuantity) {
        final int endOfStream = -1;
        final int expectedIncomingQuantity = 1;
        if (incomingQuantity == endOfStream) {
            throw new WrongIncomingDataFormat("unexpected end of stream reached");
        }
        if (incomingQuantity != expectedIncomingQuantity) {
            throw new NeedFixCode("wrong number of characters read");
        }
        return;
    }

    private int convertToken(String token) {
        int tokenAsInt;
        try {
            tokenAsInt = Integer.valueOf(token);
        } catch (NumberFormatException exception) {
            throw new WrongIncomingDataFormat("expected numeric token, received crap");
        }
        return tokenAsInt;
    }
}