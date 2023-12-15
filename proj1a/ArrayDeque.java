public class ArrayDeque<T> {
    private T[] items = (T[]) new Object[8];
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        items[nextFirst--] = item;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        size++;
    }

    /*  Adds an item of type T to the back of the deque */
    public void addLast(T item) {
        items[nextLast++] = item;
        if (nextLast >= items.length) {
            nextLast = 0;
        }
        size++;
    }

    /* Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    /* Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space */
    public void printDeque() {
        if (size > 0) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    System.out.print(items[i]);
                    System.out.print(" ");
                }
            }
        }
    }

    /*  Removes and returns the item at the front of the deque. If no such item exists, returns null */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        return null;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null */
    public T removeLast() {
        return null;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
       and so forth. If no such item exists, returns null. Must not alter the deque */
    public T get(int index) {
        return null;
    }
}
