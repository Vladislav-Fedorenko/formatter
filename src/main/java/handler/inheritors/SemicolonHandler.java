package handler.inheritors;

import closer.CloserException;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for semicolon - ';'.
 */
public class SemicolonHandler extends Handler {
    @Override
    public void handle(final Writable out, final StylisationOfCode style, final char readChar) throws HandlerException,
            CloserException, WriterException {
        try {
            if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            out.writeChar(readChar);
            out.writeChar('\n');
            writeTabs(out, style.getCountOfTabs() - 1);
            style.setBuffer('\t');
            style.setNeedCloseBracket(true);
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }
    }
}
