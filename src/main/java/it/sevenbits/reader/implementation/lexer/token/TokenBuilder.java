package it.sevenbits.reader.implementation.lexer.token;

/**
 * Class for storage string builder for token.
 */
public class TokenBuilder {
    private StringBuilder tokenBuilder;
    private String stringForReturn;

    /**
     * constructor
     * @param tokenBuilder current token
     */
    public TokenBuilder(final StringBuilder tokenBuilder) {
        this.tokenBuilder = tokenBuilder;
    }

    /**
     * call method StringBuilder.append(char c);
     * @param c char for append
     */
    public void append(final char c) {
        tokenBuilder.append(c);
    }

    /**
     * Method set token for return
     */
    public void setTokenForReturn() {
        stringForReturn = tokenBuilder.toString();
        tokenBuilder = new StringBuilder("");
    }

    public String getStringForReturn() {
        return stringForReturn;
    }

    @Override
    public String toString() {
        return tokenBuilder.toString();
    }
}
