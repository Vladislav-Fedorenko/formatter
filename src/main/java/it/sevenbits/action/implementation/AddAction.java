package it.sevenbits.action.implementation;

import it.sevenbits.action.Action;
import it.sevenbits.reader.implementation.lexer.token.TokenBuilder;

/**
 * Class for add char in lexeme.
 */
public class AddAction implements Action {
    private boolean returnToken;

    @Override
    public void execute(final TokenBuilder tokenBuilder, final char c) {
        tokenBuilder.append(c);
        returnToken = false;
    }

    @Override
    public boolean returnToken() {
        return returnToken;
    }
}
