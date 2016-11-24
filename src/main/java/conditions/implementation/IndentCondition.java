package conditions.implementation;

import conditions.ConditionException;
import conditions.ICondition;
import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import table.MapForIndentCondition;
import writer.Writable;

/**
 * Class code in a condition of after '{' and ';'.
 */
public class IndentCondition implements ICondition {
    private Indent indent;
    private MapForIndentCondition mapForIndentCondition;
    private Handler handler;

    /**
     * Constructor
     * @param indent current indent
     */
    public IndentCondition(final Indent indent) {
        mapForIndentCondition = new MapForIndentCondition();
        this.indent = indent;
        handler = null;
    }
    @Override
    public void execute(final Writable out, final char c) throws ConditionException {
        try {
            handler = mapForIndentCondition.getHandler(c);
            handler.handle(out, indent, c);
        } catch (HandlerException e) {
            throw new ConditionException("errors of write in IndentCondition", e);
        }
    }
}
