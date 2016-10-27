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
            StringBuilder buffer = new StringBuilder("");
            int countOfTabs = 0;
            char readChar;
            while (!in.isEnd()) {
                readChar = in.read();
                switch (readChar) {
                    case '{':
                        countOfTabs++;
                        buffer.append(" {\n");
                        buffer.append(writeSpaces(countOfTabs));
                        break;
                    case '}':
                        countOfTabs--;
                        buffer.insert(buffer.length() - 1, "}\n");
                        break;
                    case ';':
                        buffer.append(";\n");
                        buffer.append(writeSpaces(countOfTabs));
                        break;
                    case ' ':
                        if (buffer.charAt(buffer.length() - 1) != ' ' && buffer.charAt(buffer.length() - 1) != '\n' ) {
                            buffer.append(" ");
                        }
                        break;
                    default:
                        buffer.append(readChar);
                }
            }
            if (countOfTabs != 0) {
                out.write("error: incorrect number of braces");
                throw new WriterException(new Throwable("error: incorrect number of braces"));
            }
            out.write(buffer.toString());
        } catch (ReaderException e) {
            throw new FormatException(e);
        } catch (WriterException e) {
            throw new FormatException(e);
        } catch (CloserException e) {
            throw new FormatException(e);
        }
    }

    /**
     *
     * @param countOfTabs count of tabs
     * @return string with a specified number of spaces
     */

    private String writeSpaces(final int countOfTabs) {
        String temp = "";
        for (int i = 0; i < countOfTabs; i++) {
            temp += "\t";
        }
        return temp;
    }
}
