package handler.indentCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for indent.
 */
public class IndentHandler extends Handler {
    @Override
    public void handle(final Writable out, final Indent indent, final char readChar) throws HandlerException {
        try {
            writeIndent(out, indent.getSPACES());
            out.writeChar(readChar);
        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
}
