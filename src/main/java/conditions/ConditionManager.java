package conditions;

import conditions.implementation.*;
import handler.Indent;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager of conditions.
 */
public class ConditionManager {
    private Map<DataForNextCondition<Character>, ICondition> map;
    private ICondition defaultCondition;

    /**
     * Default constructor
     * @param indent current indent
     */
    public ConditionManager(final Indent indent) {
        map = new HashMap<DataForNextCondition<Character>, ICondition>();

        defaultCondition = new DefaultCondition(indent);
        ICondition doubleQuoteCondition = new DoubleQuoteCondition(indent);
        ICondition singleQuoteCondition = new SingleQuoteCondition(indent);
        ICondition multiLineCommentCondition = new MultiLineCommentCondition(indent);
        ICondition singleLineCommentCondition = new SingleLineCommentCondition(indent);
        ICondition beginOfCommentCondition = new BeginOfCommentCondition();
        ICondition endOfCommentCondition = new EndOfCommentCondition();

        map.put(new DataForNextCondition<Character>(defaultCondition, '/'), beginOfCommentCondition);
        map.put(new DataForNextCondition<Character>(defaultCondition, '\"'), doubleQuoteCondition);
        map.put(new DataForNextCondition<Character>(defaultCondition, '\''), singleQuoteCondition);
        map.put(new DataForNextCondition<Character>(doubleQuoteCondition, '\"'), defaultCondition);
        map.put(new DataForNextCondition<Character>(singleQuoteCondition, '\''), defaultCondition);
        map.put(new DataForNextCondition<Character>(multiLineCommentCondition, '*'), endOfCommentCondition);
        map.put(new DataForNextCondition<Character>(singleLineCommentCondition, '\n'), defaultCondition);
        map.put(new DataForNextCondition<Character>(beginOfCommentCondition, '/'), singleLineCommentCondition);
        map.put(new DataForNextCondition<Character>(beginOfCommentCondition, '*'), multiLineCommentCondition);
        map.put(new DataForNextCondition<Character>(endOfCommentCondition, '/'), defaultCondition);
    }

    /**
     * call method map.get(Key k)
     * @param currentCondition current condition of code
     * @param c read character
     * @return next condition if map.get() != null
     *          current condition else
     */
    public ICondition getNextCondition(final ICondition currentCondition, final char c) {
        if (map.containsKey(new DataForNextCondition<Character>(currentCondition, c))) {
            return map.get(new DataForNextCondition<Character>(currentCondition, c));
        }
        return currentCondition;
    }

    /**
     * Method for initialization condition
     * @return initial condition
     */
    public ICondition getInitialCondition() {
        return defaultCondition;
    }
}
