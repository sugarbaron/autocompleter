package ru.sugarbaron_bicycles.autocompleter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import ru.sugarbaron_bicycles.autocompleter.exceptions.WrongIncomingDataFormat;

public final class TokenExtractorTest {
    @Test
    public void extractAllTokens() throws IOException {
        try ( FileInputStream input = new FileInputStream("test_data/positiveCase.in") ) {
            tryToExtractAllTokens(input);
        }
        return;
    }

    private void tryToExtractAllTokens(FileInputStream input) {
        TokenExtractor extractor = new TokenExtractor(input);
        int actualWordsQuantity = extractor.nextTokenAsNumber();
        assertTrue( actualWordsQuantity == 5 );

        String word1 = extractor.nextTokenAsText();
        assertTrue( word1.equals("kare") );

        int frequency1 = extractor.nextTokenAsNumber();
        assertTrue( frequency1 == 10 );

        String word2 = extractor.nextTokenAsText();
        assertTrue( word2.equals("kanojo") );

        int frequency2 = extractor.nextTokenAsNumber();
        assertTrue( frequency2 == 1000000 );

        String word3 = extractor.nextTokenAsText();
        assertTrue( word3.equals("karetachi") );

        int frequency3 = extractor.nextTokenAsNumber();
        assertTrue( frequency3 == 1 );

        String word4 = extractor.nextTokenAsText();
        assertTrue( word4.equals("korosu") );

        int frequency4 = extractor.nextTokenAsNumber();
        assertTrue( frequency4 == 7 );

        String word5 = extractor.nextTokenAsText();
        assertTrue( word5.equals("sakura") );

        int frequency5 = extractor.nextTokenAsNumber();
        assertTrue( frequency5 == 3 );

        int prefixesQuantity = extractor.nextTokenAsNumber();
        assertTrue( prefixesQuantity == 3 );

        String prefix1 = extractor.nextTokenAsText();
        assertTrue( prefix1.equals("k") );

        String prefix2 = extractor.nextTokenAsText();
        assertTrue( prefix2.equals("ka") );

        String prefix3 = extractor.nextTokenAsText();
        assertTrue( prefix3.equals("kar") );
        return;
    }

    @Test
    public void nextTokenAsText() throws IOException {
        try ( FileInputStream input = new FileInputStream("test_data/singleTextTokenCase.in") ) {
            tryToExtractTextToken(input);
        }
        return;
    }

    @Test
    public void nextTokenAsText_noNewLine() throws IOException {
        try ( FileInputStream in = new FileInputStream("test_data/noNewLineAfterTextCase.in") ) {
            tryToExtractTextToken(in);
        }
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void nextTokenAsText_endOfStream() throws IOException {
        try ( FileInputStream input = new FileInputStream("test_data/endOfStreamCase.in") ) {
            tryToExtractTextToken(input);
        }
        return;
    }

    private void tryToExtractTextToken(FileInputStream input) throws IOException {
        TokenExtractor extractor = new TokenExtractor(input);

        String token = extractor.nextTokenAsText();

        assertTrue( token.equals("metallica") );
        return;
    }

    @Test
    public void nextTokenAsNumber() throws IOException {
        try ( FileInputStream input = new FileInputStream("test_data/singleNumericTokenCase.in") ) {
            tryToExtractNumericToken(input);
        }
        return;
    }

    @Test
    public void nextTokenAsNumber_noNewLine() throws IOException {
        try ( FileInputStream input = new FileInputStream("test_data/noNewLineAfterNumCase.in") ) {
            tryToExtractNumericToken(input);
        }
        return;
    }

    @Test(expected = WrongIncomingDataFormat.class)
    public void nextTokenAsNumber_endOfStream() throws IOException {
        try ( FileInputStream input = new FileInputStream("test_data/endOfStreamCase.in") ) {
            tryToExtractNumericToken(input);
        }
        return;
    }

    private void tryToExtractNumericToken(FileInputStream in) throws IOException {
        TokenExtractor extractor = new TokenExtractor(in);

        int token = extractor.nextTokenAsNumber();

        assertTrue( token == 5 );
        return;
    }
}