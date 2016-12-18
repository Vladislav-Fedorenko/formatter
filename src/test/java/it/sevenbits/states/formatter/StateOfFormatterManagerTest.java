package it.sevenbits.states.formatter;

import it.sevenbits.formatter.FormatException;
import it.sevenbits.formatter.implementation.Formatter;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.implementation.lexer.Lexer;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.reader.implementation.stringreader.StringReader;
import it.sevenbits.states.State;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.implementation.stringwriter.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests for states of formatter
 */
public class StateOfFormatterManagerTest {
    private Formatter formatter;
    private Readable<Token> inToken;
    private Readable<Character> inChar;
    private Writable<String> out;
    private State currentState;
    private StateManager stateManager;

    @Before
    public void setUp() {
        out = new StringWriter("");
        formatter = new Formatter();
        stateManager = new StateManager();
    }

    @Test
    public void getInitialStateTest() {
        currentState = new State("default");
        assertEquals("wrong", currentState, stateManager.getInitialState());
    }
    @Test
    public void doubleQuoteStateTest() throws FormatException {
        inChar = new StringReader("s = \"f");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("double quote");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void singleQuoteStateTest() throws FormatException {
        inChar = new StringReader("c = \'c");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("single quote");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void multiLineCommentStateTest() throws FormatException {
        inChar = new StringReader("ab/* multiline");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("multi line comment");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void multiLineComment2StateTest() throws FormatException {
        inChar = new StringReader("ab/* multiline\n");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("multi line comment");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void singleLineCommentStateTest() throws FormatException {
        inChar = new StringReader("test// single line");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("single line comment");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void cycleForStateTest() throws FormatException {
        inChar = new StringReader(" for(");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("cycle for");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void indentStateTest() throws FormatException {
        inChar = new StringReader("{a;");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("indent");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void indent2StateTest() throws FormatException {
        inChar = new StringReader("main(){");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("indent");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void escapeSequenceInDoubleQuoteStateTest() throws FormatException {
        inChar = new StringReader("\"\\");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("escape sequence in double quote");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
    @Test
    public void escapeSequenceInSingleQuoteStateTest() throws FormatException {
        inChar = new StringReader("\'\\");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        currentState = new State("escape sequence in single quote");
        assertEquals("wrong", currentState, formatter.getCurrentState());
    }
}
