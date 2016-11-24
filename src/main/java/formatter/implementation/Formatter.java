package formatter.implementation;


import conditions.ConditionException;
import conditions.ConditionManager;
import conditions.ICondition;
import formatter.FormatException;
import formatter.Formatable;
import handler.Indent;
import reader.Readable;
import reader.ReaderException;
import writer.Writable;

/**
 * The class for read, format from input streams data and write to output stream
 */
public class Formatter implements Formatable {

    /**
     * Constructor.
     */
    public Formatter() {
    }
    @Override
    public void format(final Readable in, final Writable out) throws FormatException {
        try {
            Indent indent = new Indent();
            ConditionManager conditionManager = new ConditionManager(indent);
            ICondition condition = conditionManager.getInitialCondition();
            Character readChar;
            while (!in.isEnd()) {
                readChar = in.readChar();
                condition.execute(out, readChar);
                condition = conditionManager.getNextCondition(condition, readChar);
            }
            if (indent.getCurrentIndent() != 0) {
                throw new ConditionException("error handle: incorrect number of braces", new Throwable());
            }
        } catch (ReaderException e) {
            throw new FormatException(e);
        } catch (ConditionException e) {
            throw new FormatException(e);
        }
    }
}
