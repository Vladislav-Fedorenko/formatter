package it.sevenbits.states;

/**
 * The exception thrown when any occur errors of state.
 */
public class StateException extends Throwable {
    /**
     * Constructor
     * @param e is incoming param
     * @param message message of exception
     */
    public StateException(final String message, final Throwable e) {
        super(message, e);
    }
}
