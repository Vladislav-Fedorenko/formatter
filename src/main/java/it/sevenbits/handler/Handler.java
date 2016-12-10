package it.sevenbits.handler;

import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * The abstract class for handle input string and write data in output stream.
 */
public abstract class Handler {
    /**
     * Handle the input string and write result in the output stream
     * @param indent current indent
     * @param out output stream
     * @param s input string for handle
     * @throws HandlerException thrown if any errors occur handling
     */
    public abstract void handle(Writable<String> out, Indent indent, String s) throws HandlerException;

    /**
     * Write indent in output stream
     * @param out output stream
     * @param countOfIndent count of indents
     * @throws HandlerException thrown if any errors occur handling
     */
    protected void writeIndent(final Writable<String> out, final int countOfIndent) throws HandlerException {
        try {
            char[] chars = new char[countOfIndent];
            for (int i = 0; i < countOfIndent; i++) {
                chars[i] = ' ';
            }
            out.write(new String(chars));
        } catch (WriterException e) {
            throw new HandlerException("error of write in Handler", e);
        }
    }
}
