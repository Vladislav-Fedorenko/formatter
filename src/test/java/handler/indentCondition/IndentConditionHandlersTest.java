package handler.indentCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import org.junit.Before;
import org.junit.Test;
import writer.Writable;
import writer.implementation.stringWriter.StringWriter;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests for handlers in condition of indent.
 */
public class IndentConditionHandlersTest {
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
    public void indentHandlerTest() throws HandlerException {
        handler = new IndentHandler();
        handler.handle(writer, indent, 't');
        assertEquals("wrong", "    t", writer.toString());
    }
}
