package it.sevenbits.handler;

/**
 * Class for storage info about current indent.
 */
public class Indent {

    private int currentIndent;
    private static final int SPACES = 4;

    /**
     * Default constructor
     */
    public Indent() {
        currentIndent = 0;
    }

    /**
     * increment current indent
     */
    public void incCurrentIndent() {
        currentIndent++;
    }
    /**
     * decrement current indent
     */
    public void decCurrentIndent() {
        currentIndent--;
    }

    public int getCurrentIndent() {
        return currentIndent;
    }

    public int getSpaces() {
        return SPACES;
    }
}
