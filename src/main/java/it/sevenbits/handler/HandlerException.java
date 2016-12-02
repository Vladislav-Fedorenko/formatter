package it.sevenbits.handler;

/**
 * The exception thrown when any occur handling errors.
 */
public class HandlerException extends Throwable {
    /**
     * Constructor
     * @param message message of exception
     * @param e is incoming param
     */
    public HandlerException(final String message, final Throwable e) {
        super(message, e);
    }
}
