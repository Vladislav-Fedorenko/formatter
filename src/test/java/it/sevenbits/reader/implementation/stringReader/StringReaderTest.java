package it.sevenbits.reader.implementation.stringReader;

import org.junit.Before;
import org.junit.Test;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for StringReader.
 */
public class StringReaderTest {

    private Readable<Character> reader;

    @Before
    public void setUp() {
        reader = new StringReader("string");
    }

    @Test
    public void readCharTest() throws ReaderException {
        char expected = 's';
        char result = reader.read();
        assertEquals("wrong result", expected, result);
    }

    @Test
    public void isEndTrueTest() throws ReaderException {
        assertTrue("wrong result", !reader.isEnd());
    }


}
