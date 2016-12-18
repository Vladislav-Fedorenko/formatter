package it.sevenbits.handler.lexer.action.implementation;

import it.sevenbits.handler.lexer.action.Action;
import it.sevenbits.reader.implementation.lexer.token.TokenBuilder;

/**
 * Class for return lexeme.
 */
public class ReturnAction implements Action {
    private boolean returnToken;

    @Override
    public void execute(final TokenBuilder tokenBuilder, final char c) {
        tokenBuilder.setTokenForReturn();
        tokenBuilder.append(c);
        returnToken = true;
    }

    @Override
    public boolean returnToken() {
        return returnToken;
    }
}
