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
 * Tests for State of single line comment .
 */
public class SingleLineCommentStateTest {
    private Writable<Character> writer;
    private IState state;

    @Before
    public void setUp() {
        Indent indent = new Indent();
        writer = new StringWriter("");
        StateManager stateManager = new StateManager(indent);
        state = stateManager.getInitialState();
        state = stateManager.getNextState(state, '/');
        state = stateManager.getNextState(state, '/');
    }

    @Test
    public void singleLineCommentStateOpenBracketTest() throws StateException {
        state.execute(writer, '{');
        assertEquals("wrong", "{", writer.toString());
    }
    @Test
    public void singleLineCommentStateCloseBracketTest() throws StateException {
        state.execute(writer, '}');
        assertEquals("wrong", "}", writer.toString());
    }
    @Test
    public void singleLineCommentStateSemicolonTest() throws StateException {
        state.execute(writer, ';');
        assertEquals("wrong", ";", writer.toString());
    }
    @Test
    public void singleLineCommentStateDefaultTest() throws StateException {
        state.execute(writer, 'u');
        assertEquals("wrong", "u", writer.toString());
    }
    @Test
    public void singleLineCommentStateCharNewLineTest() throws StateException {
        state.execute(writer, '\n');
        assertEquals("wrong", "\n", writer.toString());
    }
    @Test
    public void singleLineCommentStateQuotesTest() throws StateException {
        state.execute(writer, '\'');
        state.execute(writer, ' ');
        state.execute(writer, '\"');
        assertEquals("wrong", "\' \"", writer.toString());
    }
    @Test(expected = StateException.class)
    public void singleLineCommentStateWithExceptionTest() throws StateException, WriterException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write('\r');
        state.execute(writer, '\r');
        fail();
    }
}
