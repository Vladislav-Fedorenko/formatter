package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Handler for close bracket - '}'.
 */
public class CloseBracketHandler extends Handler {

    @Override
    public void handle(final Writable<Character> out, final Indent indent, final char readChar) throws HandlerException {
        try {
            indent.decCurrentIndent();
            out.write(readChar);
            out.write('\n');
            writeIndent(out, (indent.getCurrentIndent() * indent.getSpaces()));
        } catch (WriterException e) {
            throw new HandlerException("error of write in CloseBracketHandler", e);
        }
    }
}
