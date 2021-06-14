package ge.edu.freeuni;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class StudentsContextListener implements ServletContextListener {
    private MysqlConnectionPoolDataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is being created");
        ds = new MysqlConnectionPoolDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("test_db");
        ds.setUser("root");
        ds.setPassword("rootroot");
        StudentDao students = new SqlStudentDao(ds);
        sce.getServletContext().setAttribute("store", students);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context is being destroyed");
    }
}
