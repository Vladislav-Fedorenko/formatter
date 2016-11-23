package handler.defaultCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for character of new line - '\n'.
 */
public class CharOfNewLineHandler extends Handler {
    @Override
    public void handle(final Writable out, final Indent indent, final char readChar) throws HandlerException {
        try {
            /*
            if (style.isSingleLineComment()) {
                style.setSingleLineComment(false);
                out.writeChar(readChar);
                writeTabs(out, style.getCountOfTabs() - 1);
                style.setBuffer('\t');
                return;
            }
            out.writeChar(readChar);
            style.setBuffer(readChar);*/
            out.writeChar(readChar);
            writeIndent(out, (indent.getCurrentIndent() * indent.getCOUNTSPACES()) - 1);

        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
}
