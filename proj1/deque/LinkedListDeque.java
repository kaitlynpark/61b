/** Declare file is a part of the deque package. */
package deque;

/** Imports to use iterator */
import java.util.Iterator;

/** Creates a new class LinkedListDeque with a first and rest, rest points to another linked list. */
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    public Iterator<T> iterator() {
        return new LLDIterator();
    }

    private class TNode {
        private T item;
        private TNode next;
        private TNode prev;

        public TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private TNode sentinel;
    private int size;

    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        size = 0;
    }

    /** Adds item to the front of the deque. */
    @Override
    public void addFirst(T item) {
        size += 1;
        if (sentinel.next == null) {
            TNode nN = new TNode(item, null, null);
            sentinel.next = nN;
            sentinel.prev = nN;
        } else {
            TNode nN = new TNode(item, sentinel.next, sentinel);
            sentinel.next = nN;
            nN.next.prev = nN;
        }
    }

    /** Adds item to the end of the deque. */
    @Override
    public void addLast(T item) {
        size += 1;
        if (sentinel.next == null) {
            TNode nN = new TNode(item, null, null);
            sentinel.next = nN;
            sentinel.prev = nN;
        } else {
            TNode nN = new TNode(item, sentinel, sentinel.prev);
            sentinel.prev = nN;
            nN.prev.next = nN;
        }
    }

    /** Returns number of items in deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints items in deque with spaces between each one along with a new line beneath printed items. */
    @Override
    public void printDeque() {
        TNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            p = p.next;
        }
    }

    /** Returns and removes first item of deque, returns null if empty. */
    @Override
    public T removeFirst() {
        if (sentinel.next == null) {
            return null;
        } else if (sentinel.next.next == null) {
            T first = sentinel.next.item;
            sentinel.next.prev = null;
            sentinel.next = null;
            sentinel.prev.next = null;
            sentinel.prev = null;
            size -= 1;
            return first;
        } else {
            T first = sentinel.next.item;
            sentinel.next.prev = null;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev.next = null;
            sentinel.next.prev = null;
            sentinel.next.prev = sentinel;
            size -= 1;
            return first;
        }
    }

    /** Returns and removes last item of deque, returns null if empty. */
    @Override
    public T removeLast() {
        if (sentinel.next == null) {
            return null;
        } else if (sentinel.prev.prev == null) {
            T last = sentinel.prev.item;
            sentinel.next.prev = null;
            sentinel.prev.next = null;
            sentinel.prev = null;
            sentinel.next = null;
            size -= 1;
            return last;
        } else {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next.next = null;
            sentinel.prev.next.prev = null;
            sentinel.prev.next = null;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last;
        }
    }

    /** Gets item at given index (o index), returns null if empty, deque stays the same. */
    @Override
    public T get(int index) {
        if (index == 0) {
            return sentinel.next.item;
        }
        TNode current = sentinel.next.next;
        while (index > 1 && current.next != null) {
            index -= 1;
            current = current.next;
        }
        return current.item;
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

    /** Helper method for getRecursive. */
    private TNode helper(int index, TNode recursiveNode) {
        if (index == 0) {
            return recursiveNode;
        } else {
            return helper(index - 1, recursiveNode.next);
        }
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        TNode tempNode = sentinel.next;
        return helper(index, tempNode).item;
    }

    private class LLDIterator implements Iterator<T> {
        private int pos;

        public LLDIterator() {
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
