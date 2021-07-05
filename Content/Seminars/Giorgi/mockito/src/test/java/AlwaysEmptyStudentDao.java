import java.util.List;

public class AlwaysEmptyStudentDao implements StudentDao {
    @Override
    public void add(Student st) {

    }

    @Override
    public void remove(Student st) {

    }

    @Override
    public List<Student> getAll() {
        return null;
    }
}
