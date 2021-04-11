import java.util.Collection;

public class MyStack<T> {

    private Object[] elems;
    private int logLen;

    public MyStack(){
        elems = new Object[4];
        logLen = 0;
    }


    public void add(T elem){
        if (elems.length == logLen){
            Object[] newElems = new Object[2 * logLen];
            for (int i = 0; i < logLen; i++)
                newElems[i] = elems[i];
            elems = newElems;
        }
        elems[logLen] = elem;
        logLen++;
    }

    public T pop(){
        if (this.size() == 0)
            throw new IndexOutOfBoundsException("Stack is empty!");
        logLen--;
        T tmp = (T) elems[logLen];
        elems[logLen] = null;
        return tmp;
    }

    public void addAll(Collection<T> elems){
        for (T elem: elems){
            add(elem);
        }
    }

    public int size(){
        return logLen;
    }

}