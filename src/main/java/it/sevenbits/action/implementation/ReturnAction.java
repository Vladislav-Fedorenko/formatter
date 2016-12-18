package it.sevenbits.action.implementation;

import it.sevenbits.action.Action;
import it.sevenbits.reader.implementation.lexer.token.TokenBuilder;

/**
 * Class for return lexeme.
 */
public class ReturnAction implements Action {
    private boolean returnToken;
    /*@Override
    public String execute(final Readable<Token> in, final char c) {
        Lexer lexer = (Lexer) in;
        String currentValue = lexer.getLexeme();
        lexer.newLexeme(c);
        return currentValue;
    }*/

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
