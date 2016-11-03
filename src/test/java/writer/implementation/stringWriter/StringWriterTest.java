package writer.implementation.stringWriter;

import closer.CloserException;
import org.junit.Before;
import org.junit.Test;
import writer.Writable;
import writer.WriterException;

import static org.junit.Assert.assertEquals;

/**
 * Test for StringWriter.
 */
public class StringWriterTest {
    private Writable writer;

    @Before
    public void setUp() {
        writer = new StringWriter("");
    }

    @Test
    public void writeCharAndToStringTest() throws CloserException, WriterException {
        writer.writeChar('c');
        writer.writeChar('h');
        writer.writeChar('a');
        writer.writeChar('r');
        String expected = "char";
        assertEquals("wrong result", expected, writer.toString());
    }
}
