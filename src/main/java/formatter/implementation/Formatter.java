package formatter.implementation;


import closer.CloserException;
import formatter.FormatException;
import formatter.Formatable;
import reader.Readable;
import reader.ReaderException;
import writer.Writable;
import writer.WriterException;

/**
 * The class for read, format from input streams data and write to output stream
 */
public class Formatter implements Formatable {

    @Override
    public void format(final Readable in, final Writable out) throws FormatException {
        try {
                boolean isNeededClosedBrace = false;
                int countOfTabs = 0;
                char readChar;
                char buffer = '!'; //simple character
                while (!in.isEnd()) {
                    readChar = in.readChar();
                    switch (readChar) {
                        case '{':
                            countOfTabs++;
                            out.writeChar(' ');
                            out.writeChar(readChar);
                            out.writeChar('\n');
                            writeSpaces(countOfTabs, out);
                            buffer = '\t';
                            break;
                        case '}':
                            countOfTabs--;
                            out.writeChar(readChar);
                            out.writeChar('\n');
                            writeSpaces(countOfTabs - 1, out);
                            buffer = '\n';
                            break;
                        case ';':
                            out.writeChar(readChar);
                            out.writeChar('\n');
                            writeSpaces(countOfTabs - 1, out);
                            buffer = '\t';
                            isNeededClosedBrace = true;
                            break;
                        case ' ':
                            if (buffer != ' ' && buffer != '\n' && buffer != '\t') {
                                out.writeChar(' ');
                                buffer = ' ';
                            }
                            break;
                        default:
                            if (isNeededClosedBrace) {
                                out.writeChar('\t');
                            }
                            out.writeChar(readChar);
                            buffer = readChar;
                            isNeededClosedBrace = false;
                }
            }
            if (countOfTabs != 0) {
                throw new WriterException(new Throwable("error: incorrect number of braces"));
            }
        } catch (ReaderException e) {
            throw new FormatException(e);
        } catch (WriterException e) {
            throw new FormatException(e);
        } catch (CloserException e) {
            throw new FormatException(e);
        }
    }

    /**
     * Write TABs to the output stream.
     * @param countOfTabs count of tabs
     * @throws WriterException thrown if any errors occur writing
     * @throws CloserException thrown if any errors occur closing
     */

    private void writeSpaces(final int countOfTabs, final Writable out) throws WriterException, CloserException {
        for (int i = countOfTabs; i > 0; i--) {
            out.writeChar('\t');
        }
    }
}
