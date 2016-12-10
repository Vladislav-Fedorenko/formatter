package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Handler for indent.
 */
public class IndentHandler extends Handler {
    @Override
    public void handle(final Writable<String> out, final Indent indent, final String s) throws HandlerException {
        try {
            writeIndent(out, indent.getSpaces());
            out.write(s);
        } catch (WriterException e) {
            throw new HandlerException("error of write in IndentHandler", e);
        }
    }
}
