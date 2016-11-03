
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
     * @throws CloserException thrown if any errors occur closing
     */
    public FileWriter(final String fileName) throws WriterException, CloserException {
        try {
            outputFile = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new WriterException(e);
        }
    }
    @Override
    public final void writeChar(final char c) throws WriterException, CloserException {
        try {
            outputFile.write(c);
        } catch (IOException e) {
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
