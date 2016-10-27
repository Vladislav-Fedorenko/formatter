package writer;

/**
 * The exception thrown when any occur write errors
 */
public class WriterException extends Throwable {
    /**
     * Constructor.
     * @param e is incoming param
     */
    public WriterException(final Throwable e) {
        initCause(e);
    }
}
