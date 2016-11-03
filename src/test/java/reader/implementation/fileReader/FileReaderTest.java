package reader.implementation.fileReader;

import closer.CloserException;
import org.junit.Before;
import org.junit.Test;
import reader.ReaderException;
import writer.WriterException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;

/**
 * Test for FileReader.
 */
public class FileReaderTest {

    private FileReader reader;
    private OutputStream writer;

    @Before
    public void setUp() throws IOException, ReaderException, CloserException, WriterException {
        File file = File.createTempFile("output", "txt");
        writer = new FileOutputStream(file.getAbsolutePath());
        reader = new FileReader(file.getAbsolutePath());
    }

    @Test(expected = ReaderException.class)
    public void constructorTest() throws ReaderException {
        reader = new FileReader("NotFoundFile.txt");
        fail();
    }
    @Test
    public void readCharTest() throws IOException, ReaderException, CloserException, WriterException {
        writer.write('c');
        char expected = 'c';
        char actual = reader.readChar();
        assertEquals("wrong", expected, actual);
    }

    @Test
    public void isEndTrueTest() throws IOException, ReaderException {
        writer.write('i');
        reader.readChar();
        assertTrue("wrong result", reader.isEnd());
    }

    @Test
    public void isEndFalseTest() throws IOException, ReaderException {
        writer.write('i');
        assertTrue("wrong result", !reader.isEnd());
    }

    @Test(expected = ReaderException.class)
    public void isEndWithExceptionTest() throws CloserException, ReaderException {
        reader.close();
        reader.isEnd();
        fail();
    }

    @Test(expected = ReaderException.class)
    public void closeAndReadCharWithExceptionTest() throws CloserException, ReaderException {
        reader.close();
        reader.readChar();
        fail();
    }


    /*@Test(expected = CloserException.class)
    public void closeWithExceptionTest() throws CloserException {
        reader.close();
        reader.close();
        fail();
    }*/
}
