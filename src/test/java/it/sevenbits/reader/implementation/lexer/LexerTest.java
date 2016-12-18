package it.sevenbits.reader.implementation.lexer;

import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.reader.implementation.stringreader.StringReader;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests for lexer.
 */
public class LexerTest {
    private Readable<Character> in;
    private Lexer lexer;

    @Before
    public void setUp() {
    }

    @Test
    public void separatorOpenBracketTest() throws ReaderException {
        in = new StringReader("test{");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorCloseBracketTest() throws ReaderException {
        in = new StringReader("test}");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorOpenRoundBracketTest() throws ReaderException {
        in = new StringReader("test(");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorCloseRoundBracketTest() throws ReaderException {
        in = new StringReader("test)");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorSpaceTest() throws ReaderException {
        in = new StringReader("test t");
        lexer = new Lexer(in);
        String expected = "test t";
        String actual = lexer.read().getLexeme() + lexer.read().getLexeme() + lexer.read().getLexeme();
        assertEquals("wrong", expected, actual);
    }
    @Test
    public void separatorCharNewLineTest() throws ReaderException {
        in = new StringReader("test\n");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorSemicolonTest() throws ReaderException {
        in = new StringReader("test;");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorSlashTest() throws ReaderException {
        in = new StringReader("test/");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorAsteriskTest() throws ReaderException {
        in = new StringReader("test*");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorDoubleQuoteTest() throws ReaderException {
        in = new StringReader("test\"");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorSingleQuotesTest() throws ReaderException {
        in = new StringReader("test\'");
        lexer = new Lexer(in);
        String expected = "test";
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorOtherTest() throws ReaderException {
        in = new StringReader("{\'");
        lexer = new Lexer(in);
        String expected = "{";
        lexer.read();
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void separatorOther2Test() throws ReaderException {
        in = new StringReader(";{");
        lexer = new Lexer(in);
        String expected = ";";
        lexer.read();
        Token token = lexer.read();
        assertEquals("wrong", expected, token.getLexeme());
    }
    @Test
    public void isEndTest() throws ReaderException {
        in = new StringReader("");
        lexer = new Lexer(in);
        lexer.read();
        assertEquals("wrong", true, lexer.isEnd());
    }
    @Test
    public void isEndNotEmptyTest() throws ReaderException {
        in = new StringReader("a");
        lexer = new Lexer(in);
        lexer.read();
        assertEquals("wrong", true, lexer.isEnd());
    }
}
