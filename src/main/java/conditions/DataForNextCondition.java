package conditions;

/**
 * Class for data for next condition.
 *  @param <T> parameter
 */
class DataForNextCondition <T> {
    private ICondition currentCondition;
    private T currentParam;

    /**
     * Constructor on the condition and on the current parameter
     * @param currentCondition currentParam condition of code
     * @param current currentParam parameter
     */
    DataForNextCondition(final ICondition currentCondition, final T current) {
        this.currentCondition = currentCondition;
        this.currentParam = current;
    }

    /**
     * Constructor on the condition
     * @param currentCondition current condition
     */
    DataForNextCondition(final ICondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataForNextCondition<?> that = (DataForNextCondition<?>) o;

        return currentCondition != null ? currentCondition.equals(that.currentCondition) : that.currentCondition == null
                && (currentParam != null ? currentParam.equals(that.currentParam) : that.currentParam == null);

    }

    @Override
    public int hashCode() {
        int result = currentCondition != null ? currentCondition.hashCode() : 0;
        result = 31 * result + (currentParam != null ? currentParam.hashCode() : 0);
        return result;
    }
}
