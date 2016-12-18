package it.sevenbits.handler.formatter.inheritors;

import it.sevenbits.handler.formatter.Handler;
import it.sevenbits.handler.formatter.HandlerException;
import it.sevenbits.handler.formatter.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Handler for close bracket - '}'.
 */
public class CloseBracketHandler extends Handler {

    @Override
    public void handle(final Writable<String> out, final Indent indent, final String s) throws HandlerException {
        try {
            indent.decCurrentIndent();
            out.write(s);
            out.write("\n");
            if (indent.getCurrentIndent() < 1) {
                writeIndent(out, ((indent.getCurrentIndent()) * indent.getSpaces()));
                return;
            }
            writeIndent(out, ((indent.getCurrentIndent() - 1) * indent.getSpaces()));
        } catch (WriterException e) {
            throw new HandlerException("error of write in CloseBracketHandler", e);
        }
    }
}
