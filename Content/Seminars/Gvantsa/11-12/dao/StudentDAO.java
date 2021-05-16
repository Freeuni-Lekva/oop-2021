package dao;

import bean.Course;
import bean.Student;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final BasicDataSource dataSource;

    public StudentDAO(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from students;");
            while(result.next()){
                students.add(convertToStudent(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return students;
    }

    public Student getStudentById(int studentId){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from students where student_id = ?;");
            statement.setInt(1, studentId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                return convertToStudent(result);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public List<Course> getStudentCourses(int studentId){
        List<Course> courses = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select c.* from courses c left join student_courses sc on c.id = sc.course_id where sc.student_id = ?;");
            statement.setInt(1, studentId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Course course = new Course();
                course.setCourseId(result.getInt("course_id"));
                course.setCourseName(result.getString("course_name"));
                course.setCourseCredit(result.getInt("course_credit"));
                course.setCourseType(result.getString("course_type"));
                courses.add(course);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return courses;
    }

    private Student convertToStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getInt("student_id"));
        student.setIdNumber(resultSet.getString("idnumber"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        student.setRegisterDate(resultSet.getDate("register_date"));
        return student;
    }

}
