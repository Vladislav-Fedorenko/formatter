package conditions.implementation;

import conditions.ConditionException;
import conditions.ICondition;
import writer.Writable;
import writer.WriterException;

/**
 * Class code in a condition of begin of comment.
 */
public class BeginOfCommentCondition implements ICondition {

    @Override
    public void execute(final Writable out, final char c) throws ConditionException {
        try {
            out.writeChar(c);
        } catch (WriterException e) {
            throw new ConditionException("error of write in BeginOfCondition", e);
        }
    }
}
