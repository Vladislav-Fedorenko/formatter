package it.sevenbits.formatter.implementation;


import it.sevenbits.states.StateException;
import it.sevenbits.states.StateManager;
import it.sevenbits.states.IState;
import it.sevenbits.formatter.FormatException;
import it.sevenbits.formatter.Formatable;
import it.sevenbits.handler.Indent;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.writer.Writable;

/**
 * The class for read, format from input streams data and write to output stream
 */
public class Formatter implements Formatable {

    /**
     * Constructor.
     */
    public Formatter() {
    }
    @Override
    public void format(final Readable<Character> in, final Writable<Character> out) throws FormatException {
        try {
            Indent indent = new Indent();
            StateManager stateManager = new StateManager(indent);
            IState state = stateManager.getInitialState();
            Character readChar;
            while (!in.isEnd()) {
                readChar = in.read();
                state.execute(out, readChar);
                state = stateManager.getNextState(state, readChar);
            }
            if (indent.getCurrentIndent() != 0) {
                throw new StateException("error handle: incorrect number of braces", new Throwable());
            }
        } catch (ReaderException e) {
            throw new FormatException("error of read in Formatter", e);
        } catch (StateException e) {
            throw new FormatException("error of state in Formatter", e);
        }
    }
}
