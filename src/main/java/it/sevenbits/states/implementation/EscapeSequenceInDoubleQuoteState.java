package it.sevenbits.states.implementation;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.states.IState;
import it.sevenbits.states.StateException;
import it.sevenbits.states.StateManager;
import it.sevenbits.writer.Writable;

/**
 * Class code in a state of escape sequence in double quote.
 */
public class EscapeSequenceInDoubleQuoteState implements IState {
    private StateManager stateManager;
    private Handler handler;

    /**
     * Constructor
     * @param stateManager state manager
     */
    public EscapeSequenceInDoubleQuoteState(final StateManager stateManager) {
        this.stateManager = stateManager;
        handler = null;
    }

    @Override
    public void execute(final Writable<Character> out, final char c) throws StateException {
        try {
            handler = stateManager.getHandler(this, c);
            handler.handle(out, null, c);
        } catch (HandlerException e) {
            throw new StateException("error of handle in EscapeSequenceInSingleQuoteState", e);
        }
    }
}
