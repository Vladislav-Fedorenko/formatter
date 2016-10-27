package writer;

import closer.CloserException;

/**
 * The interface for write in different sources.
 */
public interface Writable {
    /**
     * Write string.
     * @param s input string
     * @throws WriterException thrown if any errors occur writing
     * @throws CloserException thrown if any errors occur closing
     */
    void write(String s) throws WriterException, CloserException;
}
