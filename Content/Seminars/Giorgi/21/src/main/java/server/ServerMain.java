package server;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import shared.ArrayListStudentDao;
import shared.SqlStudentDao;
import shared.StudentDao;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        StudentDao students = createSqlDao();
        RPCServer<StudentDao> server = new RPCServer<StudentDao>(8081, students, StudentDao.class);
        server.start();
    }

    public static StudentDao createSqlDao() {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("test_db");
        ds.setUser("root");
        ds.setPassword("rootroot");
        return new SqlStudentDao(ds);
    }

    public static StudentDao createInMemoryDao() {
        return new ArrayListStudentDao();
    }
}
