package it.sevenbits.states;

/**
 * Class for different states anything.
 */
public class State {
    private String nameOfState;

    /**
     * constructor
     * @param nameOfState name of current state
     */
    public State(final String nameOfState) {
        this.nameOfState = nameOfState;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        State that = (State) o;

        return nameOfState.equals(that.nameOfState);
    }

    @Override
    public int hashCode() {
        return nameOfState.hashCode();
    }
}
