package api.lazy;

import api.Sequence;

import java.util.Iterator;

public class LazyWrapperSequence<T> extends LazySequence<T> {
    private Sequence<T> seq;

    public LazyWrapperSequence(Sequence<T> seq) {
        this.seq = seq;
    }

    @Override
    public Iterator<T> iterator() {
        return seq.iterator();
    }
}
