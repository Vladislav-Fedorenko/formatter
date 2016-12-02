package it.sevenbits.writer.implementation.fileWriter;

import it.sevenbits.closer.CloserException;
import org.junit.Before;
import org.junit.Test;
import it.sevenbits.writer.WriterException;

import java.io.*;

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

    @Test(expected = WriterException.class)
    public void constructorTest() throws CloserException, WriterException {
        writer = new FileWriter(".");
        fail();
    }
    @Test
    public void writeCharTest() throws CloserException, WriterException, IOException {

        writer.write('{');
        char actual = (char) reader.read();
        char expected = '{';
        assertEquals("wrong result", expected, actual);
    }

    @Test(expected = WriterException.class)
    public void closeAndWriteCharWithExceptionTest() throws CloserException, WriterException {
        writer.close();
        writer.write('}');
        fail();
    }

}


