package handler.defaultCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for semicolon - ';'.
 */
public class SemicolonHandler extends Handler {
    @Override
    public void handle(final Writable out, final Indent indent, final char readChar) throws HandlerException {
        try {
            /*if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            out.writeChar(readChar);
            out.writeChar('\n');
            writeTabs(out, style.getCountOfTabs() - 1);
            style.setBuffer('\t');
            style.setNeedCloseBracket(true);*/
            out.writeChar(readChar);
            out.writeChar('\n');
            writeIndent(out, (indent.getCurrentIndent() * indent.getCOUNTSPACES()) - 1);
        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
}
