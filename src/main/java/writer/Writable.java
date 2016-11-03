package writer;

import closer.CloserException;

import java.io.IOException;

/**
 * The interface for write in different sources.
 */
public interface Writable {
    /**
     * Write char.
     * @param c input char
     * @throws WriterException thrown if any errors occur writing
     * @throws CloserException thrown if any errors occur closing
     */
    void writeChar(char c) throws WriterException, CloserException;
}
