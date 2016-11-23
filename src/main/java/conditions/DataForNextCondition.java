package conditions;

/**
 * Class for data for next condition.
 *  @param <T> parameter
 */
public class DataForNextCondition <T> {
    private ICondition currentCondition;
    private T current;

    /**
     * Default constructor
     * @param currentCondition current condition of code
     * @param current current parameter
     */
    public DataForNextCondition(final ICondition currentCondition, final T current) {
        this.currentCondition = currentCondition;
        this.current = current;
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
                && (current != null ? current.equals(that.current) : that.current == null);

    }

    @Override
    public int hashCode() {
        int result = currentCondition != null ? currentCondition.hashCode() : 0;
        result = 31 * result + (current != null ? current.hashCode() : 0);
        return result;
    }
}
