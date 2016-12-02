package it.sevenbits.reader;

/**
 * The interface for read from different sources.
 * @param <T> parameter of read
 */
public interface Readable <T> {
    /**
     * Reads one parameter
     * @return read parameter
     * @throws ReaderException thrown if any errors occur reading
     */
    T read() throws ReaderException;

    /**
     * Checks on the end
     * @return true if it is end of the stream, false else
     * @throws ReaderException thrown if any errors occur reading
     */
    boolean isEnd() throws ReaderException;
}
