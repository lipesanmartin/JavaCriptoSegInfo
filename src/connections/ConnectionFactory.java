package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/seg_info", "root", "2205");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
