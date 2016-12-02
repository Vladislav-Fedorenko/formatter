package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Handler for other characters.
 */
public class DefaultHandler extends Handler {
    @Override
    public void handle(final Writable<Character> out, final Indent indent, final char readChar) throws HandlerException {
        try {
            out.write(readChar);
        } catch (WriterException e) {
            throw new HandlerException("error of write in DefaultHandler", e);
        }
    }
}
