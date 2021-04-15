import java.util.Arrays;

public class Stack<T> {
    private Object[] elems;
    private int logLen;

    public Stack() {
        elems = new Object[4];
        logLen = 0;
    }

    public void push(T elem) {
        if (logLen == elems.length) {
            elems = Arrays.copyOf(elems, logLen * 2);
        }
        elems[logLen] = elem;
        logLen++;
    }

    public T pop() {
        if (logLen == 0) {
            throw new PopOnEmptyStackException();
        }
        logLen--;
        return (T) elems[logLen];
    }
}
