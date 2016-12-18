package it.sevenbits.states.formatter;

import it.sevenbits.handler.Handler;
import it.sevenbits.handler.inheritors.ForceCharOfNewLineHandler;
import it.sevenbits.handler.inheritors.CharOfNewLineHandler;
import it.sevenbits.handler.inheritors.CloseBracketHandler;
import it.sevenbits.handler.inheritors.DefaultHandler;
import it.sevenbits.handler.inheritors.IndentHandler;
import it.sevenbits.handler.inheritors.OpenBracketHandler;
import it.sevenbits.handler.inheritors.CharOfNewLineInMultiLineCommentHandler;
import it.sevenbits.handler.inheritors.SemicolonHandler;
import it.sevenbits.reader.implementation.lexer.token.Token;
import it.sevenbits.states.Pair;
import it.sevenbits.states.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager of states.
 */
public class StateManager {
    private Map<Pair<State, Token>, State> mapOfStates;
    private Map<Pair<State, Token>, Handler> mapOfHandlers;
    private State defaultState;

    /**
     * Default constructor
     */
    public StateManager() {
        mapOfStates = new HashMap<Pair<State, Token>, State>();

        defaultState = new State("default");
        State doubleQuoteState = new State("double quote"),
                singleQuoteState = new State("single quote"),
                multiLineCommentState = new State("multi line comment"),
                singleLineCommentState = new State("single line comment"),
                escapeSequenceInDoubleQuoteState = new State("escape sequence in double quote"),
                escapeSequenceInSingleQuoteState = new State("escape sequence in single quote"),
                cycleForState = new State("cycle for"),
                indentState = new State("indent");

        mapOfStates.put(new Pair<State, Token>(defaultState, new Token("\"")), doubleQuoteState);
        mapOfStates.put(new Pair<State, Token>(defaultState, new Token("\'")), singleQuoteState);
        mapOfStates.put(new Pair<State, Token>(defaultState, new Token("/*")), multiLineCommentState);
        mapOfStates.put(new Pair<State, Token>(defaultState, new Token("//")), singleLineCommentState);
        mapOfStates.put(new Pair<State, Token>(defaultState, new Token("for")), cycleForState);
        mapOfStates.put(new Pair<State, Token>(defaultState, new Token("{")), indentState);
        mapOfStates.put(new Pair<State, Token>(defaultState, new Token(";")), indentState);
        mapOfStates.put(new Pair<State, Token>(doubleQuoteState, new Token("\\")), escapeSequenceInDoubleQuoteState);
        mapOfStates.put(new Pair<State, Token>(doubleQuoteState, new Token("\"")), defaultState);
        mapOfStates.put(new Pair<State, Token>(singleQuoteState, new Token("\\")), escapeSequenceInSingleQuoteState);
        mapOfStates.put(new Pair<State, Token>(singleQuoteState, new Token("\'")), defaultState);
        mapOfStates.put(new Pair<State, Token>(multiLineCommentState, new Token("*/")), indentState);
        mapOfStates.put(new Pair<State, Token>(singleLineCommentState, new Token("\n")), indentState);
        mapOfStates.put(new Pair<State, Token>(cycleForState, new Token(")")), defaultState);
        mapOfStates.put(new Pair<State, Token>(escapeSequenceInDoubleQuoteState), doubleQuoteState);
        mapOfStates.put(new Pair<State, Token>(escapeSequenceInSingleQuoteState), singleQuoteState);
        mapOfStates.put(new Pair<State, Token>(indentState, new Token("\"")), doubleQuoteState);
        mapOfStates.put(new Pair<State, Token>(indentState, new Token("\'")), singleQuoteState);
        mapOfStates.put(new Pair<State, Token>(indentState, new Token("/*")), multiLineCommentState);
        mapOfStates.put(new Pair<State, Token>(indentState, new Token("//")), singleLineCommentState);
        mapOfStates.put(new Pair<State, Token>(indentState, new Token("for")), cycleForState);
        mapOfStates.put(new Pair<State, Token>(indentState, new Token("")), indentState);
        mapOfStates.put(new Pair<State, Token>(indentState), defaultState);




        mapOfHandlers = new HashMap<Pair<State, Token>, Handler>();

        mapOfHandlers.put(new Pair<State, Token>(defaultState, new Token("{")), new OpenBracketHandler());
        mapOfHandlers.put(new Pair<State, Token>(defaultState, new Token("}")), new CloseBracketHandler());
        mapOfHandlers.put(new Pair<State, Token>(defaultState, new Token(";")), new SemicolonHandler());
        mapOfHandlers.put(new Pair<State, Token>(multiLineCommentState, new Token("*/")), new ForceCharOfNewLineHandler());
        mapOfHandlers.put(new Pair<State, Token>(multiLineCommentState, new Token("\n")), new CharOfNewLineInMultiLineCommentHandler());
        mapOfHandlers.put(new Pair<State, Token>(singleLineCommentState, new Token("\n")), new CharOfNewLineHandler());
        mapOfHandlers.put(new Pair<State, Token>(escapeSequenceInDoubleQuoteState, new Token("\"")), new DefaultHandler());
        mapOfHandlers.put(new Pair<State, Token>(escapeSequenceInSingleQuoteState, new Token("\'")), new DefaultHandler());
        mapOfHandlers.put(new Pair<State, Token>(indentState, new Token("{")), new CloseBracketHandler());
        mapOfHandlers.put(new Pair<State, Token>(indentState, new Token("")), new DefaultHandler());
        mapOfHandlers.put(new Pair<State, Token>(indentState), new IndentHandler());
    }

    /**
     * call method mapOfStates.get(Key k)
     * @param currentState current state of code
     * @param t read token
     * @return next state if mapOfStates.get() != null
     *          current state else
     */
    public State getNextState(final State currentState, final Token t) {
        if (mapOfStates.containsKey(new Pair<State, Token>(currentState, t))) {
            return mapOfStates.get(new Pair<State, Token>(currentState, t));
        }
        if (mapOfStates.containsKey(new Pair<State, Token>(currentState))) {
            return mapOfStates.get(new Pair<State, Token>(currentState));
        }
        return currentState;
    }

    /**
     * Method for initialization state
     * @return initial state
     */
    public State getInitialState() {
        return defaultState;
    }

    /**
     * call method mapOfHandlers.get(Key k)
     * @param t input token
     * @param currentState current state of code
     * @return handler for input character
     */
    public Handler getHandler(final State currentState, final Token t) {
        if (mapOfHandlers.containsKey(new Pair<State, Token>(currentState, t))) {
            return mapOfHandlers.get(new Pair<State, Token>(currentState, t));
        }
        if (mapOfHandlers.containsKey(new Pair<State, Token>(currentState))) {
            return mapOfHandlers.get(new Pair<State, Token>(currentState));
        }
        return new DefaultHandler();
    }
}
