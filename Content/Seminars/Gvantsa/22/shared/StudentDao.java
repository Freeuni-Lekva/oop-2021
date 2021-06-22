package shared;

import java.util.List;

public interface StudentDao {
    public void add(Student st);
    public void remove(Student st);
    public List<Student> filter(Filter f);

    public List<Student> getAll();
}