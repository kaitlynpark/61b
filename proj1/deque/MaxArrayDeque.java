package deque;

import java.util.Comparator;

/**
 * @source https://stackoverflow.com/questions/50561658/comparator-is-referred-to-as-both-an-object-and-an-interface#:
 * ~:text=uti.-,Comparator%20is%20an%20interface.,or%20extended%20by%20other%20interfaces.
 */

/** Constructs new class that inherits from ArrayDeque. */
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> newComparator;

    /**
     * Creates a MaxArrayDeque with the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.newComparator = c;
    }

    /**
     * Returns the maximum element in the deque with previous Comparator. Returns null if empty.
     */
    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T maxVal = this.get(0);
        for (int i = 1; i < this.size(); i += 1) {
            if (newComparator.compare(maxVal, this.get(i)) < 0) {
                maxVal = this.get(i);
            }
        }
        return maxVal;
    }

    /**
     * Returns the maximum element in the deque. Returns null if empty.
     */
    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T maxVal = this.get(0);
        for (int i = 1; i < this.size(); i += 1) {
            if (c.compare(maxVal, this.get(i)) < 0) {
                maxVal = this.get(i);
            }
        }
        return maxVal;
    }
}
