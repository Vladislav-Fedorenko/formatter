package it.sevenbits.formatter;

import it.sevenbits.reader.Readable;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.writer.Writable;

/**
 * The interface for read, format from input streams data and write to output stream
 */
public interface Formatable {
    /**
     * Read and format the input data and write to output.
     * @param in input stream
     * @param out output stream
     * @throws FormatException thrown if any errors occur formatting
     */
    void format(Readable<Token> in, Writable<String> out) throws FormatException;


}
