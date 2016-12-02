package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;
import it.sevenbits.writer.implementation.stringWriter.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Tests for CharOfNewLineHandler.
 */
public class CharOfNewLineHandlerTest {
    private Handler handler;
    private Writable<Character> writer;
    private Indent indent;
    @Before
    public void setUp() {
        writer = new StringWriter("a;");
        handler = new CharOfNewLineHandler();
        indent = new Indent();
    }

    @Test
    public void charOfNewLineHandlerTest() throws HandlerException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        String current = writer.toString();
        handler.handle(writer, indent, '\n');
        assertEquals("wrong", current + "\n    ", writer.toString());
    }
    @Test(expected = HandlerException.class)
    public void charOfNewLineHandlerWithExceptionTest() throws WriterException, HandlerException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write('\n');
        handler.handle(writer, indent, '\n');
        fail();
    }
}
