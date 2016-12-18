package it.sevenbits.handler.lexer.action;

import it.sevenbits.reader.implementation.lexer.token.TokenBuilder;

/**
 * Interface for anything action.
 */
public interface Action {
    /*transfer in package handler*/
    /**
     * Method for add char in current token
     * @param tokenBuilder class containing token
     * @param c input character
     */
    void execute(TokenBuilder tokenBuilder, char c);
    /*replacement Readable<Token> on  StringBuilder*/

    /**
     * method-indicator of the end of token
     * @return true if is end of token
     *          false else
     */
    boolean returnToken();
}
