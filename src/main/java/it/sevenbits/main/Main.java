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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Main class
 */
public final class Main {
    /**
     * LOGGER
     */
    private static final Logger LOGGER =
                LoggerFactory.getLogger(Main.class);
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

            try {
                Formatable formatter = new Formatter();
                FileReader in = new FileReader(args[0]);
                LOGGER.info("open file for read");
                FileWriter out = new FileWriter(args[1]);
                LOGGER.info("open file for write");
                Readable<Token> lexer = new Lexer(in);
                formatter.format(lexer, out);
                in.close();
                LOGGER.info("close file for read");
                out.close();
                LOGGER.info("close file for write");
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
