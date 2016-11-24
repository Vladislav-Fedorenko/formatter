package handler.multiLineCommentCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import org.junit.Before;
import org.junit.Test;
import writer.Writable;
import writer.implementation.stringWriter.StringWriter;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests for handlers in condition of multi line comment.
 */
public class MultiLineCommentConditionHandlersTest {
    private Handler handler;
    private Writable writer;
    private Indent indent;
    @Before
    public void setUp() {
        writer = new StringWriter("");
        handler = null;
        indent = new Indent();
    }

    @Test
    public void charOfNewLineInMultiLineCommentHandlerTest() throws HandlerException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        handler = new CharNewLineInMultiLineCommentHandler();
        handler.handle(writer, indent, '\n');
        assertEquals("wrong", "\n        * ", writer.toString());
    }
}
