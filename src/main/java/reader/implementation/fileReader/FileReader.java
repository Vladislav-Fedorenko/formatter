package reader.implementation.fileReader;

import closer.Closeable;
import closer.CloserException;

import reader.Readable;
import reader.ReaderException;

import java.io.*;

/**
 * The class for work with the thread from the file
 */
public class FileReader implements Readable, Closeable {
    /**
     * The input file
     */
    private InputStream inputFile;

    /**
     * The constructor for the string
     * @param fileName name of the input file
     * @throws ReaderException generated if a file not found
     */
    public FileReader(final String fileName) throws ReaderException {
        try {
            inputFile = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new ReaderException(e);
        }
    }

    @Override
    public void close() throws CloserException {
        try {
            inputFile.close();
        } catch (IOException e) {
            throw new CloserException(e);
        }
    }

    @Override
    public char readChar() throws ReaderException {
        try {
            return (char) inputFile.read();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    @Override
    public boolean isEnd() throws ReaderException {
        try {
            return !(inputFile.available() > 0);
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}

