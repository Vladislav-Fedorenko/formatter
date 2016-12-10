package it.sevenbits.formatter.implementation;


import it.sevenbits.formatter.FormatException;
import it.sevenbits.formatter.Formatable;
import it.sevenbits.handler.Handler;
import it.sevenbits.handler.HandlerException;
import it.sevenbits.handler.Indent;
import it.sevenbits.reader.Readable;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.states.State;
import it.sevenbits.states.StateException;
import it.sevenbits.states.formatter.StateManager;
import it.sevenbits.writer.Writable;

/**
 * The class for read, format from input streams data and write to output stream
 */
public class Formatter implements Formatable {

    /**
     * Constructor.
     */
    public Formatter() {
    }
    @Override
    public void format(final Readable<Token> in, final Writable<String> out) throws FormatException {
        try {
            Indent indent = new Indent();
            StateManager stateManager = new StateManager();
            State state = stateManager.getInitialState();
            Token token;
            while (!in.isEnd()) {
                token = in.read();
                String lexeme = token.getLexeme();
                Handler handler = stateManager.getHandler(state, token);
                handler.handle(out, indent, lexeme);
                state = stateManager.getNextState(state, token);
            }
            if (indent.getCurrentIndent() != 0) {
                throw new StateException("error handle: incorrect number of braces", new Throwable());
            }
        } catch (ReaderException e) {
            throw new FormatException("error of read in Formatter", e);
        } catch (StateException e) {
            throw new FormatException("error of state in Formatter", e);
        } catch (HandlerException e) {
            throw new FormatException("error of handle in Formatter", e);
        }
    }
}
