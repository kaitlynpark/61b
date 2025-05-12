/** Declare file is a part of the deque package. */
package deque;

/**
 * @source https://opendatastructures.org/newhtml/ods/latex/arrays.html#tex2htm-26
 * @source https://algs4.cs.princeton.edu/13stacks/ResizingArrayQueue.java.html
 */

/** Imports to use iterator */
import java.util.Iterator;

/** Creates a new class ArrayDeque. */
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {

    public Iterator<T> iterator() {
        return new ALDIterator();
    }

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INIT_SIZE = 8;
    private static final int INIT_NEXT_FIRST = 4;
    private static final int INIT_NEXT_LAST = 5;
    private static final int RESIZE_AT = 16;

    /** Creates an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[INIT_SIZE];
        size = 0;
        nextFirst = INIT_NEXT_FIRST;
        nextLast = INIT_NEXT_LAST;
    }
    /** Resizes array to target capacity. */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            newItems[i] = items[(nextFirst + 1 + i) % items.length];
        }
        items = newItems;
        nextFirst = -1;
        nextLast = size;
    }

    /** Adds item to the front of the deque. */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    /** Adds item to the end of the deque. */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        if (nextLast == items.length) {
            nextLast = 0;
        }
        items[nextLast] = item;
        size += 1;
        nextLast = (nextLast + 1 + items.length) % items.length;
    }

    /** Returns number of items in deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints items in deque with spaces between each one along with a new line beneath printed items. */
    @Override
    public void printDeque() {
        int i = 0;
        while (i <= size) {
            System.out.print(items[i]);
            i += 1;
        }
    }

    /** Returns and removes first item of deque, returns null if empty. */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / INIT_NEXT_FIRST) && (items.length >= RESIZE_AT)) {
            resize(items.length / INIT_NEXT_FIRST);
        }
        T first = items[(nextFirst + 1 + items.length) % items.length];
        items[(nextFirst + 1 + items.length) % items.length] = null;
        size -= 1;
        nextFirst = (nextFirst + 1 + items.length) % items.length;
        if (nextFirst + 1 == items.length) {
            nextFirst = -1;
        }
        return first;
    }

    /** Returns and removes last item of deque, returns null if empty. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / INIT_NEXT_FIRST) && (items.length >= RESIZE_AT)) {
            resize(items.length / INIT_NEXT_FIRST);
        }
        T last = items[(nextLast - 1 + items.length) % items.length];
        items[(nextLast - 1 + items.length) % items.length] = null;
        size -= 1;
        nextLast = (nextLast - 1 + items.length) % items.length;
        if (nextLast - 1 == items.length) {
            nextLast = 0;
        }
        return last;
    }

    /** Gets item at given index (o index), returns null if empty, deque stays the same. */
    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        } else {
            int newIndex = (nextFirst + 1 + index) % items.length;
            return items[newIndex];
        }
    }

    /** Returns whether o is equal to the deque. */
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            if (((Deque<?>) o).size() == this.size()) {
                for (int i = 0; i < this.size(); i += 1) {
                    if (!((Deque<?>) o).get(i).equals(this.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private class ALDIterator implements Iterator<T> {
        private int pos;

        public ALDIterator() {
            pos = 0;
        }
        public boolean hasNext() {
            return pos < size;
        }
        public T next() {
            T toReturn = get(pos);
            pos += 1;
            return toReturn;
        }
    }
}
