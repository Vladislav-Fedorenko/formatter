package it.sevenbits.handler.formatter.inheritors;

import it.sevenbits.handler.formatter.Handler;
import it.sevenbits.handler.formatter.HandlerException;
import it.sevenbits.handler.formatter.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Handler for character of new line - '\n'.
 */
public class CharOfNewLineHandler extends Handler {
    @Override
    public void handle(final Writable<String> out, final Indent indent, final String s) throws HandlerException {
        try {
            out.write(s);
            writeIndent(out, (indent.getCurrentIndent() - 1) * indent.getSpaces());
        } catch (WriterException e) {
            throw new HandlerException("error of write in CharNewLineHandler", e);
        }
    }
}
