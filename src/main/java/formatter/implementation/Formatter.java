package formatter.implementation;


import closer.CloserException;
import formatter.FormatException;
import formatter.Formatable;
import formatter.StylisationOfCode;
import handler.Handler;
import handler.HandlerException;
import handler.inheritors.OtherCharacterHandler;
import reader.Readable;
import reader.ReaderException;
import table.Table;
import writer.Writable;
import writer.WriterException;

/**
 * The class for read, format from input streams data and write to output stream
 */
public class Formatter implements Formatable {

    private Handler handler;
    private OtherCharacterHandler other;
    private StylisationOfCode style;
    private Table table;

    /**
     * Constructor.
     */
    public Formatter() {
        table = new Table();
        style = new StylisationOfCode();
        other = new OtherCharacterHandler();
        handler = null;
    }
    @Override
    public void format(final Readable in, final Writable out) throws FormatException {
        try {
            style.resetAll();
            char readChar;
            while (!in.isEnd()) {
                readChar = in.readChar();
                handler = table.getHandler(readChar);
                if (handler != null) {
                    handler.handle(out, style, readChar);
                } else {
                    other.handle(out, style, readChar);
                }
            }
            if (style.getCountOfTabs() != 0) {
                throw new HandlerException(new Throwable("error handle: incorrect number of braces"));
            }
        } catch (ReaderException e) {
            throw new FormatException(e);
        } catch (CloserException e) {
            throw new FormatException(e);
        } catch (WriterException e) {
            throw new FormatException(e);
        } catch (HandlerException e) {
            throw new FormatException(e);
        }
    }
}
