package it.sevenbits.formatter.implementation;


import it.sevenbits.formatter.FormatException;
import it.sevenbits.formatter.Formatable;
import it.sevenbits.handler.formatter.Handler;
import it.sevenbits.handler.formatter.HandlerException;
import it.sevenbits.handler.formatter.Indent;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.states.State;
import it.sevenbits.states.formatter.StateManager;
import it.sevenbits.writer.Writable;
import it.sevenbits.writer.WriterException;

/**
 * The class for read, format from input streams data and write to output stream
 */
public class Formatter implements Formatable {
    private State state;
    /**
     * Constructor.
     */
    public Formatter() {
    }
    @Override
    public void format(final Readable<Token> in, final Writable<String> out) throws FormatException {
        try {
            Token token;
            Indent indent = new Indent();
            StateManager stateManager = new StateManager();
            state = stateManager.getInitialState();
            while (!in.isEnd()) {
                token = in.read();
                String lexeme = token.getLexeme();
                Handler handler = stateManager.getHandler(state, token);
                handler.handle(out, indent, lexeme);
                state = stateManager.getNextState(state, token);
            }
            if (indent.getCurrentIndent() != 0) {
                out.write("incorrect number of braces");
                /*throw new StateException("error handle: incorrect number of braces", new Throwable());*/
            }
        } catch (ReaderException e) {
            throw new FormatException("error of read in Formatter", e);
        } catch (WriterException e) {
            throw new FormatException("error of write in Formatter", e);
        } catch (HandlerException e) {
            throw new FormatException("error of handle in Formatter", e);
        }
    }

    /**
     * Method for get current state of formatter
     * @return current state
     */
    public State getCurrentState() {
        return state;
    }
}
