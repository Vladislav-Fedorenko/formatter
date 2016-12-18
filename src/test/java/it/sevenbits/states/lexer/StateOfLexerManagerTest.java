package it.sevenbits.states.lexer;

import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.lexer.Lexer;
import it.sevenbits.reader.implementation.stringreader.StringReader;
import it.sevenbits.states.State;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests for States of Lexer.
 */
public class StateOfLexerManagerTest {
    private Readable<Character> in;
    private Lexer lexer;
    private StateOfLexerManager stateManager;
    private State currentState;

    @Before
    public void setUp() {
        stateManager = new StateOfLexerManager();
    }

    @Test
    public void getInitialStateTest() {
        currentState = new State("append");
        assertEquals("wrong", currentState, stateManager.getInitialState());
    }
    @Test
    public void appendStateTest() throws ReaderException {
        in = new StringReader("main/aa");
        lexer = new Lexer(in);
        currentState = new State("append");
        lexer.read();
        lexer.read();
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void returnWithRepeatStateTest() throws ReaderException {
        in = new StringReader("main{");
        lexer = new Lexer(in);
        currentState = new State("return with repeat");
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void returnWithRepeat2StateTest() throws ReaderException {
        in = new StringReader("\n}");
        lexer = new Lexer(in);
        currentState = new State("return with repeat");
        lexer.read();
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void returnWithRepeat3StateTest() throws ReaderException {
        in = new StringReader("a;");
        lexer = new Lexer(in);
        currentState = new State("return with repeat");
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void returnWithRepeat4StateTest() throws ReaderException {
        in = new StringReader("test//");
        lexer = new Lexer(in);
        currentState = new State("return with repeat");
        lexer.read();
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void returnWithRepeat5StateTest() throws ReaderException {
        in = new StringReader("main \n {");
        lexer = new Lexer(in);
        currentState = new State("return with repeat");
        lexer.read();
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void returnWithRepeat6StateTest() throws ReaderException {
        in = new StringReader("main \n a");
        lexer = new Lexer(in);
        currentState = new State("append");
        lexer.read();
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void continueStateTest() throws ReaderException {
        in = new StringReader("test/");
        lexer = new Lexer(in);
        currentState = new State("continue");
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void continue2StateTest() throws ReaderException {
        in = new StringReader("test*");
        lexer = new Lexer(in);
        currentState = new State("continue");
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void skipStateTest() throws ReaderException {
        in = new StringReader("test ");
        lexer = new Lexer(in);
        currentState = new State("skip");
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void skip2StateTest() throws ReaderException {
        in = new StringReader("test\n");
        lexer = new Lexer(in);
        currentState = new State("skip");
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }
    @Test
    public void skip3StateTest() throws ReaderException {
        in = new StringReader("test\n\n    \n   ");
        lexer = new Lexer(in);
        currentState = new State("skip");
        lexer.read();
        lexer.read();
        lexer.read();
        lexer.read();
        lexer.read();
        assertEquals("wrong", currentState, lexer.getCurrentState());
    }

}
