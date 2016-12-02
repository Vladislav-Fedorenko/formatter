
package it.sevenbits.closer;
/**
 * The exception thrown then any occur close errors
 */
public class CloserException extends Throwable {
    /**
     * Constructor.
     * @param message message of exception
     * @param e is incoming param
     */
    public CloserException(final String message, final Throwable e) {
        super(message, e);
    }
}
