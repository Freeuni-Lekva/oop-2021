package ge.edu.freeuni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListStudentDao implements StudentDao {
    private List<Student> students;

    public ArrayListStudentDao() {
        this.students = new ArrayList<>();
    }

    @Override
    public synchronized void add(Student st) {
        st.setId(students.size());
        students.add(st);
    }

    @Override
    public synchronized void remove(Student st) {
        for (int i = 0; i < students.size(); i++) {
            if (st == students.get(i)) {
                students.remove(i);
                return;
            }
        }
    }

//    @Override
//    public List<Student> filter(Filter f) {
//        List<Student> ret = new ArrayList<>();
//        for (Student st : students) {
//            if (f.filter(st)) {
//                ret.add(st);
//            }
//        }
//        return ret;
//    }

    @Override
    public synchronized List<Student> getAll() {
        return Collections.unmodifiableList(students);
    }
}
