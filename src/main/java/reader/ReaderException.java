package reader;

/**
 * The exception thrown when any occur read errors
 */
public class ReaderException extends Throwable {
    /**
     * Constructor.
     * @param e is incoming param
     */
    public ReaderException(final Throwable e) {
        initCause(e);
    }
}
