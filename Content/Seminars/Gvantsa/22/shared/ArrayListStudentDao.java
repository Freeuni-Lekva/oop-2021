package shared;

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
        if (st.getId() == Student.NO_ID) {
            st.setId(students.size());
        }
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

    public synchronized Student get(int id) {
        for (int i = 0; i < students.size(); i++) {
            Student st = students.get(i);
            System.out.println(st);
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    @Override
    public synchronized List<Student> filter(Filter f) {
        List<Student> ret = new ArrayList<>();
        for (Student st : students) {
            if (f.filter(st)) {
                ret.add(st);
            }
        }
        return ret;
    }

    @Override
    public synchronized List<Student> getAll() {
        return Collections.unmodifiableList(students);
    }
}