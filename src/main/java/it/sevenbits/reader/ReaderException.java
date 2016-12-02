package it.sevenbits.reader;

/**
 * The exception thrown when any occur read errors
 */
public class ReaderException extends Throwable {
    /**
     * Constructor.
     * @param message message of exception
     * @param e is incoming param
     */
    public ReaderException(final String message, final Throwable e) {
        super(message, e);
    }
}
