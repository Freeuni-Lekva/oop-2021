import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class ConnectionManager implements ConnectionPool {
    private static ConnectionManager mngr;
    private List<Connection> conns;

    public static ConnectionManager getInstance() {
        if (mngr == null) {
            mngr = new ConnectionManager(2);
        }
        return mngr;
    }

    private ConnectionManager(int num_connection) {
        conns = new ArrayList<>();
        try {
            for (int i = 0; i < num_connection; ++i) {
                conns.add(DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test_db",
                        "root",
                        "rootroot"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return conns.remove(conns.size() - 1);
    }

    @Override
    public void releaseConnection(Connection conn) {
        conns.add(conn);
    }
}
