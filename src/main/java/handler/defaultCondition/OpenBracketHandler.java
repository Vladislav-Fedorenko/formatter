package handler.defaultCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for open bracket - '{'.
 */
public class OpenBracketHandler extends Handler {

    @Override
    public void handle(final Writable out, final Indent indent, final char readChar) throws HandlerException {
        try {
            indent.incCurrentIndent();
            out.writeChar(readChar);
            out.writeChar('\n');
            writeIndent(out, (indent.getCurrentIndent() - 1) * indent.getSPACES());
        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
}
