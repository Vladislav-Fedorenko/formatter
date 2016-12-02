package it.sevenbits.writer;

/**
 * The interface for write in different sources.
 * @param <T> parameter of write
 */
public interface Writable<T> {
    /**
     * Write char.
     * @param param input parameter
     * @throws WriterException thrown if any errors occur writing
     */
    void write(T param) throws WriterException;
}
