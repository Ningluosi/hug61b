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

    public void addFirst(T item) {
        items[nextFirst--] = item;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        size++;
    }

    public void addLast(T item) {
        items[nextLast++] = item;
        if (nextLast >= items.length) {
            nextLast = 0;
        }
        size++;
    }
    
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }


    public int size() {
        return size;
    }

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

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[++nextFirst % items.length];
        size--;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item;
        nextLast--;
        if (nextLast < 0) {
            item = items[items.length - 1];
        }
        else {
            item = items[nextLast];
        }

        size--;
        return item;
    }

    public T get(int index) {
        if (size == 0) {
            return null;
        }

        for (int i = 0; i < size; i++) {
            if (i == index) {
                return items[++nextFirst % items.length];
            }
            nextFirst++;
        }

        return null;
    }
}
