
package formatter;

/**
 * The exception thrown when any occur formatting errors
 */
public class FormatException extends Throwable {
    /**
     * Constructor.
     * @param e is incoming param
     */
    public FormatException(final Throwable e) {
        initCause(e);
        System.out.println(e.getMessage());
    }
}
