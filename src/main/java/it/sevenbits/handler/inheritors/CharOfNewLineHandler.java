package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Handler for character of new line - '\n'.
 */
public class CharOfNewLineHandler extends Handler {
    @Override
    public void handle(final Writable<Character> out, final Indent indent, final char readChar) throws HandlerException {
        try {
            out.write(readChar);
            writeIndent(out, (indent.getCurrentIndent() - 1) * indent.getSpaces());
        } catch (WriterException e) {
            throw new HandlerException("error of write in CharNewLineHandler", e);
        }
    }
}
