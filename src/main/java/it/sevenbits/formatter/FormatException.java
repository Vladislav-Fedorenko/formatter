
package it.sevenbits.formatter;

/**
 * The exception thrown when any occur formatting errors.
 */
public class FormatException extends Throwable {
    /**
     * Constructor.
     * @param message message of exception
     * @param e is incoming param
     */
    public FormatException(final String message, final Throwable e) {
        super(message, e);
    }
}
