package it.sevenbits.handler;

import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * The abstract class for handle input character and write data in output stream.
 */
public abstract class Handler {
    /**
     * Handle the input character and write result in the output stream
     * @param indent current indent
     * @param out output stream
     * @param readChar input character for handle
     * @throws HandlerException thrown if any errors occur handling
     */
    public abstract void handle(Writable<Character> out, Indent indent, char readChar) throws HandlerException;

    /**
     * Write indent in output stream
     * @param out output stream
     * @param countOfIndent count of indents
     * @throws HandlerException thrown if any errors occur handling
     */
    protected void writeIndent(final Writable<Character> out, final int countOfIndent) throws HandlerException {
        try {
            for (int i = 0; i < countOfIndent; i++) {
                out.write(' ');
            }
        } catch (WriterException e) {
            throw new HandlerException("error of write in Handler", e);
        }
    }
}
