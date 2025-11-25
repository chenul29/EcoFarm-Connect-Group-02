import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmerNotificationDAO {

    // Create notifications table
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS farmer_notifications (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "farmer_id INT NOT NULL, " +
                     "title VARCHAR(200) NOT NULL, " +
                     "message TEXT NOT NULL, " +
                     "notification_type VARCHAR(50) DEFAULT 'INFO', " +
                     "is_read BOOLEAN DEFAULT FALSE, " +
                     "sent_by VARCHAR(100) DEFAULT 'Admin', " +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Notifications table created.");
        } catch (Exception e) {
            System.out.println("Error creating notifications table: " + e.getMessage());
        }
    }

    // Get all notifications for a farmer
    public static List<Object[]> getAllNotifications(int farmerId) {
        List<Object[]> notifications = new ArrayList<>();
        String sql = "SELECT id, title, message, notification_type, sent_by, created_at, is_read " +
                     "FROM farmer_notifications WHERE farmer_id = ? ORDER BY created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, farmerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] notification = {
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("message"),
                    rs.getString("notification_type"),
                    rs.getString("sent_by"),
                    rs.getTimestamp("created_at"),
                    rs.getBoolean("is_read") ? "Read" : "Unread"
                };
                notifications.add(notification);
            }

        } catch (Exception e) {
            System.out.println("Error getting notifications: " + e.getMessage());
        }

        return notifications;
    }

    // Add notification (called from admin dashboard)
    public static boolean addNotification(int farmerId, String title, String message, String type, String sentBy) {
        String sql = "INSERT INTO farmer_notifications (farmer_id, title, message, notification_type, sent_by) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, farmerId);
            stmt.setString(2, title);
            stmt.setString(3, message);
            stmt.setString(4, type);
            stmt.setString(5, sentBy);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error adding notification: " + e.getMessage());
            return false;
        }
    }

    // Broadcast notification to all farmers
    public static boolean broadcastToAllFarmers(String title, String message, String type, String sentBy) {
        String sql = "INSERT INTO farmer_notifications (farmer_id, title, message, notification_type, sent_by) " +
                     "SELECT id, ?, ?, ?, ? FROM users WHERE user_type = 'farmer'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, message);
            stmt.setString(3, type);
            stmt.setString(4, sentBy);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error broadcasting notification: " + e.getMessage());
            return false;
        }
    }

    // Mark notification as read
    public static boolean markAsRead(int notificationId) {
        String sql = "UPDATE farmer_notifications SET is_read = TRUE WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, notificationId);
            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error marking notification as read: " + e.getMessage());
            return false;
        }
    }
}

