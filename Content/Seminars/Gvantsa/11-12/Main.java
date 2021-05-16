import bean.Course;
import bean.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.StudentDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class Main {

    public static void main(String[] args){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");

        StudentDAO studentDAO = new StudentDAO(dataSource);

        List<Course> courses = studentDAO.getStudentCourses(1);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (Course course: courses){
            System.out.println(gson.toJson(course));
        }

    }
}
