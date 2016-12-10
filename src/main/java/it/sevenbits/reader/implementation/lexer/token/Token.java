package it.sevenbits.reader.implementation.lexer.token;

/**
 * Class for storage lexeme.
 */
public class Token {
    private String lexeme;

    /**
     * Constructor
     * @param lexeme value of lexeme
     */
    public Token(final String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Token token = (Token) o;

        return lexeme != null ? lexeme.equals(token.lexeme) : token.lexeme == null;
    }

    @Override
    public int hashCode() {
        return lexeme != null ? lexeme.hashCode() : 0;
    }

    /**
     * Method for get the lexeme
     * @return current lexeme
     */
    public String getLexeme() {
        return lexeme;
    }
}
