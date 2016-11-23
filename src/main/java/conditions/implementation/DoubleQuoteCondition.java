package conditions.implementation;

import conditions.ConditionException;
import conditions.ICondition;
import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import table.MapForDoubleQuoteCondition;
import writer.Writable;

/**
 * Class code in a condition of double quotes.
 */
public class DoubleQuoteCondition implements ICondition {
    private Handler handler;
    private MapForDoubleQuoteCondition mapForDoubleQuoteCondition;
    private Indent indent;

    /**
     * Constructor
     * create map for code in double quotes condition and set handler for character
     */
    public DoubleQuoteCondition(final Indent indent) {
        mapForDoubleQuoteCondition = new MapForDoubleQuoteCondition();
        handler = null;
        this.indent = indent;
    }

    @Override
    public void execute(final Writable out, final char c) throws ConditionException {
        try {
            handler = mapForDoubleQuoteCondition.getHandler(c);
            handler.handle(out, indent, c);
        } catch (HandlerException e) {
            throw new ConditionException("error of handler in DoubleQuoteCondition", e);
        }
    }
}
