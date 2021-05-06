import java.util.ArrayList;
import java.util.List;

public class StudentStore {
    private List<Student> students;

    public StudentStore() {
        this.students = new ArrayList<>();
    }

    public void add(Student st) {
        students.add(st);
    }

    public void remove(int index) {
        students.remove(index);
    }

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
