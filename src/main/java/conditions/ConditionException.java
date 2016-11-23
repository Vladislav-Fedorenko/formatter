package conditions;

/**
 * The exception thrown when any occur errors of condition.
 */
public class ConditionException extends Throwable {
    /**
     * Constructor
     * @param e is incoming param
     * @param message message of exception
     */
    public ConditionException(final String message, final Throwable e) {
        super(message, e);
    }
}
