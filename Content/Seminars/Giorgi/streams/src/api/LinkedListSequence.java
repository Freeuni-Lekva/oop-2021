package api;

public class LinkedListSequence<T> extends Sequence<T> {
    private Node head;
    private Node tail;

    private class Node {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }

    private class Iterator implements java.util.Iterator<T> {
        private Node current;

        public Iterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    public LinkedListSequence() {
        head = null;
        tail = null;
    }

    public void add(T elem) {
        Node n = new Node(elem);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }
}
