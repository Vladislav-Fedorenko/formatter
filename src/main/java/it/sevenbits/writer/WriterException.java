package it.sevenbits.writer;

/**
 * The exception thrown when any occur write errors
 */
public class WriterException extends Throwable {
    /**
     * Constructor.
     * @param message message of exception
     * @param e is incoming param
     */
    public WriterException(final String message, final Throwable e) {
        super(message, e);
    }
}
