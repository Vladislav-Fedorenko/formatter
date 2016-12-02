
package it.sevenbits.writer.implementation.fileWriter;

import it.sevenbits.closer.Closeable;
import it.sevenbits.closer.CloserException;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * The class for write thread in file
 */
public class FileWriter implements Writable<Character>, Closeable {

    /**
     * The output file.
     */
    private OutputStream outputFile;

    /**
     * The constructor for the string
     * @param fileName is name of output file
     * @throws WriterException thrown if any errors occur writing
     */
    public FileWriter(final String fileName) throws WriterException {
        try {
            outputFile = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new WriterException("error of create stream in FileWriter", e);
        }
    }
    @Override
    public final void write(final Character c) throws WriterException {
        try {
            outputFile.write(c);
        } catch (IOException e) {
            try {
                this.close();
            } catch (CloserException e1) {
                throw new WriterException("error of write in FileWriter(try close stream)", e1);
            }
            throw new WriterException("error of write in FileWriter", e);
        }
    }

    @Override
    public void close() throws CloserException {
        try {
            outputFile.close();
        } catch (IOException e) {
            throw new CloserException("error of close in FileWriter", e);
        }
    }
}
