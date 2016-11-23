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
            /*if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            style.decCountOfTabs();
            out.writeChar(readChar);
            out.writeChar('\n');
            writeTabs(out, style.getCountOfTabs() - 1);
            style.setBuffer('\n');*/
            indent.decCurrentIndent();
            writeIndent(out, (indent.getCurrentIndent() * indent.getCOUNTSPACES()) - 1);
            out.writeChar(readChar);
            out.writeChar('\n');
        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
}
