package reader;

/**
 * The interface for read from different sources.
 */
public interface Readable {
    /**
     * Reads one character
     * @return read character
     * @throws ReaderException thrown if any errors occur reading
     */
    char readChar() throws ReaderException;

    /**
     * Checks on the end
     * @return true if it is end of the stream, false else
     * @throws ReaderException thrown if any errors occur reading
     */
    boolean isEnd() throws ReaderException;
}
