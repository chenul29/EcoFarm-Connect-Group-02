import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    // Create notifications table if it doesn't exist
    public static void createNotificationsTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS notifications (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "user_id INT NOT NULL, " +
                "notification_type VARCHAR(50) NOT NULL, " +
                "title VARCHAR(255) NOT NULL, " +
                "message TEXT NOT NULL, " +
                "is_read BOOLEAN DEFAULT FALSE, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
                ")";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Notifications table created or already exists.");
        } catch (Exception e) {
            System.out.println("Error creating notifications table: " + e.getMessage());
        }
    }

    // Get all notifications for a user (newest first)
    public static List<Notification> getNotificationsByUserId(int userId) {
        List<Notification> notifications = new ArrayList<>();
        String query = "SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("notification_type"),
                        rs.getString("title"),
                        rs.getString("message"),
                        rs.getBoolean("is_read"),
                        rs.getTimestamp("created_at")
                );
                notifications.add(notification);
            }
        } catch (Exception e) {
            System.out.println("Error getting notifications: " + e.getMessage());
        }

        return notifications;
    }

    // Get unread notifications count
    public static int getUnreadCount(int userId) {
        String query = "SELECT COUNT(*) FROM notifications WHERE user_id = ? AND is_read = FALSE";
        int count = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error getting unread count: " + e.getMessage());
        }

        return count;
    }

    // Add a new notification
    public static boolean addNotification(int userId, String type, String title, String message) {
        String query = "INSERT INTO notifications (user_id, notification_type, title, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, type);
            pstmt.setString(3, title);
            pstmt.setString(4, message);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error adding notification: " + e.getMessage());
            return false;
        }
    }

    // Mark notification as read
    public static boolean markAsRead(int notificationId) {
        String query = "UPDATE notifications SET is_read = TRUE WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, notificationId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error marking notification as read: " + e.getMessage());
            return false;
        }
    }

    // Mark all notifications as read for a user
    public static boolean markAllAsRead(int userId) {
        String query = "UPDATE notifications SET is_read = TRUE WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error marking all notifications as read: " + e.getMessage());
            return false;
        }
    }

    // Delete a notification
    public static boolean deleteNotification(int notificationId) {
        String query = "DELETE FROM notifications WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, notificationId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error deleting notification: " + e.getMessage());
            return false;
        }
    }

    // Automatic notification triggers

    // Trigger: Crop ready to harvest
    public static void checkAndNotifyHarvestReady(int userId, String cropName, String fieldName) {
        String title = "🌾 Harvest Ready";
        String message = cropName + " in " + fieldName + " is ready for harvest!";
        addNotification(userId, "HARVEST_READY", title, message);
    }

    // Trigger: Low inventory alert
    public static void checkAndNotifyLowInventory(int userId, String itemName, int currentStock) {
        String title = "⚠️ Low Inventory Alert";
        String message = itemName + " stock is running low (" + currentStock + " units remaining). Please restock soon.";
        addNotification(userId, "LOW_INVENTORY", title, message);
    }

    // Trigger: New order received
    public static void notifyNewOrder(int userId, String orderDetails) {
        String title = "📦 New Order Received";
        String message = "New order: " + orderDetails;
        addNotification(userId, "ORDER_RECEIVED", title, message);
    }

    // Trigger: Weather alert
    public static void notifyWeatherAlert(int userId, String weatherMessage) {
        String title = "🌦️ Weather Alert";
        String message = weatherMessage;
        addNotification(userId, "WEATHER_ALERT", title, message);
    }

    // Trigger: Planting reminder
    public static void notifyPlantingReminder(int userId, String cropName, String dueDate) {
        String title = "🌱 Planting Reminder";
        String message = "It's time to plant " + cropName + " (Due: " + dueDate + ")";
        addNotification(userId, "PLANTING_REMINDER", title, message);
    }
}

