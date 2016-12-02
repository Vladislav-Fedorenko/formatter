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
 * Tests for default State.
 */
public class DefaultStatesTest {
    private Indent indent;
    private Writable<Character> writer;
    private IState state;

    @Before
    public void setUp() {
        indent = new Indent();
        StateManager stateManager = new StateManager(indent);
        writer = new StringWriter("");
        state = stateManager.getInitialState();
        state = stateManager.getNextState(state, '{');
        state = stateManager.getNextState(state, '0');
    }

    @Test
    public void defaultStateOpenBracketTest() throws StateException {
        indent.incCurrentIndent();
        state.execute(writer,'{');
        assertEquals("wrong", "{\n    ", writer.toString());
    }

    @Test
    public void defaultStateCloseBracketTest() throws StateException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        state.execute(writer, '}');
        assertEquals("wrong", "}\n    ", writer.toString());
    }

    @Test
    public void defaultStateCharNewLineTest() throws StateException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        state.execute(writer, '\n');
        assertEquals("wrong", "\n    ", writer.toString());
    }

    @Test
    public void defaultStateSemicolonTest() throws StateException {
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        indent.incCurrentIndent();
        state.execute(writer, ';');
        assertEquals("wrong", ";\n        ", writer.toString());
    }
    @Test
    public void defaultStateDefaultTest() throws StateException {
        state.execute(writer, 't');
        assertEquals("wrong", "t", writer.toString());
    }
    @Test(expected = StateException.class)
    public void defaultStateWithExceptionTest() throws StateException, WriterException {
        writer = mock(Writable.class);
        doThrow(WriterException.class).when(writer).write('\r');
        state.execute(writer, '\r');
        fail();
    }

}
