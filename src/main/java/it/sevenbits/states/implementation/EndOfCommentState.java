package it.sevenbits.states.implementation;

import it.sevenbits.states.StateException;
import it.sevenbits.states.IState;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Class code in a state of end of comment.
 */
public class EndOfCommentState implements IState {

    @Override
    public void execute(final Writable<Character> out, final char c) throws StateException {
        try {
            out.write(c);
            out.write('\n');
        } catch (WriterException e) {
            throw new StateException("error of write in EndOfState", e);
        }
    }
}
