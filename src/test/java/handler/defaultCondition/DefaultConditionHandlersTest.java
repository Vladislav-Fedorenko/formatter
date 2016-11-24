package handler.defaultCondition;

import handler.Handler;
import handler.HandlerException;
import handler.Indent;
import org.junit.Before;
import org.junit.Test;
import writer.Writable;
import writer.implementation.stringWriter.StringWriter;

import static junit.framework.TestCase.assertEquals;

/**
 * Test for handlers in default condition.
 */
public class DefaultConditionHandlersTest {
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
    public void charOfNewLineHandlerTest() throws HandlerException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        handler = new CharOfNewLineHandler();
        handler.handle(writer, indent, '\n');
        assertEquals("wrong", "\n    ", writer.toString());
    }
    @Test
    public void closeBracketHandlerTest() throws HandlerException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        handler = new CloseBracketHandler();
        handler.handle(writer, indent, '}');
        assertEquals("wrong", "}\n        ", writer.toString());
    }
    @Test
    public void openBracketHandlerTest() throws HandlerException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        handler = new OpenBracketHandler();
        handler.handle(writer, indent, '{');
        assertEquals("wrong", "{\n        ", writer.toString());
    }
    @Test
    public void semicolonHandlerTest() throws HandlerException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        handler = new SemicolonHandler();
        handler.handle(writer, indent, ';');
        assertEquals("wrong", ";\n        ", writer.toString());
    }
    @Test
    public void defaultHandlerTest() throws HandlerException {
        handler = new DefaultHandler();
        handler.handle(writer, indent, '7');
        handler.handle(writer, indent, 'b');
        handler.handle(writer, indent, 'i');
        handler.handle(writer, indent, 't');
        handler.handle(writer, indent, 's');
        assertEquals("wrong", "7bits", writer.toString());
    }
}
