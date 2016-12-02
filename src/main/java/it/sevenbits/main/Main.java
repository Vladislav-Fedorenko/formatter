package it.sevenbits.main;

import it.sevenbits.closer.CloserException;
import it.sevenbits.formatter.FormatException;
import it.sevenbits.formatter.Formatable;
import it.sevenbits.formatter.implementation.Formatter;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.fileReader.FileReader;
import it.sevenbits.reader.implementation.stringReader.StringReader;
import it.sevenbits.writer.WriterException;
import it.sevenbits.writer.implementation.fileWriter.FileWriter;
import it.sevenbits.writer.implementation.stringWriter.StringWriter;

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

            Formatable formatter = new Formatter();
            String s = "main() {/*hello,\n" +
                    " friend!\n" +
                    " how are you?*/if (a == 5) {while (true) {b++;}b=6;}String s = \"{ ; }\";" +
                    "char c = '{';a;b;}";
            Readable<Character> in = new StringReader(s);
            StringWriter out = new StringWriter("");
            formatter.format(in, out);
            System.out.print(out.toString());
            try {
                FileReader fileInput = new FileReader(args[0]);
                FileWriter fileOutput = new FileWriter(args[1]);
                formatter.format(fileInput, fileOutput);
                fileInput.close();
                fileOutput.close();
            } catch (ReaderException e) {
                throw new FormatException("error of read in main", e);
            } catch (WriterException e) {
                throw new FormatException("error of  in main", e);
            } catch (CloserException e) {
                throw new FormatException("error of read in main", e);
            }
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

}
