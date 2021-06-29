package shared;

import shared.filter.descriptor.ExpressionDescription;
import shared.filter.reflection.ExpressionBuilder;
import shared.filter.reflection.Filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListStudentDao implements StudentDao {
    private List<Student> students;

    public ArrayListStudentDao() {
        this.students = new ArrayList<>();
    }

    @Override
    public synchronized Integer add(Student st) {
        st.setId(students.size());
        students.add(st);
        return students.size() - 1;
    }

    @Override
    public synchronized Void remove(Student st) {
        for (int i = 0; i < students.size(); i++) {
            if (st == students.get(i)) {
                students.remove(i);
                break;
            }
        }
        return null;
    }

    @Override
    public synchronized Student get(int id) {
        return students.get(id);
    }

    @Override
    public synchronized List<Student> filter(ExpressionDescription ed) {
        Filter f = (Filter) ExpressionBuilder.build(ed);
        List<Student> ret = new ArrayList<>();
        for (Student st : students) {
            if (f.evaluate(st)) {
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