package ge.edu.freeuni;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStudentDao implements StudentDao {
    private DataSource ds;

    public SqlStudentDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void add(Student st) {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = ds.getConnection();
            PreparedStatement stm = conn.prepareStatement(
                    "INSERT INTO students (first_name, last_name, enrollment_year) " +
                            "VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, st.getFirstName());
            stm.setString(2, st.getLastName());
            stm.setInt(3, st.getEnrollmentYear());
            int num_inserted = stm.executeUpdate();
            if (num_inserted != 1) {
                // return error
            }
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            st.setId(rs.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public void remove(Student st) {
        if (st.getId() != Student.NO_ID) {
            // return error
        }
        Connection conn = null;
        try {
            conn = ds.getConnection();
            PreparedStatement stm = conn.prepareStatement(
                    "DELETE FROM students WHERE id = ?");
            stm.setInt(1, st.getId());
            int num_deleted = stm.executeUpdate();
            if (num_deleted != 1) {
                // return error
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<Student> getAll() {
        Connection conn = null;
        List<Student> ret = new ArrayList<>();
        try {
            conn = ds.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(
                    "SELECT id, first_name, last_name, enrollment_year FROM students");
            while (rs.next()) {
                ret.add(new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return ret;
    }

    @Override
    public List<Student> filter(Filter f) {
        Connection conn = null;
        List<Student> ret = new ArrayList<>();
        try {
            conn = ds.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(
                    "SELECT id, first_name, last_name, enrollment_year FROM students " +
                            "WHERE " + f.format());
            while (rs.next()) {
                ret.add(new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return ret;
    }
}