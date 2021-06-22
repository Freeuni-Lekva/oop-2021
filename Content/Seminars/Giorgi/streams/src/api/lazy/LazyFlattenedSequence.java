package api.lazy;

import api.Sequence;

import java.util.Iterator;

public class LazyFlattenedSequence<T> extends LazySequence<T> {
    private Sequence<Sequence<T>> sequences;

    public class Iterator implements java.util.Iterator<T> {
        private java.util.Iterator<Sequence<T>> seqIter;
        private java.util.Iterator<T> seq;

        public Iterator() {
            seqIter = sequences.iterator();
            seq = null;
        }

        @Override
        public boolean hasNext() {
            while (true) {
                if (seq != null) {
                    if (seq.hasNext()) {
                        return true;
                    }
                } else if (seqIter.hasNext()) {
                    seq = seqIter.next().iterator();
                } else {
                    break;
                }
            }
            return false;
        }

        @Override
        public T next() {
            return seq.next();
        }
    }

    public LazyFlattenedSequence(Sequence<Sequence<T>> sequences) {
        this.sequences = sequences;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }
}
