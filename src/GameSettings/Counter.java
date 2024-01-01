package GameSettings;

/**
 * The Counter class represents a simple counter that can be increased or decreased.
 */
public class Counter {
    private int count;

    /**
     * Increases the counter by a specified value.
     * @param number the value to increase the counter by.
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Decreases the counter by a specified value.
     * @param number the value to decrease the counter by.
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Returns the current value of the counter.
     * @return the current value of the counter.
     */
    public int getValue() {
        return count;
    }
}
