package conditions;

import writer.Writable;

/**
 * Interface for executing anything.
 */
public interface ICondition {
    /**
     * Write the char in the output stream given a condition of code
     * @param c input character
     * @param out output stream
     */
    void execute(Writable out, char c) throws ConditionException;
}
