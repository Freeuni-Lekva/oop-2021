package ge.edu.freeuni;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import ge.edu.freeuni.api.StudentsWebSocket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import javax.websocket.Decoder;
import javax.websocket.DeploymentException;
import javax.websocket.Encoder;
import javax.websocket.Extension;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

public class StudentsContextListener implements ServletContextListener {
    private MysqlConnectionPoolDataSource ds;
    private StudentDao students;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is being created");
        createDBPool(sce.getServletContext());
        initWebSocket(sce.getServletContext());
    }

    private void initWebSocket(ServletContext sc) {
        ServerContainer cont = (ServerContainer) sc.getAttribute("javax.websocket.server.ServerContainer");
        try {
            ServerEndpointConfig cfg = ServerEndpointConfig.Builder
                    .create(StudentsWebSocket.class, "/api/students_ws")
                    .build();
            cfg.getUserProperties().put("store", students);
            cont.addEndpoint(cfg);
        } catch (DeploymentException e) {
            e.printStackTrace();
        }
    }

    private void createDBPool(ServletContext sc) {
        ds = new MysqlConnectionPoolDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("test_db");
        ds.setUser("root");
        ds.setPassword("rootroot");
        students = new SqlStudentDao(ds);
        sc.setAttribute("store", students);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context is being destroyed");
    }
}
