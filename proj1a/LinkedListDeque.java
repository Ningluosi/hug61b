public class LinkedListDeque<T> {

    private class ListNode {
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

    public void addFirst(T item) {
        ListNode node = new ListNode(sentiel, item, sentiel.next);

        if (size == 0) {
            sentiel.prev = node;
        } else {
            sentiel.next.prev = node;
        }
        sentiel.next = node;
        size++;
    }

    public void addLast(T item) {
        ListNode node = new ListNode(sentiel.prev, item, sentiel);

        if (size == 0) {
            sentiel.next = node;
        } else {
            sentiel.prev.next = node;
        }
        sentiel.prev = node;
        size++;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode p = sentiel.next;

        while (p != sentiel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = sentiel.next.item;
        ListNode p = sentiel.next.next;
        sentiel.next = p;
        p.prev = sentiel;
        size--;
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = sentiel.prev.item;
        ListNode p = sentiel.prev.prev;
        sentiel.prev = p;
        p.next = sentiel;
        size--;
        return ret;
    }

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
