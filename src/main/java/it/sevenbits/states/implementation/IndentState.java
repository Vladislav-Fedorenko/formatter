package it.sevenbits.states.implementation;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.states.IState;
import it.sevenbits.states.StateException;
import it.sevenbits.states.StateManager;
import it.sevenbits.writer.Writable;

/**
 * Class code in a state of after '{' and ';'.
 */
public class IndentState implements IState {
    private Indent indent;
    private StateManager stateManager;
    private Handler handler;

    /**
     * Constructor
     * @param indent current indent
     * @param stateManager map of state
     */
    public IndentState(final Indent indent, final StateManager stateManager) {
        this.stateManager = stateManager;
        this.indent = indent;
        handler = null;
    }
    @Override
    public void execute(final Writable<Character> out, final char c) throws StateException {
        try {
            handler = stateManager.getHandler(this, c);
            handler.handle(out, indent, c);
        } catch (HandlerException e) {
            throw new StateException("errors of handler in IndentState", e);
        }
    }
}
