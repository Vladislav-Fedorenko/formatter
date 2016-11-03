
package writer.implementation.stringWriter;

import writer.Writable;

/**
 * The class for write thread in string
 */
public class StringWriter implements Writable {
    /**
     * tool for work with string
     */
    private StringBuilder stringBuilder;

    /**
     * Constructor for the string.
     * @param inputString the input string for constructor
     */
    public StringWriter(final String inputString) {
        stringBuilder = new StringBuilder(inputString);
    }

    @Override
    public final void writeChar(final char c) {
        stringBuilder.append(c);
    }

    /**
     * Method for get working string
     * @return the working string
     */
    public final String toString() {
        return stringBuilder.toString();
    }
}
