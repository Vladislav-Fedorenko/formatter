
package it.sevenbits.writer.implementation.filewriter;

import it.sevenbits.closer.Closeable;
import it.sevenbits.closer.CloserException;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;


/**
 * The class for write thread in file
 */
public class FileWriter implements Writable<String>, Closeable {

    /**
     * The output stream.
     */
    private Writer fileWriter;

    /**
     * The constructor for the string
     * @param fileName is name of output file
     * @throws WriterException thrown if any errors occur writing
     */
    public FileWriter(final String fileName) throws WriterException {
        try {
            OutputStream fileStream =
                    new FileOutputStream(new File(fileName));
            fileWriter =
                    new OutputStreamWriter(fileStream, "utf-8");
        } catch (FileNotFoundException e) {
            throw new WriterException("error of create stream OutputStream in FileWriter", e);
        } catch (UnsupportedEncodingException e) {
            throw new WriterException("error of create stream Writer in FileWriter", e);
        }
    }
    @Override
    public final void write(final String s) throws WriterException {
        try {
            fileWriter.write(s);
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
            fileWriter.close();
        } catch (IOException e) {
            throw new CloserException("error of close in FileWriter", e);
        }
    }
}
