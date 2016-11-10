package handler.inheritors;

import closer.CloserException;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for single quote - '\''.
 */
public class SingleQuoteHandler extends Handler {
    @Override
    public void handle(final Writable out, final StylisationOfCode style, final char readChar) throws HandlerException,
            CloserException, WriterException {
        try {
            if (style.isCodeInSingleQuotes()) {
                style.setCodeInSingleQuotes(false);
                out.writeChar(readChar);
                style.setBuffer(readChar);
                return;
            }
            if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            if (!style.isCodeInSingleQuotes()) {
                style.setCodeInSingleQuotes(true);
                out.writeChar(readChar);
            }
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }
    }
}
