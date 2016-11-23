package handler.defaultCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for other characters.
 */
public class DefaultHandler extends Handler {
    @Override
    public void handle(final Writable out, final Indent indent, final char readChar) throws HandlerException {
        try {
            /*if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            if (style.isNeedCloseBracket()) {
                out.writeChar('\t');
                style.setBuffer('\t');
            }
            out.writeChar(readChar);
            style.setBuffer(readChar);
            style.setNeedCloseBracket(false);*/
            out.writeChar(readChar);
        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
}
