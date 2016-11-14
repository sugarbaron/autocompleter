package ru.sugarbaron_bicycles.autocompleter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;

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
}