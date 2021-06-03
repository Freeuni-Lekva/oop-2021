import java.sql.Connection;

public interface ConnectionPool {
    public Connection getConnection();
    public void releaseConnection(Connection conn);
}
