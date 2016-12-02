package it.sevenbits.states.implementation;

import it.sevenbits.states.StateException;
import it.sevenbits.states.IState;
import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.states.StateManager;
import it.sevenbits.writer.Writable;

/**
 * Class for default state of code.
 */
public class DefaultState implements IState {

    private Handler handler;
    private StateManager stateManager;
    private Indent indent;
    /**
     * Constructor
     * @param indent current indent
     * @param stateManager map of states
     */
    public DefaultState(final Indent indent, final StateManager stateManager) {
        this.stateManager = stateManager;
        handler = null;
        this.indent = indent;
    }
    @Override
    public void execute(final Writable<Character> out, final char c) throws StateException {
        try {
            handler = stateManager.getHandler(this, c);
            handler.handle(out, indent, c);
        } catch (HandlerException e) {
            throw new StateException("error of handler in DefaultState", e);
        }
    }
}
