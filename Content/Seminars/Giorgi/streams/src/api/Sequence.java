package api;

import api.lazy.LazyFlattenedSequence;

public abstract class Sequence<T> implements Iterable<T> {
    public <Q> Sequence<Q> map(Mapper<T, Q> mapper) {
        LinkedListSequence<Q> ret = new LinkedListSequence<>();
        for (T elem : this) {
            ret.add(mapper.map(elem));
        }
        return ret;
    }

    public Sequence<T> filter(Filter<T> filter) {
        LinkedListSequence<T> ret = new LinkedListSequence<>();
        for (T elem : this) {
            if (filter.filter(elem)) {
                ret.add(elem);
            }
        }
        return ret;
    }

    public <O> O reduce(Reducer<T, O> reducer, O init) {
        O ret = init;
        for (T elem : this) {
            ret = reducer.reduce(elem, ret);
        }
        return ret;
    }

    public static <T> Sequence<T> flatten(Sequence<Sequence<T>> sequences) {
        return sequences.reduce((curSeq, res) -> {
            curSeq.reduce((elem, res_) -> {
                res_.add(elem);
                return res_;
            }, res);
            return res;
        }, new LinkedListSequence<>());
    }

    public static <T> Sequence<T> flatten(Sequence<T>... sequences) {
        return flatten(new ArraySequence<>(sequences));
    }

    public static <T> Sequence<T> lazyFlatten(Sequence<Sequence<T>> sequences) {
        return new LazyFlattenedSequence<>(sequences);
    }

    public static <T> Sequence<T> lazyFlatten(Sequence<T>... sequences) {
        return lazyFlatten(new ArraySequence<>(sequences));
    }
}
