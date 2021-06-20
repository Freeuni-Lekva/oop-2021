package ge.freeuni.edu;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBStudentDao implements StudentDao{
    private final BasicDataSource dataSource;

    public DBStudentDao(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Student st) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("insert into students (student_id, idnumber, firstName, lastName) \n" +
                    "values \n" +
                    "(?, ?, ?, ?);");
            int id = new Random().nextInt(100);
            if (id < 10)
                id += 10;
            statement.setInt(1, id);
            statement.setString(2, "111111131" + id);
            statement.setString(3, st.getFirstName());
            statement.setString(4, st.getLastName());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(Student st) {

    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<Student>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from students;");
            while(result.next()) {
                Student student = new Student(result.getString("firstname"), result.getString("lastname"), 10);
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }
}
