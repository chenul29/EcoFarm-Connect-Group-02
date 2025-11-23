import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/agriculture_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Get database connection
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully!");
            return connection;
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}

