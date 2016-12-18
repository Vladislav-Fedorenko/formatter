package it.sevenbits.states.lexer;

import it.sevenbits.action.Action;
import it.sevenbits.action.implementation.AddAction;
import it.sevenbits.action.implementation.ReturnAction;
import it.sevenbits.action.implementation.SkipAction;
import it.sevenbits.states.Pair;
import it.sevenbits.states.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager of states of lexer.
 */
public class StateOfLexerManager {
    private Map<Pair<State, Character>, State> mapOfState;
    private State appendState;
    private Map<Pair<State, Character>, Action> mapOfAction;
    /**
     * Constructor
     */
    public StateOfLexerManager() {
        mapOfState = new HashMap<Pair<State, Character>, State>();
        appendState = new State("append");
        State skipState = new State("skip"),
                continueState = new State("continue"),
                returnWithRepeatState = new State("return with repeat"),
                returnWithoutRepeatState = new State("return without repeat");

        mapOfState.put(new Pair<State, Character>(appendState, '{'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(appendState, '}'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(appendState, ';'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(appendState, ' '), skipState);
        mapOfState.put(new Pair<State, Character>(appendState, '\n'), skipState);
        mapOfState.put(new Pair<State, Character>(appendState, '/'), continueState);
        mapOfState.put(new Pair<State, Character>(appendState, '*'), continueState);
        mapOfState.put(new Pair<State, Character>(appendState, '('), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(appendState, ')'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(appendState, '\"'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(appendState, '\''), returnWithRepeatState);
        //mapOfState.put(new Pair<State, Character>(returnWithRepeatState), appendState); not need
        mapOfState.put(new Pair<State, Character>(returnWithoutRepeatState), appendState);
        mapOfState.put(new Pair<State, Character>(skipState, ' '), skipState);
        mapOfState.put(new Pair<State, Character>(skipState, '\n'), skipState);
        mapOfState.put(new Pair<State, Character>(skipState, '{'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(skipState, '}'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(skipState, ';'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(skipState, '/'), continueState);
        mapOfState.put(new Pair<State, Character>(skipState, '*'), continueState);
        mapOfState.put(new Pair<State, Character>(skipState, '('), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(skipState, ')'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(skipState, '\"'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(skipState, '\''), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(skipState), appendState);
        mapOfState.put(new Pair<State, Character>(continueState, '/'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(continueState, '*'), returnWithRepeatState);
        mapOfState.put(new Pair<State, Character>(continueState), returnWithRepeatState);


        /*
            Handlers
        */
        mapOfAction = new HashMap<Pair<State, Character>, Action>();
        mapOfAction.put(new Pair<State, Character>(appendState, '{'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, '}'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, ';'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, ' '), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, '\n'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, '/'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, '*'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, '('), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, ')'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, '\''), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, '\"'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState, '\\'), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(appendState), new AddAction());
        mapOfAction.put(new Pair<State, Character>(skipState, ' '), new SkipAction());
        mapOfAction.put(new Pair<State, Character>(skipState, '\n'), new SkipAction());
        mapOfAction.put(new Pair<State, Character>(skipState), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(returnWithoutRepeatState), new AddAction());
        mapOfAction.put(new Pair<State, Character>(returnWithRepeatState), new ReturnAction());
        mapOfAction.put(new Pair<State, Character>(continueState, '/'), new AddAction());
        mapOfAction.put(new Pair<State, Character>(continueState, '*'), new AddAction());


    }

    public State getInitialState() {
        return appendState;
    }
    /**
     * call method mapOfStates.get(Key k)
     * @param currentState current state of lexer
     * @param c read character
     * @return next state if mapOfStates.get(Key k) != null
     *          current state else
     */
    public State getNextState(final State currentState, final char c) {
        if (mapOfState.containsKey(new Pair<State, Character>(currentState, c))) {
            return mapOfState.get(new Pair<State, Character>(currentState, c));
        }
        if (mapOfState.containsKey(new Pair<State, Character>(currentState))) {
            return mapOfState.get(new Pair<State, Character>(currentState));
        }
        return currentState;
    }

    /**
     * call method mapOfAction.get(key k)
     * @param currentState current state of lexer
     * @param c ?
     * @return action for current state if mapOfAction.get(key k) != null
     *          AddAction else
     */
    public Action getAction(final State currentState, final char c) {
        if (mapOfAction.containsKey(new Pair<State, Character>(currentState, c))) {
            return mapOfAction.get(new Pair<State, Character>(currentState, c));
        }
        if (mapOfAction.containsKey(new Pair<State, Character>(currentState))) {
            return mapOfAction.get(new Pair<State, Character>(currentState));
        }
        return new AddAction();
    }
}
