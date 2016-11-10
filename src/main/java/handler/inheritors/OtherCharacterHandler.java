package handler.inheritors;

import closer.CloserException;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for other characters.
 */
public class OtherCharacterHandler extends Handler {
    @Override
    public void handle(final Writable out, final StylisationOfCode style, final char readChar) throws HandlerException,
            CloserException, WriterException {
        try {
            if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            if (style.isNeedCloseBracket()) {
                out.writeChar('\t');
                style.setBuffer('\t');
            }
            out.writeChar(readChar);
            style.setBuffer(readChar);
            style.setNeedCloseBracket(false);
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }
    }
}
