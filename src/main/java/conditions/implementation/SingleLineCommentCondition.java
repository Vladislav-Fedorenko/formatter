package conditions.implementation;

import conditions.ConditionException;
import conditions.ICondition;
import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import table.MapForSingleLineCommentCondition;
import writer.Writable;

/**
 * Class code in a condition of single line comment.
 */
public class SingleLineCommentCondition implements ICondition {
    private Handler handler;
    private MapForSingleLineCommentCondition mapForSingleLineCommentCondition;
    private Indent indent;
    /**
     * Constructor
     * create map for code in single line comments condition and set handler for character
     * @param indent current indent
     */
    public SingleLineCommentCondition(final Indent indent) {
        mapForSingleLineCommentCondition = new MapForSingleLineCommentCondition();
        handler = null;
        this.indent = indent;
    }

    @Override
    public void execute(final Writable out, final char c) throws ConditionException {
        try {
            handler = mapForSingleLineCommentCondition.getHandler(c);
            handler.handle(out, indent, c);
        } catch (HandlerException e) {
            throw new ConditionException("error of handler in SingleLineCondition", e);
        }
    }

}
