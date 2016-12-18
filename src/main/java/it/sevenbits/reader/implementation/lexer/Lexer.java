package it.sevenbits.reader.implementation.lexer;

import it.sevenbits.action.Action;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.reader.implementation.lexer.token.TokenBuilder;
import it.sevenbits.states.State;
import it.sevenbits.states.lexer.StateOfLexerManager;

/**
 * Lexical analyzer.
 */
public class Lexer implements Readable<Token> {
    private Readable<Character> in;
    private StateOfLexerManager stateOfLexerManager;
    private State state;
    private TokenBuilder tokenBuilder;

    /**
     * Constructor
     * @param in input stream for read.
     */
    public Lexer(final Readable<Character> in) {
        this.in = in;
        stateOfLexerManager = new StateOfLexerManager();
        state = stateOfLexerManager.getInitialState();
        tokenBuilder = new TokenBuilder(new StringBuilder());
    }

    @Override
    public Token read() throws ReaderException {
        Action action;
        char readChar;
        String currentLexeme;
        /*write map for state(return with repeat*/
        if (state.equals(new State("return with repeat"))) {
            currentLexeme = tokenBuilder.toString();
            tokenBuilder.setTokenForReturn();
            state = stateOfLexerManager.getInitialState();
            return new Token(currentLexeme);
        }
        while (!in.isEnd()) {
            readChar = in.read();
            action = stateOfLexerManager.getAction(state, readChar);
            action.execute(tokenBuilder, readChar);
            state = stateOfLexerManager.getNextState(state, readChar);
            if (action.returnToken()) {
                return new Token(tokenBuilder.getStringForReturn());
            }
        }
        return new Token(tokenBuilder.toString());
    }
    @Override
    public boolean isEnd() throws ReaderException {
        return in.isEnd();
    }


}
