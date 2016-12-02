package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.writer.WriterException;
import org.junit.Before;
import org.junit.Test;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.implementation.stringWriter.StringWriter;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Test for handlers in default state.
 */
public class SemicolonHandlerTest {
    private Handler handler;
    private Writable<Character> writer;
    private Indent indent;

    @Before
    public void setUp() {
        writer = new StringWriter("main() {a");
        handler = new SemicolonHandler();
        indent = new Indent();
    }

    @Test
    public void semicolonHandlerTest() throws HandlerException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        String current = writer.toString();
        handler.handle(writer, indent, ';');
        assertEquals("wrong", current + ";\n    ", writer.toString());
    }
    @Test(expected = HandlerException.class)
    public void semicolonHandlerWithExceptionTest() throws WriterException, HandlerException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write(';');
        handler.handle(writer, indent, ';');
        fail();
    }
}
