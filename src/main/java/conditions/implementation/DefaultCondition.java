package conditions.implementation;

import conditions.ConditionException;
import conditions.ICondition;
import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import table.MapForDefaultCondition;
import writer.Writable;

/**
 * Class for default condition of code.
 */
public class DefaultCondition implements ICondition {

    private Handler handler;
    private MapForDefaultCondition mapForDefaultCondition;
    private Indent indent;
    /**
     * Constructor
     * create map for default condition and set handler for character
     * @param indent current indent
     */
    public DefaultCondition(final Indent indent) {
        mapForDefaultCondition = new MapForDefaultCondition();
        handler = null;
        this.indent = indent;
    }
    @Override
    public void execute(final Writable out, final char c) throws ConditionException {
        try {
            handler = mapForDefaultCondition.getHandler(c);
            handler.handle(out, indent, c);
        } catch (HandlerException e) {
            throw new ConditionException("error of handler in DefaultCondition", e);
        }
    }
}
