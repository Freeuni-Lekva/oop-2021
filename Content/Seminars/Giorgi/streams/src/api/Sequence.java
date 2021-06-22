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

    public T reduce(Reducer<T> reducer, T init) {
        T ret = init;
        for (T elem : this) {
            ret = reducer.reduce(elem, ret);
        }
        return ret;
    }

    public static <T> Sequence<T> flatten(Sequence<Sequence<T>> sequences) {
        LinkedListSequence<T> ret = new LinkedListSequence<>();
        for (Sequence<T> seq : sequences) {
            for (T elem : seq) {
                ret.add(elem);
            }
        }
        return ret;
//        LinkedListSequence<T> ret = sequences.reduce(new Reducer<Sequence<T>, LinkedListSequence<T>>() {
//                                                         @Override
//                                                         public LinkedListSequence<T> reduce(Sequence<T> seq, LinkedListSequence<T> init) {
//                                                             seq.reduce(new Reducer<T, LinkedListSequence<T>>() {
//                                                                 @Override
//                                                                 public LinkedListSequence<T> reduce(T elem, LinkedListSequence<T> init) {
//                                                                     init.add(elem);
//                                                                     return init;
//                                                                 }
//                                                             });
//                                                             return null;
//                                                         }
//                                                     }, new LinkedListSequence<T>());
//        LinkedListSequence<T> ret = sequences.reduce((seq, a) -> {
//            seq.reduce((x, b) -> {
//                b.add(x);
//                return b;
//            }, a);
//            return a;
//        }, new LinkedListSequence<T>());
        // return ret;
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
