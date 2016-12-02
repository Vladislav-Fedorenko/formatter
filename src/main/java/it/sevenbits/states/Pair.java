package it.sevenbits.states;

/**
 *  Pair variables type of T and U.
 *  @param <T> parameter
 *  @param <U> parameter
 */
class Pair<T, U> {
    private T param1;
    private U param2;

    /**
     * Constructor with two parameters
     * @param param1 variable type of T
     * @param param2 variable type of U
     */
    Pair(final T param1, final U param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    /**
     * Constructor with one parameter
     * @param param1 variable type of T
     */
    Pair(final T param1) {
        this.param1 = param1;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return (param1 != null ? param1.equals(pair.param1) : pair.param1 == null)
                && (param2 != null ? param2.equals(pair.param2) : pair.param2 == null);
    }

    @Override
    public int hashCode() {
        int result = param1 != null ? param1.hashCode() : 0;
        result = 31 * result + (param2 != null ? param2.hashCode() : 0);
        return result;
    }


}
