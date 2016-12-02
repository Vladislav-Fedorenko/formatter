package it.sevenbits.states;

import it.sevenbits.writer.Writable;

/**
 * Interface for executing anything.
 */
public interface IState {
    /**
     * Write the char in the output stream given a state of code
     * @param c input character
     * @param out output stream
     * @throws StateException thrown if any errors occur state
     */
    void execute(Writable<Character> out, char c) throws StateException;
}
