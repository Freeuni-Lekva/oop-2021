package shared;

import shared.filter.descriptor.*;
import shared.filter.sql.ParametrizedFilter;
import shared.filter.sql.FilterBuilder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStudentDao implements StudentDao {
    private final DataSource ds;
    private final FilterBuilder<Student> filterBuilder;

    public SqlStudentDao(DataSource ds) {
        this.ds = ds;
        filterBuilder = new FilterBuilder<>(Student.class);
    }

    @Override
    public Integer add(Student st) {
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
            return st.getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Student.NO_ID;
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
    public Student get(int id) {
        ExpressionDescription ed = new IntegerCompareOperationDescription(
                CompareOperator.EQUALS,
                new FieldValueDescription<>("id"),
                new IntegerValueDescription(id));
        List<Student> students = filter(ed);
        if (students.size() == 0) {
            return null;
        }
        return students.get(0);
    }

    @Override
    public Void remove(Student st) {
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
        return null;
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
    public List<Student> filter(ExpressionDescription ed) {
        Connection conn = null;
        List<Student> ret = new ArrayList<>();
        try {
            conn = ds.getConnection();
            ParametrizedFilter f = filterBuilder.build(ed);
            PreparedStatement stm = conn.prepareStatement(
                    "SELECT id, first_name, last_name, enrollment_year FROM students " +
                            "WHERE " + f.toString());
            int cnt = 1;
            for (Object o : f.getParams()) {
                stm.setObject(cnt, o);
                cnt++;
            }
            ResultSet rs = stm.executeQuery();
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