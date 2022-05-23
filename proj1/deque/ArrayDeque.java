package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {

    private T[] items;
    private int size;

    private int startIndex = 0;
    private int endIndex = 0;
    private int capacity = 8;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        size = 0;
    }

    /**
    public ArrayDeque(int x) {
        capacity = x;
        items = (T[]) new Object[capacity];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[capacity];
        size = 0;

        for (int i = 0; i < other.size(); i += 1) {
            addLast((T) other.get(i));
        }
    }
     */

    /** Resizes the underlying array to the target capacity. */
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap * 2];
        if (endIndex == startIndex) {
            System.arraycopy(items, 0, a, 0, endIndex);
            System.arraycopy(items, startIndex, a, startIndex + capacity, capacity - startIndex);
            startIndex += cap;
        } else {
            System.arraycopy(items, 0, a, 0, endIndex);
        }
        items = a;
        capacity = cap * 2;
    }

    public void addFirst(T item) {
        if (endIndex == startIndex) {
            items[startIndex] = item;
            endIndex += 1;
        } else {
            startIndex = (startIndex - 1 + capacity) % capacity;
            items[startIndex] = item;
        }
        size += 1;
        if (size == capacity) {
            resize(capacity);
        }
    }

    public void addLast(T item) {
        items[endIndex] = item;
        endIndex += 1;
        size += 1;
        if (size == capacity) {
            resize(capacity);
        }
    }

    /**
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
     */

    public int size() {
        return size;
    }

    public void printDeque() {
        if (endIndex < startIndex) {
            for (int i = startIndex; i < capacity; i++) {
                System.out.print(items[i] + " ");
            }
        }
        for (int i = 0; i < endIndex; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int temp = startIndex;
        startIndex = (startIndex + 1 + capacity) % capacity;
        size -= 1;
        return items[temp];
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        endIndex = (endIndex - 1 + capacity) % capacity;
        size -= 1;
        return items[endIndex];
    }

    public T get(int index) {
        int realIndex = (index + startIndex) % capacity;
        if ((realIndex >= endIndex && realIndex < startIndex) || realIndex >= capacity) {
            return null;
        }
        return items[realIndex];
    }

    /** Hasn't figured out yet */
    /** return an iterator */
    public Iterator<T> iterator() {

        return new ArrayIterator();
    }

    public class ArrayIterator implements Iterator<T> {

        int position;

        public ArrayIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T returnItem = items[position];
            position += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof Deque)) {
            return false;
        }

        //if (o.getClass() != this.getClass()) {
        //    return false;
        //}

        //ArrayDeque<T> other = (ArrayDeque) o;
        Deque<T> other = (Deque) o;

        if (other.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < this.size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    /** for test use */
    /**
    public static void main(String[] arg) {
        ArrayDeque arr = new ArrayDeque();
        //arr.addFirst(1);
        //arr.addFirst(2);
        //arr.addFirst(3);
        arr.addLast(11);
        arr.addLast(12);
        arr.addLast(13);
        arr.addLast(14);
        //arr.addFirst(4);
        //arr.addFirst(5);
        arr.addLast(15);
        arr.addLast(16);
        arr.addLast(17);
        arr.addLast(18);
        //arr.printDeque();
        arr.addFirst(1);
        arr.addFirst(2);
        //arr.printDeque();
        arr.addLast(19);
        //arr.addFirst(1);
        //arr.printDeque();
        //System.out.println(arr.get(15));
        //System.out.println(arr.get(13));
        //System.out.println(arr.get(9));
        //System.out.println(arr.get(8));
        System.out.println(arr.size());
        arr.removeFirst();
        System.out.println(arr.size());
        arr.addFirst(3);
        arr.addFirst(4);
        System.out.println(arr.size());
        arr.printDeque();
        System.out.println(arr.removeLast());
        System.out.println(arr.size());
        arr.printDeque();
        System.out.println(arr.removeLast());
        System.out.println(arr.size());
        arr.printDeque();
        System.out.println(arr.removeFirst());
        System.out.println(arr.size());
        arr.printDeque();
    }
    */
}
