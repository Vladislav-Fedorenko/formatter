package it.sevenbits.main;

import it.sevenbits.closer.CloserException;
import it.sevenbits.formatter.FormatException;
import it.sevenbits.formatter.Formatable;
import it.sevenbits.formatter.implementation.Formatter;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.filereader.FileReader;
import it.sevenbits.reader.implementation.lexer.Lexer;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.writer.WriterException;
import it.sevenbits.writer.implementation.filewriter.FileWriter;

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
            /*String s = "main() {*//*hello,\n" +
                    " friend!\n" +
                    " how are you?*//*if (a == 5) {while (true) {b++;}b=6;}String s = \"{ ; }\";" +
                    "char c = '{';a;b;}";
            Readable<Character> in = new StringReader(s);
            StringWriter out = new StringWriter("");
            Readable<Token> lexer = new Lexer(in);
            formatter.format(lexer, out);
            System.out.print(out.toString());*/
            try {
                Formatable formatter = new Formatter();
                FileReader in = new FileReader(args[0]);
                FileWriter out = new FileWriter(args[1]);
                Readable<Token> lexer = new Lexer(in);
                formatter.format(lexer, out);
                in.close();
                out.close();
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
