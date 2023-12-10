/** TODO LIST:
 As your first deque implementation, you’ll build the LinkedListDeque class, which will be linked list based.

 Your operations are subject to the following rules:

 add and remove operations must not involve any looping or recursion. A single such operation must take “constant time”,
 i.e. execution time should not depend on the size of the deque.

 get must use iteration, not recursion.

 size must take constant time.

 The amount of memory that your program uses at any given time must be proportional to the number of items.
 For example, if you add 10,000 items to the deque, and then remove 9,999 items, the resulting size should be more like a deque with 1 item than 10,000.
 Do not maintain references to items that are no longer in the deque.

 Implement all the methods listed above in “The Deque API” section.

 In addition, you also need to implement:

 public LinkedListDeque(): Creates an empty linked list deque.

 public T getRecursive(int index): Same as get, but uses recursion.

 */

public class LinkedListDeque<T> {

    public class ListNode {
        public ListNode prev;
        public T item;
        public ListNode next;

        public ListNode(ListNode prev, T item, ListNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        public T getItem(int index) {
            if (index == 0) {
                return item;
            }

            return next.getItem(index - 1);
        }
    }

    private ListNode sentiel;
    private int size;

    public LinkedListDeque() {
        sentiel = new ListNode(null, null, null);
        sentiel.next = sentiel;
        sentiel.prev = sentiel;
        size = 0;
    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        ListNode node = new ListNode(sentiel, item, sentiel.next);

        if (size == 0) {
            sentiel.prev = node;
        }
        else {
            sentiel.next.prev = node;
        }
        sentiel.next = node;
        size++;
    }

    /*  Adds an item of type T to the back of the deque */
    public void addLast(T item) {
        ListNode node = new ListNode(sentiel.prev, item, sentiel);

        if (size == 0) {
            sentiel.next = node;
        }
        else {
            sentiel.prev.next = node;
        }
        sentiel.prev = node;
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
        ListNode p = sentiel.next;

        while (p != sentiel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /*  Removes and returns the item at the front of the deque. If no such item exists, returns null */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = sentiel.next.item;
        sentiel.next = sentiel.next.next;
        sentiel.next.next.prev = sentiel;
        size--;
        return ret;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = sentiel.prev.item;
        sentiel.prev = sentiel.prev.prev;
        sentiel.prev.prev.next = sentiel;
        size--;
        return ret;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item,
       and so forth. If no such item exists, returns null. Must not alter the deque */
    public T get(int index) {
        int i = 0;
        ListNode p = sentiel.next;
        while (p != sentiel) {
            if (i == index) {
                return p.item;
            }
            p = p.next;
            i++;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (size == 0 || index >= size) {
            return null;
        }

        return sentiel.next.getItem(index);
    }
}
