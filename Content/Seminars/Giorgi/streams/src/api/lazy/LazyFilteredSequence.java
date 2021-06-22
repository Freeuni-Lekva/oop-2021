package api.lazy;

import api.Filter;
import api.Sequence;

import java.util.Iterator;

public class LazyFilteredSequence<T> extends LazySequence<T> {
    private Sequence<T> seq;
    private Filter<T> filter;

    private class Iterator implements java.util.Iterator<T> {
        private java.util.Iterator<T> iter;
        private T val;

        public Iterator() {
            this.iter = seq.iterator();
            val = null;
        }

        @Override
        public boolean hasNext() {
            while (true) {
                if (!iter.hasNext()) {
                    break;
                }
                val = iter.next();
                if (filter.filter(val)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            return val;
        }
    }

    public LazyFilteredSequence(Sequence<T> seq, Filter<T> filter) {
        this.seq = seq;
        this.filter = filter;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }
}
