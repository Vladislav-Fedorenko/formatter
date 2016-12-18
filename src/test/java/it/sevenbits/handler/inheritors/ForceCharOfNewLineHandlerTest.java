package it.sevenbits.handler.inheritors;

import it.sevenbits.handler.formatter.Handler;
import it.sevenbits.handler.formatter.HandlerException;
import it.sevenbits.handler.formatter.Indent;
import it.sevenbits.handler.formatter.inheritors.ForceCharOfNewLineHandler;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;
import it.sevenbits.writer.implementation.stringwriter.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Handler for force char of new line.
 */
public class ForceCharOfNewLineHandlerTest {
    private Handler handler;
    private Writable<String> writer;
    private Indent indent;
    @Before
    public void setUp() {
        writer = new StringWriter("a;");
        handler = new ForceCharOfNewLineHandler();
        indent = new Indent();
    }

    @Test
    public void forceCharOfNewLineHandlerTest() throws HandlerException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        String current = writer.toString();
        handler.handle(writer, indent, "\n");
        assertEquals("wrong", current + "\n\n    ", writer.toString());
    }
    @Test(expected = HandlerException.class)
    public void forceCharOfNewLineHandlerWithExceptionTest() throws WriterException, HandlerException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write("\n");
        handler.handle(writer, indent, "\n");
        fail();
    }
}
