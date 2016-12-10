package it.sevenbits.reader.implementation.filereader;

import it.sevenbits.closer.Closeable;
import it.sevenbits.closer.CloserException;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;

import java.io.InputStream;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.IOException;


/**
 * The class for work with the thread from the file
 */
public class FileReader implements Readable<Character>, Closeable {
    /**
     * The input stream.
     */
    private Reader fileReader;
    private int endOfFile = 0;

    /**
     * The constructor for the string
     * @param fileName name of the input file
     * @throws ReaderException generated if a file not found
     */
    public FileReader(final String fileName) throws ReaderException {
        try {
            InputStream fileStream =
                    new FileInputStream(new File(fileName));
            fileReader =
                    new InputStreamReader(fileStream, "utf-8");
        } catch (FileNotFoundException e) {
            throw new ReaderException("error create stream InputStream in FileReader", e);
        } catch (UnsupportedEncodingException e) {
            throw new ReaderException("error create stream Reader in FileReader", e);
        }
    }

    @Override
    public void close() throws CloserException {
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new CloserException("error close in FileReader", e);
        }
    }

    @Override
    public Character read() throws ReaderException {
        try {
            endOfFile = fileReader.read();
            return (char) endOfFile;
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
        return (endOfFile == -1);
    }
}

