
package writer.implementation.fileWriter;

import closer.Closeable;
import closer.CloserException;
import writer.Writable;
import writer.WriterException;

import java.io.*;

/**
 * The class for write thread in file
 */
public class FileWriter implements Writable, Closeable {

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
            throw new WriterException(e);
        }
    }
    @Override
    public final void writeChar(final char c) throws WriterException {
        try {
            outputFile.write(c);
        } catch (IOException e) {
            try {
                this.close();
            } catch (CloserException e1) {
                throw new WriterException(e1);
            }
            throw new WriterException(e);
        }
    }

    @Override
    public void close() throws CloserException {
        try {
            outputFile.close();
        } catch (IOException e) {
            throw new CloserException(e);
        }
    }
}
