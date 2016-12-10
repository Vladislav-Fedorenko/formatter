package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Handler for open bracket - '{'.
 */
public class OpenBracketHandler extends Handler {

    @Override
    public void handle(final Writable<String> out, final Indent indent, final String s) throws HandlerException {
        try {
            indent.incCurrentIndent();
            out.write(s);
            out.write("\n");
            writeIndent(out, (indent.getCurrentIndent() - 1) * indent.getSpaces());
        } catch (WriterException e) {
            throw new HandlerException("error of write in OpenBracketHandler", e);
        }
    }
}
