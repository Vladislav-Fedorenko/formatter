package handler.inheritors;

import closer.CloserException;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for character of new line - '\n'.
 */
public class CharOfNewLineHandler extends Handler {
    @Override
    public void handle(final Writable out, final StylisationOfCode style, final char readChar) throws HandlerException,
            CloserException, WriterException {
        try {
            if (style.isSingleLineComment()) {
                style.setSingleLineComment(false);
                out.writeChar(readChar);
                writeTabs(out, style.getCountOfTabs() - 1);
                style.setBuffer('\t');
                return;
            }
            out.writeChar(readChar);
            style.setBuffer(readChar);
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }
    }
}
