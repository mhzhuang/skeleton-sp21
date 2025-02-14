package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private class Node {
        private Node prev;
        private T item;
        private Node next;

        Node(Node p, T i, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private Node sentinelFront;
    private Node sentinelEnd;

    //public Node first;
    //public int item;
    //public Node next;

    public LinkedListDeque() {
        sentinelFront = new Node(null, null, null);
        sentinelEnd = new Node(sentinelFront, null, null);
        sentinelFront.next = sentinelEnd;
        size = 0;
    }

    /** https://www.youtube.com/watch?v=JNroRiEG7U4 (Project 1A Copy Constructor Walkthrough) */
    /**
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinelFront = new Node(null, null, null);
        sentinelEnd = new Node(sentinelFront, null, null);
        sentinelFront.next = sentinelEnd;
        size = 0;

        for (int i = 0; i < other.size(); i += 1) {
            addLast(other.get(i));
        }
    }
     */

    /**
    public LinkedListDeque(T x) {
        sentinelFront = new Node(null, null, null);
        sentinelEnd = new Node(null, null, null);
        sentinelFront.next = new Node(sentinelFront, x, sentinelEnd);
        sentinelEnd.prev = sentinelFront.next;
        size = 1;
    }
     */

    public void addFirst(T item) {
        sentinelFront.next = new Node(sentinelFront, item, sentinelFront.next);
        sentinelFront.next.next.prev = sentinelFront.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinelEnd.prev = new Node(sentinelEnd.prev, item, sentinelEnd);
        sentinelEnd.prev.prev.next = sentinelEnd.prev;
        size += 1;
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
        Node temp = sentinelFront.next;
        int tempSize = size;
        while (tempSize > 0) {
            System.out.print(temp.item + " ");
            temp = temp.next;
            tempSize -= 1;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = sentinelFront.next.item;
        sentinelFront.next = sentinelFront.next.next;
        sentinelFront.next.prev = sentinelFront;
        size -= 1;
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = sentinelEnd.prev.item;
        sentinelEnd.prev = sentinelEnd.prev.prev;
        sentinelEnd.prev.next = sentinelEnd;
        size -= 1;
        return x;
    }

    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node temp = sentinelFront;
        while (index >= 0) {
            temp = temp.next;
            index -= 1;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        Node temp = sentinelFront.next;
        return getRecursiveHelper(index, temp);
    }

    private T getRecursiveHelper(int index, Node temp) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        if (index == 0) {
            return temp.item;
        }
        return getRecursiveHelper(index - 1, temp.next);
    }

    /** Hasn't figured out yet */
    /** return an iterator */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private int position;

        LinkedListDequeIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T returnItem = get(position);
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
        //LinkedListDeque A = new LinkedListDeque();
        //System.out.println(A.isEmpty());
        LinkedListDeque<Integer> L = new LinkedListDeque<>(4);
        System.out.println(L.isEmpty());
        L.addFirst(14);
        L.addLast(24);  // front -> 14 -> 4 -> 24 -> end
        //A.addFirst(100);
        //A.addLast(200);
        //System.out.println(A.isEmpty());
        //System.out.println(A.size());
        System.out.println(L.getRecursive(0));
        System.out.println(L.getRecursive(1));
        System.out.println(L.getRecursive(2));
        L.printDeque();
        //L.removeFirst();
        //L.removeLast();
    }
    */
}
