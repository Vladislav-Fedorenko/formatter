package it.sevenbits.states;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.Indent;

/*import it.sevenbits.handler.inheritors.CharOfNewLineInMultiLineCommentHandler;
import it.sevenbits.handler.inheritors.CharOfNewLineHandler;
import it.sevenbits.handler.inheritors.CloseBracketHandler;
import it.sevenbits.handler.inheritors.DefaultHandler;
import it.sevenbits.handler.inheritors.IndentHandler;
import it.sevenbits.handler.inheritors.OpenBracketHandler;
import it.sevenbits.handler.inheritors.QuotesHandler;
import it.sevenbits.handler.inheritors.SemicolonHandler;


import it.sevenbits.states.implementation.DefaultState;
import it.sevenbits.states.implementation.BeginOfCommentState;
import it.sevenbits.states.implementation.DoubleQuoteState;
import it.sevenbits.states.implementation.EndOfCommentState;
import it.sevenbits.states.implementation.EscapeSequenceInSingleQuoteState;
import it.sevenbits.states.implementation.EscapeSequenceInDoubleQuoteState;
import it.sevenbits.states.implementation.IndentState;
import it.sevenbits.states.implementation.MultiLineCommentState;
import it.sevenbits.states.implementation.SingleLineCommentState;
import it.sevenbits.states.implementation.SingleQuoteState;*/

import it.sevenbits.handler.inheritors.CharOfNewLineInMultiLineCommentHandler;
import it.sevenbits.handler.inheritors.CharOfNewLineHandler;
import it.sevenbits.handler.inheritors.CloseBracketHandler;
import it.sevenbits.handler.inheritors.DefaultHandler;
import it.sevenbits.handler.inheritors.IndentHandler;
import it.sevenbits.handler.inheritors.OpenBracketHandler;
import it.sevenbits.handler.inheritors.SemicolonHandler;


import it.sevenbits.states.implementation.DefaultState;
import it.sevenbits.states.implementation.BeginOfCommentState;
import it.sevenbits.states.implementation.DoubleQuoteState;
import it.sevenbits.states.implementation.EndOfCommentState;
import it.sevenbits.states.implementation.EscapeSequenceInSingleQuoteState;
import it.sevenbits.states.implementation.EscapeSequenceInDoubleQuoteState;
import it.sevenbits.states.implementation.IndentState;
import it.sevenbits.states.implementation.MultiLineCommentState;
import it.sevenbits.states.implementation.SingleLineCommentState;
import it.sevenbits.states.implementation.SingleQuoteState;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager of states.
 */
public class StateManager {
    private Map<Pair<IState, Character>, IState> mapOfStates;
    private Map<Pair<IState, Character>, Handler> mapOfHandlers;
    private IState defaultState;

