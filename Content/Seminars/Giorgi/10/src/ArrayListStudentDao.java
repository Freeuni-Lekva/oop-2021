import java.util.ArrayList;
import java.util.List;

public class ArrayListStudentDao implements StudentDao {
    private List<Student> students;

    public ArrayListStudentDao() {
        this.students = new ArrayList<>();
    }

    @Override
    public void add(Student st) {
        students.add(st);
    }

    @Override
    public void remove(Student st) {
        for (int i = 0; i < students.size(); i++) {
            if (st == students.get(i)) {
                students.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Student> filter(Filter f) {
        List<Student> ret = new ArrayList<>();
        for (Student st : students) {
            if (f.filter(st)) {
                ret.add(st);
            }
        }
        return ret;
    }
}
