
package it.sevenbits.closer;

/**
 * The interface for close from different sources.
 */
public interface Closeable {

    /**
     * Closes stream
     * @throws CloserException thrown if any errors occur closing
     */
    void close() throws CloserException;
}