    /**
     * Default constructor
     * @param indent current indent
     */
    public StateManager(final Indent indent) {
        mapOfStates = new HashMap<Pair<IState, Character>, IState>();

        defaultState = new DefaultState(indent, this);
        IState doubleQuoteState = new DoubleQuoteState(indent, this);
        IState singleQuoteState = new SingleQuoteState(indent, this);
        IState multiLineCommentState = new MultiLineCommentState(indent, this);
        IState singleLineCommentState = new SingleLineCommentState(indent, this);
        IState beginOfCommentState = new BeginOfCommentState();
        IState endOfCommentState = new EndOfCommentState();
        IState indentState = new IndentState(indent, this);
        IState escapeSequenceInDoubleQuoteState = new EscapeSequenceInDoubleQuoteState(this);
        IState escapeSequenceInSingleQuoteState = new EscapeSequenceInSingleQuoteState(this);

        mapOfStates.put(new Pair<IState, Character>(defaultState, '/'), beginOfCommentState);
        mapOfStates.put(new Pair<IState, Character>(defaultState, '\"'), doubleQuoteState);
        mapOfStates.put(new Pair<IState, Character>(defaultState, '\''), singleQuoteState);
        mapOfStates.put(new Pair<IState, Character>(defaultState, '{'), indentState);
        mapOfStates.put(new Pair<IState, Character>(defaultState, ';'), indentState);
        mapOfStates.put(new Pair<IState, Character>(doubleQuoteState, '\"'), defaultState);
        mapOfStates.put(new Pair<IState, Character>(doubleQuoteState, '\\'), escapeSequenceInDoubleQuoteState);
        mapOfStates.put(new Pair<IState, Character>(singleQuoteState, '\''), defaultState);
        mapOfStates.put(new Pair<IState, Character>(singleQuoteState, '\\'), escapeSequenceInSingleQuoteState);
        mapOfStates.put(new Pair<IState, Character>(multiLineCommentState, '*'), endOfCommentState);
        mapOfStates.put(new Pair<IState, Character>(singleLineCommentState, '\n'), indentState);
        mapOfStates.put(new Pair<IState, Character>(beginOfCommentState, '/'), singleLineCommentState);
        mapOfStates.put(new Pair<IState, Character>(beginOfCommentState, '*'), multiLineCommentState);
        mapOfStates.put(new Pair<IState, Character>(endOfCommentState, '/'), indentState);
        mapOfStates.put(new Pair<IState, Character>(indentState, '/'), beginOfCommentState);
        mapOfStates.put(new Pair<IState, Character>(indentState), defaultState);
        mapOfStates.put(new Pair<IState, Character>(escapeSequenceInDoubleQuoteState), doubleQuoteState);
        mapOfStates.put(new Pair<IState, Character>(escapeSequenceInSingleQuoteState), singleQuoteState);


        mapOfHandlers = new HashMap<Pair<IState, Character>, Handler>();

        mapOfHandlers.put(new Pair<IState, Character>(defaultState, '{'), new OpenBracketHandler());
        mapOfHandlers.put(new Pair<IState, Character>(defaultState, '}'), new CloseBracketHandler());
        mapOfHandlers.put(new Pair<IState, Character>(defaultState, ';'), new SemicolonHandler());
        mapOfHandlers.put(new Pair<IState, Character>(defaultState, '\n'), new CharOfNewLineHandler());
        mapOfHandlers.put(new Pair<IState, Character>(multiLineCommentState, '\n'), new CharOfNewLineInMultiLineCommentHandler());
        mapOfHandlers.put(new Pair<IState, Character>(indentState, '}'), new CloseBracketHandler());
        mapOfHandlers.put(new Pair<IState, Character>(indentState), new IndentHandler());
        mapOfHandlers.put(new Pair<IState, Character>(escapeSequenceInDoubleQuoteState, '\"'), new DefaultHandler());
        mapOfHandlers.put(new Pair<IState, Character>(escapeSequenceInDoubleQuoteState, '\''), new DefaultHandler());
        mapOfHandlers.put(new Pair<IState, Character>(escapeSequenceInSingleQuoteState, '\"'), new DefaultHandler());
        mapOfHandlers.put(new Pair<IState, Character>(escapeSequenceInSingleQuoteState, '\''), new DefaultHandler());
    }

    /**
     * call method mapOfStates.get(Key k)
     * @param currentState current state of code
     * @param c read character
     * @return next state if mapOfStates.get() != null
     *          current state else
     */
    public IState getNextState(final IState currentState, final char c) {
        if (mapOfStates.containsKey(new Pair<IState, Character>(currentState, c))) {
            return mapOfStates.get(new Pair<IState, Character>(currentState, c));
        }
        if (mapOfStates.containsKey(new Pair<IState, Character>(currentState))) {
            return mapOfStates.get(new Pair<IState, Character>(currentState));
        }
        return currentState;
    }

    /**
     * Method for initialization state
     * @return initial state
     */
    public IState getInitialState() {
        return defaultState;
    }

    /**
     * call method mapOfHandlers.get(Key k)
     * @param c input character
     * @param currentState current state of code
     * @return handler for input character
     */
    public Handler getHandler(final IState currentState, final char c) {
        if (mapOfHandlers.containsKey(new Pair<IState, Character>(currentState, c))) {
            return mapOfHandlers.get(new Pair<IState, Character>(currentState, c));
        }
        if (mapOfHandlers.containsKey(new Pair<IState, Character>(currentState))) {
            return mapOfHandlers.get(new Pair<IState, Character>(currentState));
        }
        return new DefaultHandler();
    }

}
