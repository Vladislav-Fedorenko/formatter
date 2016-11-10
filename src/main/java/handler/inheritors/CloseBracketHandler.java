package handler.inheritors;

import closer.CloserException;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for close bracket - '}'.
 */
public class CloseBracketHandler extends Handler {

    @Override
    public void handle(final Writable out, final StylisationOfCode style, final char readChar) throws HandlerException,
            CloserException, WriterException {
        try {
            if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            style.decCountOfTabs();
            out.writeChar(readChar);
            out.writeChar('\n');
            writeTabs(out, style.getCountOfTabs() - 1);
            style.setBuffer('\n');
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }
    }
}
