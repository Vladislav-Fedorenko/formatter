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
    public void handle(final Writable<Character> out, final Indent indent, final char readChar) throws HandlerException {
        try {
            out.write(readChar);
            writeIndent(out, indent.getCurrentIndent() * indent.getSpaces());
            out.write('*');
            out.write(' ');
        } catch (WriterException e) {
            throw new HandlerException("error of write in CharOfNewLineInMultiLineCommentHandler", e);
        }
    }
}
