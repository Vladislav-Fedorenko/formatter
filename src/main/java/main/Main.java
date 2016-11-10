package main;

import closer.CloserException;
import formatter.FormatException;
import formatter.Formatable;
import formatter.implementation.Formatter;
import reader.Readable;
import reader.ReaderException;
import reader.implementation.fileReader.FileReader;
import reader.implementation.stringReader.StringReader;
import writer.WriterException;
import writer.implementation.fileWriter.FileWriter;
import writer.implementation.stringWriter.StringWriter;

/**
 * Main class
 */
public final class Main {

    /**
     * Default constructor.
     */
    private Main() {

    }
    /**
     *
     * @param args input arguments
     * @throws FormatException thrown when any occur formatting errors
     */
    public static void main(final String[] args) throws FormatException {
        try {
            String s = "       public char firstChar(final String s) throws MyException{          " +
                    "  char     first; //single line\n        if(s!=null){  first = s.charAt(0);" +
                    "/*//*****{  ' '   }comment     *\n\tmultiline**/return first;}throw new MyException();}";
            Readable in = new StringReader(s);
            StringWriter out = new StringWriter("");
            Formatable formatter = new Formatter();
            formatter.format(in, out);
            System.out.print(out.toString());
            try {
                FileReader fileInput = new FileReader(args[0]);
                FileWriter fileOutput = new FileWriter(args[1]);
                formatter.format(fileInput, fileOutput);
                fileInput.close();
                fileOutput.close();
            } catch (ReaderException e) {
                throw new FormatException(e);
            } catch (WriterException e) {
                throw new FormatException(e);
            } catch (CloserException e) {
                throw new FormatException(e);
            }
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

}
