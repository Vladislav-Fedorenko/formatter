package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * Handler '\n' in multi line comment.
 */
public class CharOfNewLineInMultiLineCommentHandler extends Handler {
    @Override
    public void handle(final Writable<String> out, final Indent indent, final String s) throws HandlerException {
        try {
            out.write(s);
            writeIndent(out, indent.getCurrentIndent() * indent.getSpaces());
            out.write(" * ");
        } catch (WriterException e) {
            throw new HandlerException("error of write in CharNewLineInMultiLineCommentHandler", e);
        }
    }
}
