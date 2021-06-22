package api;

public abstract class Sequence<T> implements Iterable<T> {
    public <Q> Sequence<Q> map(Mapper<T, Q> mapper) {
        LinkedList<Q> ret = new LinkedList<>();
        for (T elem : this) {
            ret.add(mapper.map(elem));
        }
        return ret;
    }

    public Sequence<T> filter(Filter<T> filter) {
        LinkedList<T> ret = new LinkedList<>();
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
        LinkedList<T> ret = new LinkedList<>();
        for (Sequence<T> seq : sequences) {
            for (T elem : seq) {
                ret.add(elem);
            }
        }
        return ret;
    }

    public static <T> Sequence<T> flatten(Sequence<T>... sequences) {
        return flatten(new ArraySequence<>(sequences));
    }
}
