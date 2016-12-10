package it.sevenbits.action.implementation;

import it.sevenbits.action.Action;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.implementation.lexer.token.Token;

/**
 * Class for skip char.
 */
public class SkipAction implements Action {
    @Override
    public String execute(final Readable<Token> in, final char c) {
        return null;
    }
}
