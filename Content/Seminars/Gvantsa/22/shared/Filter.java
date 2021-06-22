package shared;

import java.io.Serializable;

public interface Filter extends Serializable {
    public boolean filter(Student st);
    public String format();
}