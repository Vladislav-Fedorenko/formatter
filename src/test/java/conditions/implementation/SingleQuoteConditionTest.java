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
 * Tests for condition of single quote.
 */
public class SingleQuoteConditionTest {
    private Indent indent;
    private Writable writer;

    @Before
    public void setUp() {
        indent = new Indent();
        writer = new StringWriter("");
    }

    @Test
    public void singleQuoteConditionOpenBracketTest() throws ConditionException {
        indent.incCurrentIndent();
        ICondition condition = new SingleQuoteCondition(indent);
        condition.execute(writer, '\'');
        condition.execute(writer, '{');
        condition.execute(writer, '\'');
        assertEquals("wrong", "\'{\'", writer.toString());
    }
    @Test
    public void singleQuoteConditionCloseBracketTest() throws ConditionException {
        indent.incCurrentIndent();
        ICondition condition = new SingleQuoteCondition(indent);
        condition.execute(writer, '\'');
        condition.execute(writer, '}');
        condition.execute(writer, '\'');
        assertEquals("wrong", "\'}\'", writer.toString());
    }
    @Test
    public void singleQuoteConditionSemicolonBracketTest() throws ConditionException {
        indent.incCurrentIndent();
        ICondition condition = new SingleQuoteCondition(indent);
        condition.execute(writer, '\'');
        condition.execute(writer, ';');
        condition.execute(writer, '\'');
        assertEquals("wrong", "\';\'", writer.toString());
    }
    @Test
    public void singleQuoteConditionDefaultBracketTest() throws ConditionException {
        indent.incCurrentIndent();
        ICondition condition = new SingleQuoteCondition(indent);
        condition.execute(writer, '\'');
        condition.execute(writer, 'u');
        condition.execute(writer, '\'');
        assertEquals("wrong", "\'u\'", writer.toString());
    }
}
