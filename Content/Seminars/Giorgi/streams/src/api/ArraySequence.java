package api;

public class ArraySequence<T> extends Sequence<T> {
    private T[] arr;

    private class Iterator implements java.util.Iterator<T> {
        private int current;

        public Iterator() {
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < arr.length;
        }

        @Override
        public T next() {
            T ret = arr[current];
            current++;
            return ret;
        }
    }

    public ArraySequence(T[] arr) {
        this.arr = arr;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }
}
