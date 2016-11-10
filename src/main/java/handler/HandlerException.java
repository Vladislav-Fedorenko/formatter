package handler;

/**
 * The exception thrown when any occur handling errors.
 */
public class HandlerException extends Throwable {
    /**
     * Constructor
     * @param e is incoming param
     */
    public HandlerException(final Throwable e) {
        initCause(e);
    }
}
