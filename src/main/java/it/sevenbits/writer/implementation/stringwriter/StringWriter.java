
package it.sevenbits.writer.implementation.stringwriter;

import it.sevenbits.writer.Writable;

/**
 * The class for write thread in string
 */
public class StringWriter implements Writable<String> {
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
    public final void write(final String s) {
        stringBuilder.append(s);
    }

    /**
     * Method for get working string
     * @return the working string
     */
    public final String toString() {
        return stringBuilder.toString();
    }
}
