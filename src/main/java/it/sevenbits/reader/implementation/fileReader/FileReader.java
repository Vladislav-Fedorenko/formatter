package it.sevenbits.reader.implementation.fileReader;

import it.sevenbits.closer.Closeable;
import it.sevenbits.closer.CloserException;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



/**
 * The class for work with the thread from the file
 */
public class FileReader implements Readable<Character>, Closeable {
    /**
     * The input stream
     */
    private InputStream fileStream;

    /**
     * The constructor for the string
     * @param fileName name of the input file
     * @throws ReaderException generated if a file not found
     */
    public FileReader(final String fileName) throws ReaderException {
        try {
            fileStream = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new ReaderException("error create InputStream in FileReader", e);
        }
    }

    @Override
    public void close() throws CloserException {
        try {
            fileStream.close();
        } catch (IOException e) {
            throw new CloserException("error close in FileReader", e);
        }
    }

    @Override
    public Character read() throws ReaderException {
        try {
            return (char) fileStream.read();
        } catch (IOException e) {
            try {
                this.close();
            } catch (CloserException e1) {
                throw new ReaderException("error of read in FileReader(try close stream)", e1);
            }
            throw new ReaderException("error of read in FileReader", e);
        }
    }

    @Override
    public boolean isEnd() throws ReaderException {
        try {
            return !(fileStream.available() > 0);
        } catch (IOException e) {
            try {
                this.close();
            } catch (CloserException e1) {
                throw new ReaderException("error of read in FileReader(try close stream)", e1);
            }
            throw new ReaderException("error of read in FileReader", e);
        }
    }
}

