package writer.implementation.fileWriter;

import closer.CloserException;
import org.junit.Before;
import org.junit.Test;
import writer.WriterException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for FileWriter.
 */
public class FileWriterTest {
    private FileWriter writer;
    private Reader reader;

    @Before
    public void setUp() throws CloserException, WriterException, IOException {
        File file = File.createTempFile("input", "txt");
        writer = new FileWriter(file.getAbsolutePath());
        reader = new FileReader(file.getAbsolutePath()); //java.io.FileReader
    }

    @Test
    public void writeCharTest() throws CloserException, WriterException, IOException {
        writer.writeChar('{');
        char actual = (char) reader.read();
        char expected = '{';
        assertEquals("wrong result", expected, actual);
    }

    @Test(expected = WriterException.class)
    public void closeAndWriteCharWithExceptionTest() throws CloserException, WriterException {
        writer.close();
        writer.writeChar('}');
        fail();
    }

   /* @Test(expected = CloserException.class)
    public void closeWithException() throws CloserException {
        writer.close();
        writer.close();
        fail();
    }*/
}


