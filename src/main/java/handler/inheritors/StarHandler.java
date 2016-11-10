package handler.inheritors;

import closer.CloserException;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for star - '*'
 */
public class StarHandler extends Handler {
    @Override
    public void handle(final Writable out, final StylisationOfCode style, final char readChar) throws HandlerException,
            CloserException, WriterException {
        try {
            if (style.isBeginComment()) {
                style.setMultiLineComment(true);
                style.setBeginComment(false);
                out.writeChar(readChar);
                return;
            }
            if (style.isMultiLineComment()) {
                style.setEndComment(true);
                out.writeChar(readChar);
                return;
            }
            out.writeChar(readChar);
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }

    }
}
