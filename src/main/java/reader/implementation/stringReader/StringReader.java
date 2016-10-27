package reader.implementation.stringReader;

import reader.Readable;
import reader.ReaderException;


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
    private int indexOfArray;
    /**
     * Constructor for the string.
     * @param inputString the input string
     */
    public StringReader(final String inputString) {
        this.inputString = inputString.toCharArray();
        this.indexOfArray = 0;
    }

    @Override
    public char read() throws ReaderException {
        indexOfArray++;
        return inputString[indexOfArray - 1];
    }

    @Override
    public boolean isEnd() throws ReaderException {
        return !(indexOfArray < inputString.length);
    }
}
