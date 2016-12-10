package it.sevenbits.action;

import it.sevenbits.reader.Readable;
import it.sevenbits.reader.implementation.lexer.token.Token;

/**
 * Interface for anything action.
 */
public interface Action {
    /**
     * Method for add char in current token
     * @param in stream containing token
     * @param c input character
     * @return token
     */
    String execute(Readable<Token> in, char c);
}
