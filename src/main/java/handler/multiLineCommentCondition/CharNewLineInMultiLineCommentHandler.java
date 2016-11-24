package handler.multiLineCommentCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import writer.Writable;
import writer.WriterException;

/**
 * Handler '\n' in multi line comment.
 */
public class CharNewLineInMultiLineCommentHandler extends Handler {
    @Override
    public void handle(final Writable out, final Indent indent, final char readChar) throws HandlerException {
        try {
            out.writeChar(readChar);
            writeIndent(out, indent.getCurrentIndent() * indent.getSPACES());
            out.writeChar('*');
            out.writeChar(' ');
        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
}
