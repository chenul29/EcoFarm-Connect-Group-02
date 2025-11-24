import java.sql.*;

public class UserDAO {

    // Login user - check username and password
    public static User authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                return user;
            }

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }

    // Add new user to database
    public static boolean registerUser(User user) {
        String query = "INSERT INTO users (username, password, user_type, full_name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getUserType());
            pstmt.setString(4, user.getFullName());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getPhone());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    // Setup database on first run
    public static void initializeDatabase() {
        createDatabase();
        createUsersTable();
        NotificationDAO.createNotificationsTable(); // Create notifications table
        addSampleUsers();
    }

    // Create database if not exists
    private static void createDatabase() {
        String url = "jdbc:mysql://localhost:3306/";
        String query = "CREATE DATABASE IF NOT EXISTS agriculture_db";

        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(query);
            System.out.println("Database ready!");

        } catch (Exception e) {
            System.out.println("Database creation error: " + e.getMessage());
        }
    }

    // Create users table if not exists
    private static void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) UNIQUE NOT NULL," +
                "password VARCHAR(100) NOT NULL," +
                "user_type ENUM('admin', 'farmer') NOT NULL," +
                "full_name VARCHAR(100)," +
                "email VARCHAR(100)," +
                "phone VARCHAR(20)," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(query);
            System.out.println("Users table ready!");

        } catch (Exception e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }

    // Add sample admin and farmer users
    private static void addSampleUsers() {
        // Admin user
        User admin = new User("admin", "admin123", "admin", "System Administrator",
                "admin@agriculture.com", "+1234567890");
        registerUser(admin);

        // Farmer user
        User farmer = new User("farmer", "agriculture123", "farmer", "John Farmer",
                "farmer@agriculture.com", "+1234567891");
        registerUser(farmer);

        System.out.println("Sample users created!");
        System.out.println("Login with: admin/admin123 or farmer/agriculture123");
    }
}


