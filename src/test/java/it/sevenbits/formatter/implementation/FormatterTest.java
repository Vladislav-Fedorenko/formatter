package it.sevenbits.formatter.implementation;

import it.sevenbits.formatter.FormatException;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.lexer.Lexer;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.reader.implementation.stringreader.StringReader;
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
 * Tests for formatter.
 */
public class FormatterTest {
    private Formatter formatter;
    private Readable<Token> inToken;
    private Readable<Character> inChar;
    private Writable<String> out;

    @Before
    public void setUp() {
        formatter = new Formatter();
        out = new StringWriter("");
    }

    @Test
    public void openAndCloseBracketTest() throws FormatException {
        inChar = new StringReader("m(){}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void semicolonTest() throws FormatException {
        inChar = new StringReader("m(){a;}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n    a;\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void openAndCloseRoundBracketTest() throws FormatException {
        inChar = new StringReader("m(int i){}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(int i){\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void multiLineCommentTest() throws FormatException {
        inChar = new StringReader("m(){/*multi {\nline }\ncomment ;*/}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n    /*multi {\n     * line }\n     * comment ;*/\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void singleLineCommentTest() throws FormatException {
        inChar = new StringReader("m(){//one line { } ;\n}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n    //one line { } ;\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void escapeSequenceInDoubleQuoteTest() throws FormatException {
        inChar = new StringReader("m(){String s = \"\'\\ \";}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n    String s = \"\'\\ \";\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void escapeSequenceInSingleQuoteTest() throws FormatException {
        inChar = new StringReader("m(){char c = \'\"\';}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n    char c = \'\"\';\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void cycleForTest() throws FormatException {
        inChar = new StringReader("m(){for(int i = 0;i < 10;i++){j = i+i;}}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n    for(int i = 0;i < 10;i++){\n        j = i+i;\n    }\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void indentTest() throws FormatException {
        inChar = new StringReader("m(){while(true){a;}}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n    while(true){\n        a;\n    }\n}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test
    public void indent2Test() throws FormatException {
        inChar = new StringReader("m(){while(true){a = 5;if(a==5){a=6;for(;;){}}}}");
        inToken = new Lexer(inChar);
        formatter.format(inToken, out);
        String expected = "m(){\n" +
                "    while(true){\n" +
                "        a = 5;\n" +
                "        if(a==5){\n" +
                "            a=6;\n" +
                "            for(;;){\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        assertEquals("wrong", expected, out.toString());
    }
    @Test(expected = FormatException.class)
    public void formatWithWriterExceptionTest() throws WriterException, FormatException {
        out = mock(Writable.class);
        doThrow(WriterException.class).when(out).write("");
        formatter.format(new Lexer(new StringReader("m(){}")), out);
        fail();
    }
    @Test(expected = FormatException.class)
    public void formatWithReaderExceptionTest() throws ReaderException, FormatException {
        inToken = mock(Readable.class);
        doThrow(ReaderException.class).when(inToken).read();
        formatter.format(inToken, out);
        fail();
    }
}
