import java.util.ArrayList;
import java.util.List;

public class StudentStore {
    private List<Student> students;

    public StudentStore(){
        students = new ArrayList<>();
    }

    public void addStudent(Student student){
        students.add(student);
    }


    public List<Student> filter(Filter filter){
        List<Student> result = new ArrayList<>();
        for(Student student: students){
            if (filter.filter(student))
                result.add(student);
        }
        return result;
    }
}
