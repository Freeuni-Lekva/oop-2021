import java.util.List;

public interface StudentDao {
    public void add(Student st);
    public void remove(Student st);

    public List<Student> getAll();
}