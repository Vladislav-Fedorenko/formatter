package handler;

import closer.CloserException;
import formatter.StylisationOfCode;
import writer.Writable;
import writer.WriterException;

/**
 * The abstract class for handle input character and write data in output stream.
 */
public abstract class Handler {
    /**
     * Handle the input character and write result in the output stream
     * @param out output stream
     * @param readChar input character for handle
     * @param style class auxiliary variables
     * @throws HandlerException thrown if any errors occur handling
     * @throws CloserException thrown if any errors occur closing
     * @throws WriterException thrown if any errors occur writing
     */
    public abstract void handle(Writable out, StylisationOfCode style, char readChar) throws HandlerException,
            CloserException, WriterException;

    /**
     * Check flags from class StylisationOfCode
     * @param style class auxiliary variables
     * @return true if code need to handle
     *          false else
     * */
    protected boolean needHandleCode(final StylisationOfCode style) {
        return !(style.isCodeInSingleQuotes() || style.isCodeInDoubleQuotes()
                || style.isMultiLineComment() || style.isSingleLineComment());
    }

    /**
     * Write tabulations in output stream
     * @param out output stream
     * @param countOfTabs count of tabulation
     * @throws HandlerException thrown if any errors occur handling
     * @throws CloserException thrown if any errors occur closing
     * @throws WriterException thrown if any errors occur writing
     */
    protected void writeTabs(final Writable out, final int countOfTabs) throws CloserException, WriterException,
            HandlerException {
        try {
            for (int i = 0; i < countOfTabs; i++) {
                out.writeChar('\t');
            }
        } catch (WriterException e) {
            throw new HandlerException(e);
        } catch (CloserException e) {
            throw new HandlerException(e);
        }
    }
}
