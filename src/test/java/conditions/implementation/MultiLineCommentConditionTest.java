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
 * Tests for condition of multi line comment.
 */
public class MultiLineCommentConditionTest {
    private Indent indent;
    private Writable writer;

    @Before
    public void setUp() {
        indent = new Indent();
        writer = new StringWriter("");
    }

    @Test
    public void multiLineCommentConditionDefaultTest() throws ConditionException {
        indent.incCurrentIndent();
        ICondition condition = new MultiLineCommentCondition(indent);
        condition.execute(writer, '/');
        condition.execute(writer, '*');
        condition.execute(writer, '{');
        condition.execute(writer, '\n');
        condition.execute(writer, '}');
        condition.execute(writer, '*');
        condition.execute(writer, '/');
        assertEquals("wrong", "/*{\n    * }*/", writer.toString());
    }
}
