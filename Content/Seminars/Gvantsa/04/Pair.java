import java.util.Objects;

public class Pair<T1,T2> {
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }

    public void setFirst(T1 first){
        this.first = first;
    }

    public void setSecond(T2 second){
        this.second = second;
    }

    public T1 getFirst(){
        return first;
    }

    public T2 getSecond(){
        return second;
    }

    @Override
    public boolean equals(Object pair){
        if (!(pair instanceof Pair))
            return false;
        Pair<?,?> p = (Pair<?, ?>) pair;
        return Objects.equals(p.first, this.first) && Objects.equals(p.second, this.second) ;
    }
}
