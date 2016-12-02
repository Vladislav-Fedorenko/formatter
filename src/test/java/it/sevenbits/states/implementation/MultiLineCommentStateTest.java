package it.sevenbits.states.implementation;

import it.sevenbits.states.StateException;
import it.sevenbits.states.IState;
import it.sevenbits.handler.Indent;
import it.sevenbits.states.StateManager;
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
 * Tests for State of multi line comment.
 */
public class MultiLineCommentStateTest {
    private Writable<Character> writer;
    private IState state;
    private Indent indent;

    @Before
    public void setUp() {
        indent = new Indent();
        writer = new StringWriter("");
        StateManager stateManager = new StateManager(indent);
        state = stateManager.getInitialState();
        state = stateManager.getNextState(state, '/');
        state = stateManager.getNextState(state, '*');
    }

    @Test
    public void multiLineCommentStateOpenBracketTest() throws StateException {
        state.execute(writer, '{');
        assertEquals("wrong", "{", writer.toString());
    }
    @Test
    public void multiLineCommentStateCloseBracketTest() throws StateException {
        state.execute(writer, '}');
        assertEquals("wrong", "}", writer.toString());
    }
    @Test
    public void multiLineCommentStateSemicolonTest() throws StateException {
        state.execute(writer, ';');
        assertEquals("wrong", ";", writer.toString());
    }
    @Test
    public void multiLineCommentStateDefaultTest() throws StateException {
        state.execute(writer, 'u');
        assertEquals("wrong", "u", writer.toString());
    }
    @Test
    public void multiLineCommentStateCharNewLineTest() throws StateException {
        indent.incCurrentIndent();
        state.execute(writer, ' ');
        state.execute(writer, ' ');
        state.execute(writer, ' ');
        state.execute(writer, '/');
        state.execute(writer, '*');
        state.execute(writer, '\n');
        state.execute(writer, 'c');
        assertEquals("wrong", "   /*\n    * c", writer.toString());
    }
    @Test
    public void multiLineCommentStateQuotesTest() throws StateException {
        state.execute(writer, '\'');
        state.execute(writer, ' ');
        state.execute(writer, '\"');
        assertEquals("wrong", "\' \"", writer.toString());
    }
    @Test(expected = StateException.class)
    public void multiLineCommentStateWithExceptionTest() throws StateException, WriterException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write('\r');
        state.execute(writer, '\r');
        fail();
    }
}
