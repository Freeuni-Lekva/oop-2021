import java.util.*;

public class Course {

    public static final String EMPTY_COURSE_MESSAGE = "Course doesn't have any students";

    private StudentDao students;
    private String name;
    private int maxAmount;

    public Course() {

    }

    public Course(String name, int maxAmount, StudentDao students){
        this.name = name;
        this.maxAmount = maxAmount;
        this.students = students;
    }

    public int getStudentCount(){
        return students.getAll().size();
    }

    public Student getStudentWithHighestGpa(){
        List<Student> studentList = students.getAll();
        if (studentList.size() == 0)
            throw new IllegalStateException(EMPTY_COURSE_MESSAGE);

        Student result = studentList.get(0);
        for (Student student : studentList){
            if (student.getGpa() > result.getGpa())
                result = student;
        }

        return result;
    }

    public boolean addStudent(Student student){
        if (students.getAll().size() >= maxAmount)
            return false;
        students.add(student);
        return true;
    }

    public void setStudents(StudentDao students) {
        this.students = students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public StudentDao getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public int getMaxAmount() {
        return maxAmount;
    }
}