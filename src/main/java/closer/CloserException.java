
package closer;
/**
 * The exception thrown then any occur close errors
 */
public class CloserException extends Throwable {
    /**
     * Constructor.
     * @param e is incoming param
     */
    public CloserException(final Throwable e) {
        initCause(e);
    }
}
