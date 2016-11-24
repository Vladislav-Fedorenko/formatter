package conditions.implementation;

import conditions.ConditionException;
import conditions.ICondition;
import handler.Indent;
import org.junit.Before;
import org.junit.Test;
import writer.Writable;
import writer.implementation.stringWriter.StringWriter;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests for default condition.
 */
public class DefaultConditionsTest {
    private Indent indent;
    private Writable writer;

    @Before
    public void setUp() {
        indent = new Indent();
        writer = new StringWriter("");
    }

    @Test
    public void defaultConditionOpenBracketTest() throws ConditionException {
        indent.incCurrentIndent();
        ICondition condition = new DefaultCondition(indent);
        condition.execute(writer, '{');
        assertEquals("wrong", "{\n    ", writer.toString());
    }

    @Test
    public void defaultConditionCloseBracketTest() throws ConditionException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        ICondition condition = new DefaultCondition(indent);
        condition.execute(writer, '}');
        assertEquals("wrong", "}\n    ", writer.toString());
    }

    @Test
    public void defaultConditionCharNewLineTest() throws ConditionException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        ICondition condition = new DefaultCondition(indent);
        condition.execute(writer, '\n');
        assertEquals("wrong", "\n    ", writer.toString());
    }

    @Test
    public void defaultConditionSemicolonTest() throws ConditionException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        ICondition condition = new DefaultCondition(indent);
        condition.execute(writer, ';');
        assertEquals("wrong", ";\n        ", writer.toString());
    }
    @Test
    public void defaultConditionDefaultTest() throws ConditionException {
        ICondition condition = new DefaultCondition(indent);
        condition.execute(writer, 't');
        assertEquals("wrong", "t", writer.toString());
    }

}
