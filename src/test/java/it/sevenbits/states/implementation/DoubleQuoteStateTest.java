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
 * Tests for State of double quote .
 */
public class DoubleQuoteStateTest {
    private Writable<Character> writer;
    private IState state;
    @Before
    public void setUp() {
        Indent indent = new Indent();
        writer = new StringWriter("");
        StateManager stateManager = new StateManager(indent);
        state = stateManager.getInitialState();
        state = stateManager.getNextState(state, '\"');
        state = stateManager.getNextState(state, 'o');
    }

    @Test
    public void doubleQuoteStateDefaultTest() throws StateException {

        state.execute(writer, 'a');
        state.execute(writer, '\n');
        state.execute(writer, 'c');
        assertEquals("wrong", "a\nc", writer.toString());
    }
    @Test
    public void doubleQuoteStateOpenBracketTest() throws StateException {
        state.execute(writer, '{');
        assertEquals("wrong", "{", writer.toString());
    }
    @Test
    public void doubleQuoteStateCLoseBracketTest() throws StateException {
        state.execute(writer, '}');
        assertEquals("wrong", "}", writer.toString());
    }
    @Test
    public void doubleQuoteStateSemicolonTest() throws StateException {
        state.execute(writer, ';');
        assertEquals("wrong", ";", writer.toString());
    }
    @Test
    public void doubleQuoteStateDoubleQuoteTest() throws StateException {
        state.execute(writer, '\"');
        assertEquals("wrong", "\"", writer.toString());
    }
    @Test
    public void doubleQuoteStateDSingleQuoteTest() throws StateException {
        state.execute(writer, '\'');
        assertEquals("wrong", "\'", writer.toString());
    }
    @Test(expected = StateException.class)
    public void doubleQuoteStateWithExceptionTest() throws StateException, WriterException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write('\r');
        state.execute(writer, '\r');
        fail();
    }


}
