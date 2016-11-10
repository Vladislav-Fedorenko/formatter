package handler.inheritors;

import closer.CloserException;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import writer.Writable;
import writer.WriterException;

/**
 * Handler for slash - ' '.
 */
public class SpaceHandler extends Handler {
    @Override
    public void handle(final Writable out, final StylisationOfCode style, final char readChar) throws HandlerException,
            CloserException, WriterException {
        try {
            if (!needHandleCode(style)) {
                out.writeChar(readChar);
                return;
            }
            if (style.getBuffer() != ' ' && style.getBuffer() != '\n' && style.getBuffer() != '\t') {
                out.writeChar(' ');
                style.setBuffer(' ');
            }
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }
    }
}
