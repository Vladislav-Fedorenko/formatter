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
 * Tests for BeginOfCommentState.
 */
public class BeginOfCommentStateTest {
    private Indent indent;
    private Writable<Character> writer;
    private IState state;

    @Before
    public void setUp() {
        indent = new Indent();
        StateManager stateManager = new StateManager(indent);
        writer = new StringWriter("");
        state = stateManager.getInitialState();
        state = stateManager.getNextState(state, '/');
    }

    @Test
    public void beginOfCommentStateDefaultTest() throws StateException {
        state.execute(writer,'/');
        state.execute(writer,'0');
        state.execute(writer,'f');
        assertEquals("wrong", "/0f", writer.toString());
    }
    @Test(expected = StateException.class)
    public void beginOfCommentStateWithExceptionTest() throws StateException, WriterException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write('\r');
        state.execute(writer, '\r');
        fail();
    }
}
