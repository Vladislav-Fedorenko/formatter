package handler.inheritors;

import closer.CloserException;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for slash - '/'
 */
public class SlashHandler extends Handler {

    @Override
    public void handle(final Writable out, final StylisationOfCode style, final char readChar) throws HandlerException,
            CloserException, WriterException {
        try {
            if (style.isBeginComment()) {
                style.setSingleLineComment(true);
                style.setBeginComment(false);
                out.writeChar(readChar);
                return;
            }
            if (style.isEndComment()) {
                style.setMultiLineComment(false);
                style.setEndComment(false);
                out.writeChar(readChar);
                out.writeChar('\n');
                writeTabs(out, style.getCountOfTabs() - 1);
                style.setBuffer('\t');
                return;
            }
            if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            if (!style.isBeginComment()) {
                style.setBeginComment(true);
                out.writeChar(readChar);
            }
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }
    }
}
