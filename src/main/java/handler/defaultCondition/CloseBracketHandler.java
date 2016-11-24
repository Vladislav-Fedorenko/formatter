package handler.defaultCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for close bracket - '}'.
 */
public class CloseBracketHandler extends Handler {

    @Override
    public void handle(final Writable out, final Indent indent, final char readChar) throws HandlerException {
        try {
            indent.decCurrentIndent();
            out.writeChar(readChar);
            out.writeChar('\n');
            writeIndent(out, (indent.getCurrentIndent() * indent.getSPACES()));
        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
}
