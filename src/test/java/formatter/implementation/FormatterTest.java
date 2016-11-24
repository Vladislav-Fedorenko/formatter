package formatter.implementation;

import closer.CloserException;
import formatter.FormatException;
import formatter.Formatable;
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
    public void singleLineCommentFormatTest() throws FormatException {
        String code = "main() {//one line comment \na;}";
        reader = new StringReader(code);
        formatter.format(reader, writer);
        String expected = "main() {\n" +
                "    //one line comment \n" +
                "    a;\n" +
                "}\n";
        assertEquals("wrong result", expected, writer.toString());
    }
    @Test
    public void multiLineCommentFormatTest() throws FormatException {
        String code = "main() {a; /* multi\nline\ncomment*/b;}";
        reader = new StringReader(code);
        formatter.format(reader, writer);
        String expected = "main() {\n" +
                "    a;\n" +
                "     /* multi\n" +
                "    * line\n" +
                "    * comment*/\n" +
                "    b;\n" +
                "}\n";
        assertEquals("wrong result", expected, writer.toString());
    }
    @Test
    public void doubleQuoteFormatTest() throws FormatException {
        String code = "main() {a;String s = \" { ; } // /*\";b;}";
        reader = new StringReader(code);
        formatter.format(reader, writer);
        String expected = "main() {\n" +
                "    a;\n" +
                "    String s = \" { ; } // /*\";\n" +
                "    b;\n" +
                "}\n";
        assertEquals("wrong result", expected, writer.toString());
    }
    @Test
    public void singleQuoteFormatTest() throws FormatException {
        String code = "main() {char c = \'{\'; char c1 = \"r\".charAt(0);b;}";
        reader = new StringReader(code);
        formatter.format(reader, writer);
        String expected = "main() {\n" +
                "    char c = '{';\n" +
                "     char c1 = \"r\".charAt(0);\n" +
                "    b;\n" +
                "}\n";
        assertEquals("wrong result", expected, writer.toString());
    }
    @Test(expected = FormatException.class)
    public void formatWithIncorrectNumberBranchTest() throws FormatException {
        String code = "main() {";
        reader = new StringReader(code);
        formatter.format(reader, writer);
        fail();
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
