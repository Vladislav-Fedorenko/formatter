package formatter;

/**
 * Class for auxiliary variables stylization of code.
 */
public class StylisationOfCode {
    /**
     * Count tabulations
     */
    private int countOfTabs;
    /**
     * true if code is single-quotes
     * false else
     */
    private boolean isCodeInSingleQuotes;
    /**
     * true if code is in double-quotes
     * false else
     */
    private boolean isCodeInDoubleQuotes;
    /**
     *  true if a next character can open comment
     *  false else
     */
    private boolean isBeginComment;
    /**
     * true if a next character can close comment
     * false else
     */
    private boolean isEndComment;
    /**
     * true if code is commented like '//'
     * false else
     */
    private boolean isSingleLineComment;
    /**
     * true if code is commented like '/* '
     * false else
     */
    private boolean isMultiLineComment;
    /**
     *  true if need close bracket
     *  false else
     */
    private boolean needCloseBracket;
    /**
     * for storage buffer character
     */
    private char buffer;
    /**
     * Default constructor
     */
    public StylisationOfCode() {
        resetAll();
    }
    /**
     * Reset all value
     */
    public void resetAll() {
        countOfTabs = 0;
        isCodeInSingleQuotes = false;
        isCodeInDoubleQuotes = false;
        isBeginComment = false;
        isEndComment = false;
        isSingleLineComment = false;
        isMultiLineComment = false;
        needCloseBracket = false;
        buffer = ' ';
    }
    public int getCountOfTabs() {
        return countOfTabs;
    }

    /**
     * increment countOfTabs
     */
    public void incCountOfTabs() {
        this.countOfTabs++;
    }

    /**
     * decrement countOfTabs
     */
    public void decCountOfTabs() {
        this.countOfTabs--;
    }

    public boolean isCodeInSingleQuotes() {
        return isCodeInSingleQuotes;
    }

    public void setCodeInSingleQuotes(final boolean codeInSingleQuotes) {
        isCodeInSingleQuotes = codeInSingleQuotes;
    }

    public boolean isCodeInDoubleQuotes() {
        return isCodeInDoubleQuotes;
    }

    public void setCodeInDoubleQuotes(final boolean codeInDoubleQuotes) {
        isCodeInDoubleQuotes = codeInDoubleQuotes;
    }

    public boolean isMultiLineComment() {
        return isMultiLineComment;
    }

    public void setMultiLineComment(final boolean multiLineComment) {
        isMultiLineComment = multiLineComment;
    }

    public boolean isBeginComment() {
        return isBeginComment;
    }

    public void setBeginComment(final boolean beginComment) {
        isBeginComment = beginComment;
    }

    public boolean isSingleLineComment() {
        return isSingleLineComment;
    }

    public void setSingleLineComment(final boolean singleLineComment) {
        isSingleLineComment = singleLineComment;
    }

    public boolean isNeedCloseBracket() {
        return needCloseBracket;
    }

    public void setNeedCloseBracket(final boolean needCloseBracket) {
        this.needCloseBracket = needCloseBracket;
    }

    public char getBuffer() {
        return buffer;
    }

    public void setBuffer(final char buffer) {
        this.buffer = buffer;
    }

    public boolean isEndComment() {
        return isEndComment;
    }

    public void setEndComment(final boolean endComment) {
        isEndComment = endComment;
    }
}
