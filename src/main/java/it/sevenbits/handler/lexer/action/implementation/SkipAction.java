package it.sevenbits.handler.lexer.action.implementation;

import it.sevenbits.handler.lexer.action.Action;
import it.sevenbits.reader.implementation.lexer.token.TokenBuilder;

/**
 * Class for skip char.
 */
public class SkipAction implements Action {
    private boolean returnToken;

    @Override
    public void execute(final TokenBuilder tokenBuilder, final char c) {
        returnToken = false;
    }

    @Override
    public boolean returnToken() {
        return returnToken;
    }
}
