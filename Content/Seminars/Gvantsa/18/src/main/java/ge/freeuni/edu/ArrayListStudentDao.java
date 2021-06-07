package ge.freeuni.edu;

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

    @Override
    public List<Student> getAll() {
        return Collections.unmodifiableList(students);
    }
}
