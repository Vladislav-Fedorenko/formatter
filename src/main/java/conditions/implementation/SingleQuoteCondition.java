package conditions.implementation;

import conditions.ConditionException;
import conditions.ICondition;
import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import table.MapForSingleQuoteCondition;
import writer.Writable;

/**
 * Class code in a condition of single quotes.
 */
public class SingleQuoteCondition implements ICondition {
    private Handler handler;
    private MapForSingleQuoteCondition mapForDoubleQuoteCondition;
    private Indent indent;
    /**
     * Constructor
     * create map for code in single quotes condition and set handler for character
     * @param indent current indent
     */
    public SingleQuoteCondition(final Indent indent) {
        mapForDoubleQuoteCondition = new MapForSingleQuoteCondition();
        handler = null;
        this.indent = indent;
    }
    @Override
    public void execute(final Writable out, final char c) throws ConditionException {
        try {
            handler = mapForDoubleQuoteCondition.getHandler(c);
            handler.handle(out, indent, c);
        } catch (HandlerException e) {
            throw new ConditionException("error of handler in MultiLineCondition", e);
        }
    }
}
