package it.sevenbits.writer.implementation.stringWriter;

import it.sevenbits.closer.CloserException;
import org.junit.Before;
import org.junit.Test;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

import static org.junit.Assert.assertEquals;

/**
 * Test for StringWriter.
 */
public class StringWriterTest {
    private Writable<Character> writer;

    @Before
    public void setUp() {
        writer = new StringWriter("");
    }

    @Test
    public void writeCharAndToStringTest() throws CloserException, WriterException {
        writer.write('c');
        writer.write('h');
        writer.write('a');
        writer.write('r');
        String expected = "char";
        assertEquals("wrong result", expected, writer.toString());
    }
}
