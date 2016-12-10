package it.sevenbits.action.implementation;

import it.sevenbits.action.Action;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.implementation.lexer.Lexer;
import it.sevenbits.reader.implementation.lexer.token.Token;

/**
 * Class for return lexeme.
 */
public class ReturnAction implements Action {
    @Override
    public String execute(final Readable<Token> in, final char c) {
        Lexer lexer = (Lexer) in;
        String currentValue = lexer.getLexeme();
        lexer.newLexeme(c);
        return currentValue;
    }
}
