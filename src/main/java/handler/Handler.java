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
     * buffer for storage previous read character
     */
    private static char buffer = 0;  //clear!!!!!!!!!

    /**
     * count tabulations need for indent
     */
    private static int countOfTabs = 0;//clear!!!!!!

    /**
     * Handle the input character and write result in the output stream
     * @param indent current indent
     * @param out output stream
     * @param readChar input character for handle
     * @throws HandlerException thrown if any errors occur handling
     */
    public abstract void handle(Writable out, Indent indent, char readChar) throws HandlerException;

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
     * Write indent in output stream
     * @param out output stream
     * @param countOfIndent count of indents
     * @throws HandlerException thrown if any errors occur handling
     */
    protected void writeIndent(final Writable out, final int countOfIndent) throws HandlerException {
        try {
            for (int i = 0; i < countOfTabs; i++) {
                out.writeChar(' ');
            }
        } catch (WriterException e) {
            throw new HandlerException(e);
        }
    }
    public static char getBuffer() {
        return buffer;
    }

    public static void setBuffer(final char buffer) {
        Handler.buffer = buffer;
    }

    public static int getCountOfTabs() {
        return countOfTabs;
    }

    public static void setCountOfTabs(int countOfTabs) {
        Handler.countOfTabs = countOfTabs;
    }
}
