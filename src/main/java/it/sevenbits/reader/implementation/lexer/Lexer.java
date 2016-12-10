package it.sevenbits.reader.implementation.lexer;

import it.sevenbits.action.Action;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.states.State;
import it.sevenbits.states.lexer.StateOfLexerManager;

/**
 * Lexical analyzer.
 */
public class Lexer implements Readable<Token> {
    private Readable<Character> in;
    private StringBuilder lexeme = new StringBuilder();
    private StateOfLexerManager stateOfLexerManager;
    private State state;

    /**
     * Constructor
     * @param in input stream for read.
     */
    public Lexer(final Readable<Character> in) {
        this.in = in;
        stateOfLexerManager = new StateOfLexerManager();
        state = stateOfLexerManager.getInitialState();
    }

    @Override
    public Token read() throws ReaderException {
        Action action;
        char readChar = 0;
        String currentLexeme;
        if (state.equals(new State("return with repeat"))) {
            currentLexeme = this.getLexeme();
            this.newLexeme(readChar);
            state = stateOfLexerManager.getInitialState();
            return new Token(currentLexeme);
        }
        while (!in.isEnd()) {
            readChar = in.read();
            action = stateOfLexerManager.getAction(state, readChar);
            currentLexeme = action.execute(this, readChar);
            state = stateOfLexerManager.getNextState(state, readChar);
            if (currentLexeme != null) {
                return new Token(currentLexeme);
            }
        }
        return new Token(lexeme.toString());
    }
    @Override
    public boolean isEnd() throws ReaderException {
        return in.isEnd();
    }

    /**
     * call method StringBuilder.append(char c)
     * @param c input char
     */
    public void append(final char c) {
        lexeme.append(c);
    }

    /**
     * Method create new lexeme
     * @param c input char
     */
    public void newLexeme(final char c) {
        lexeme = new StringBuilder("" + c);
    }

    /**
     * Get lexeme
     * @return current lexeme
     */
    public String getLexeme() {
        return lexeme.toString();
    }

}
