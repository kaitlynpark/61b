package deque;

import org.junit.Test;

import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    public void visualizeArray() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
    }

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> ald1 = new ArrayDeque<String>();

        assertTrue("A newly initialized ALDeque should be empty", ald1.isEmpty());
        ald1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ald1.size());
        assertFalse("ald1 should now contain 1 item", ald1.isEmpty());

        ald1.addLast("middle");
        assertEquals(2, ald1.size());

        ald1.addLast("back");
        assertEquals(3, ald1.size());

        System.out.println("Printing out deque: ");
        ald1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> ald1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("ald1 should be empty upon initialization", ald1.isEmpty());

        ald1.addFirst(10);
        // should not be empty
        assertFalse("ald1 should contain 1 item", ald1.isEmpty());

        ald1.removeFirst();
        // should be empty
        assertTrue("ald1 should be empty after removal", ald1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ald1 = new ArrayDeque<>();
        ald1.addFirst(3);

        ald1.removeLast();
        ald1.removeFirst();
        ald1.removeLast();
        ald1.removeFirst();

        int size = ald1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> ald1 = new ArrayDeque<String>();
        ArrayDeque<Double> ald2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ald3 = new ArrayDeque<Boolean>();

        ald1.addFirst("string");
        ald2.addFirst(3.14159);
        ald3.addFirst(true);

        String s = ald1.removeFirst();
        double d = ald2.removeFirst();
        boolean b = ald3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> ald1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ald1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ald1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigALDequeTest() {

        ArrayDeque<Integer> ald1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            ald1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ald1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ald1.removeLast(), 0.0);
        }
    }

    @Test
    public void addRemoveLoop() {
        ArrayDeque<Integer> ald = new ArrayDeque<Integer>();
        for (int i = 0; i < 9; i++) {
            ald.addLast(i);
        }
        ald.removeFirst();
        ald.removeLast();
        ald.addFirst(-1);
        ald.addLast(10);
        ald.removeFirst();
        ald.removeLast();
        ald.addFirst(-1);
        ald.addLast(10);
        ald.removeFirst();
        ald.removeLast();
        ald.removeFirst();
        ald.removeLast();
        ald.removeFirst();
        ald.removeLast();
        ald.removeFirst();
        ald.removeLast();
        ald.removeFirst();
        ald.removeLast();
        ald.addFirst(-1);
        ald.addLast(10);
        ald.addFirst(-1);
        ald.addLast(10);
        ald.addFirst(-1);
        ald.addLast(10);
        ald.addFirst(-1);
        ald.addLast(10);
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> ald = new ArrayDeque<Integer>();
        for (int i = 0; i < 3; i++) {
            ald.addLast(i);
        }
        int result = ald.get(1);
    }

    @Test
    public void randomTest() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();
        ArrayDeque.addFirst(0);
        ArrayDeque.addFirst(1);
        ArrayDeque.addLast(2);
        ArrayDeque.removeFirst();
        ArrayDeque.addFirst(4);
        ArrayDeque.removeFirst();
        ArrayDeque.removeFirst();
        ArrayDeque.addLast(7);
        ArrayDeque.removeFirst();
        ArrayDeque.get(0);
        ArrayDeque.removeLast();
        ArrayDeque.addLast(11);
        ArrayDeque.removeLast();
        ArrayDeque.addLast(13);
        ArrayDeque.removeFirst();
        ArrayDeque.addLast(15);
        ArrayDeque.addLast(16);
        ArrayDeque.removeLast();
        ArrayDeque.removeLast();
    }

    @Test
    public void grrTest() {
        ArrayDeque<Integer> MaxArrayDeque = new ArrayDeque<Integer>();
        MaxArrayDeque.addFirst(0);
        MaxArrayDeque.removeLast();
        MaxArrayDeque.addLast(2);
        MaxArrayDeque.removeFirst();
    }

    @Test
    public void uhhTest() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();
        ArrayDeque.addLast(0);
        ArrayDeque.get(0);
        ArrayDeque.removeFirst();
        ArrayDeque.addLast(3);
        ArrayDeque.removeFirst();
        ArrayDeque.addLast(5);
        ArrayDeque.removeFirst();
        ArrayDeque.addLast(7);
        ArrayDeque.removeLast();
        ArrayDeque.addLast(9);
        ArrayDeque.removeFirst();
        ArrayDeque.addFirst(11);
        ArrayDeque.get(0);
        ArrayDeque.get(0);
        ArrayDeque.get(0);
        ArrayDeque.removeFirst();
        ArrayDeque.addFirst(16);
        ArrayDeque.removeLast();
        ArrayDeque.addFirst(18);
        ArrayDeque.removeLast();
        ArrayDeque.addLast(20);
    }

    @Test
    public void hmmTest() {
        ArrayDeque<Integer> MaxArrayDeque = new ArrayDeque<Integer>();
        MaxArrayDeque.addLast(0);
        MaxArrayDeque.removeFirst();
        MaxArrayDeque.addLast(2);
        MaxArrayDeque.addLast(3);
        MaxArrayDeque.removeLast();
        MaxArrayDeque.addFirst(5);
        MaxArrayDeque.addFirst(6);
        MaxArrayDeque.get(2);
        MaxArrayDeque.addLast(8);
        MaxArrayDeque.addLast(9);
        MaxArrayDeque.get(1);
        MaxArrayDeque.removeLast();
        MaxArrayDeque.removeLast();
        MaxArrayDeque.addLast(13);
    }
}
