package Swing;

import java.util.ArrayList;
import java.util.List;

public class StudentStore {
    private List<Student> students;

    public StudentStore(){
        students = new ArrayList<>();
    }

    public void addStudent(Student student){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < students.size(); i++){
//            Student currStudent = students.get(i);
//            if (currStudent.getFirstName() == student.getFirstName()
//                    && currStudent.getLastName() == student.getLastName()
//                    && currStudent.getYear() == student.getYear()){
//                students.set(i, new Student(student.getFirstName(), student.getLastName(), student.getYear(),"Done"));
//            }
//        }
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
