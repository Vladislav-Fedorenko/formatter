package it.sevenbits.states.implementation;

import it.sevenbits.handler.Indent;
import it.sevenbits.states.IState;
import it.sevenbits.states.StateException;
import it.sevenbits.states.StateManager;
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
 * Tests for state of single quote.
 */
public class SingleQuoteStateTest {
    private Indent indent;
    private Writable<Character> writer;
    private IState state;

    @Before
    public void setUp() {
        indent = new Indent();
        writer = new StringWriter("");
        StateManager stateManager = new StateManager(indent);
        state = stateManager.getInitialState();
        state = stateManager.getNextState(state, '\'');
    }

    @Test
    public void singleQuoteStateOpenBracketTest() throws StateException {
        state.execute(writer, '{');
        assertEquals("wrong", "{", writer.toString());
    }
    @Test
    public void singleQuoteStateCloseBracketTest() throws StateException {
        state.execute(writer, '}');
        assertEquals("wrong", "}", writer.toString());
    }
    @Test
    public void singleQuoteStateSemicolonTest() throws StateException {
        state.execute(writer, ';');
        assertEquals("wrong", ";", writer.toString());
    }
    @Test
    public void singleQuoteStateDefaultTest() throws StateException {
        state.execute(writer, 'u');
        assertEquals("wrong", "u", writer.toString());
    }
    @Test
    public void singleQuoteStateQuotesTest() throws StateException {
        state.execute(writer, '\'');
        state.execute(writer, ' ');
        state.execute(writer, '\"');
        assertEquals("wrong", "\' \"", writer.toString());
    }
    @Test(expected = StateException.class)
    public void singleQuoteStateWithExceptionTest() throws StateException, WriterException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write('\r');
        state.execute(writer, '\r');
        fail();
    }
}
