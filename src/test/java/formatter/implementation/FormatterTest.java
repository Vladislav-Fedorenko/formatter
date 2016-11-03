package formatter.implementation;

import closer.CloserException;
import formatter.FormatException;
import formatter.Formatable;
import formatter.implementation.Formatter;
import org.junit.Before;
import org.junit.Test;
import reader.Readable;
import reader.ReaderException;
import reader.implementation.fileReader.FileReader;
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
 *  Test for Formatter.
 */
public class FormatterTest {
    private Formatable formatter;
    private Readable reader;
    private Writable writer;


    @Before
    public void setUp() {
        formatter = new Formatter();
        writer = new StringWriter("");
    }

    @Test
    public void formatTest() throws FormatException {
        String code = "public char firstChar throws MyException{" +
                "           char     first;         if(s!=null){" +
                "  first = s.charAt(0);return first;}" +
                "throw new MyException();}";
        reader = new StringReader(code);
        formatter.format(reader, writer);
        String expected = "public char firstChar throws MyException {\n" +
                "\tchar first;\n" +
                "\tif(s!=null) {\n" +
                "\t\tfirst = s.charAt(0);\n" +
                "\t\treturn first;\n" +
                "\t}\n" +
                "\tthrow new MyException();\n" +
                "}\n";
        assertEquals("wrong", expected, writer.toString());
    }
    @Test(expected = FormatException.class)
    public void formatWithIncorrectNumberBranchTest() throws FormatException {
        String code = "public char firstChar throws MyException{" +
                "           char     first;         if(s!=null){" +
                "  first = s.charAt(0);return first;}" +
                "throw new MyException();";
        reader = new StringReader(code);
        formatter.format(reader, writer);
    }

    @Test(expected = FormatException.class)
    public void formatWithExceptionTest() throws IOException, ReaderException, CloserException, WriterException, FormatException {
        File file = File.createTempFile("tmp", "txt");
        FileReader fileReader = new FileReader(file.getAbsolutePath());
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        fileReader.close();
        formatter.format(fileReader, fileWriter);
        fail();
    }

}
