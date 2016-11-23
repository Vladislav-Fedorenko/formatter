package conditions.implementation;

import conditions.ConditionException;
import conditions.ICondition;
import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import table.MapForMultiLineCommentCondition;
import writer.Writable;

/**
 * Class code in a condition of multi line comment.
 */
public class MultiLineCommentCondition implements ICondition {
    private Handler handler;
    private MapForMultiLineCommentCondition mapForMultiLineCommentCondition;
    private Indent indent;
    /**
     * Constructor
     * create map for code in multi line comments condition and set handler for character
     */
    public MultiLineCommentCondition(final Indent indent) {
        mapForMultiLineCommentCondition = new MapForMultiLineCommentCondition();
        handler = null;
        this.indent = indent;
    }
    @Override
    public void execute(final Writable out, final char c) throws ConditionException {
        try {
            handler = mapForMultiLineCommentCondition.getHandler(c);
            handler.handle(out, indent, c);
        } catch (HandlerException e) {
            throw new ConditionException("error of handler in MultiLineCondition", e);
        }
    }
}
