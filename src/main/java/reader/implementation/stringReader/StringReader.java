package reader.implementation.stringReader;

import reader.Readable;


/**
 * The class for work with the thread from the file
 */
public class StringReader implements Readable {
    /**
     * The input string.
     */
    private char[] inputString;
    /**
     * The current position in the array characters.
     */
    private int indexOfArray = 0;
    /**
     * Constructor for the string.
     * @param inputString the input string
     */
    public StringReader(final String inputString) {
        this.inputString = inputString.toCharArray();
    }

    @Override
    public char readChar() {
        indexOfArray++;
        return inputString[indexOfArray - 1];
    }

    @Override
    public boolean isEnd() {
        return !(indexOfArray < inputString.length);
    }
}
