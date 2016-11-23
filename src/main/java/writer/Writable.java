package writer;

/**
 * The interface for write in different sources.
 */
public interface Writable {
    /**
     * Write char.
     * @param c input char
     * @throws WriterException thrown if any errors occur writing
     */
    void writeChar(char c) throws WriterException;
}
