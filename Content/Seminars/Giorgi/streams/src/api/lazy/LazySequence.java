package api.lazy;

import api.Filter;
import api.Mapper;
import api.Reducer;
import api.Sequence;

import java.util.Iterator;

public abstract class LazySequence<T> extends Sequence<T> {
    @Override
    public <Q> Sequence<Q> map(Mapper<T, Q> mapper) {
        return new LazyMappedSequence<>(this, mapper);
    }

    @Override
    public Sequence<T> filter(Filter<T> filter) {
        return new LazyFilteredSequence<>(this, filter);
    }

    @Override
    public <O> O reduce(Reducer<T, O> reducer, O init) {
        return super.reduce(reducer, init);
    }
}
