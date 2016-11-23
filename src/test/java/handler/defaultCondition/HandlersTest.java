package handler.defaultCondition;

import closer.CloserException;
import formatter.FormatException;
import formatter.Formatable;
import formatter.implementation.Formatter;
import org.junit.Before;
import org.junit.Test;
import reader.Readable;
import reader.implementation.stringReader.StringReader;
import writer.Writable;
import writer.WriterException;
import writer.implementation.fileWriter.FileWriter;
import writer.implementation.stringWriter.StringWriter;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test fr handlers.
 */
public class HandlersTest {
    private Readable reader;
    private Writable writer;
    private Formatable formatter;
    @Before
    public void setUp() {
        reader = new StringReader("       public char firstChar(final String s) throws MyException{    char     first=' '; //single line ; *\n" +
                "  String temp=\"string for test\";             if(s!=null){  first = s.charAt(0);/*//*****{  ''   }comment\n" +
                "         \"        \" multiline**/return first;}throw new MyException();}");
        writer = new StringWriter("");
        formatter = new Formatter();
    }

    @Test
    public void handlersTest() throws FormatException {
        formatter.format(reader, writer);
        String expected = "public char firstChar(final String s) throws MyException {\n" +
                "\tchar first=' ';\n" +
                "//single line ; *\n" +
                "\tString temp=\"string for test\";\n" +
                "\tif(s!=null) {\n" +
                "\t\tfirst = s.charAt(0);\n" +
                "\t/*//*****{  ''   }comment\n" +
                "         \"        \" multiline**/\n" +
                "\t\treturn first;\n" +
                "\t}\n" +
                "\tthrow new MyException();\n" +
                "}\n";
        assertEquals("wrong result", expected, writer.toString());
    }

    @Test(expected = FormatException.class)
    public void handlersWithExceptionTest() throws CloserException, WriterException, FormatException, IOException {
        File file = File.createTempFile("input", "txt");
        FileWriter writer = new FileWriter(file.getAbsolutePath());
        writer.close();
        formatter.format(reader, writer);
        fail();
    }
}
