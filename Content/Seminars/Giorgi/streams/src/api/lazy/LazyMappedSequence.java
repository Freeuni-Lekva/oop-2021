package api.lazy;

import api.Mapper;
import api.Sequence;

public class LazyMappedSequence<T, Q> extends LazySequence<Q> {
    private Sequence<T> seq;
    private Mapper<T, Q> mapper;

    private class Iterator implements java.util.Iterator<Q> {
        private java.util.Iterator<T> iter;

        public Iterator() {
            iter = seq.iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public Q next() {
            return mapper.map(iter.next());
        }
    }

    public LazyMappedSequence(Sequence<T> seq, Mapper<T, Q> mapper) {
        this.seq = seq;
        this.mapper = mapper;
    }

    @Override
    public java.util.Iterator<Q> iterator() {
        return new Iterator();
    }
}
