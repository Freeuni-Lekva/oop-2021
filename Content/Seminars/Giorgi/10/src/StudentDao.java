import java.util.List;

public interface StudentDao {
    public void add(Student st);
    public void remove(Student st);
    public List<Student> filter(Filter f);

    default public List<Student> getAll() {
        return filter(new AllFilter());
    }
}
