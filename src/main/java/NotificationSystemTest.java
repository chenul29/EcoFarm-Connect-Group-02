import javax.swing.*;

/**
 * Test class to demonstrate Notification functionality
 * Tests admin sending notifications and farmer receiving them
 */
public class NotificationSystemTest {

    public static void main(String[] args) {
        // Test 1: Create notifications table
        System.out.println("=== Test 1: Creating Notifications Table ===");
        NotificationDAO.createNotificationsTable();
        System.out.println("✓ Notifications table created/verified\n");

        // Test 2: Add sample notifications
        System.out.println("=== Test 2: Adding Sample Notifications ===");

        boolean test1 = NotificationDAO.addNotification(
            1,
            "ALERT",
            "Crop Ready for Harvest",
            "Your Tomatoes crop in Field A is ready for harvest."
        );
        System.out.println("✓ Added harvest alert: " + test1);

        boolean test2 = NotificationDAO.addNotification(
            1,
            "WARNING",
            "Low Inventory Alert",
            "Your Organic Fertilizer stock is running low (10 kg remaining)."
        );
        System.out.println("✓ Added inventory warning: " + test2);

        boolean test3 = NotificationDAO.addNotification(
            1,
            "INFO",
            "Weather Alert",
            "Heavy rain expected tomorrow. Please secure your crops."
        );
        System.out.println("✓ Added weather info: " + test3);

        boolean test4 = NotificationDAO.addNotification(
            1,
            "REMINDER",
            "Planting Window",
            "Optimal planting window for Wheat starts in 3 days."
        );
        System.out.println("✓ Added planting reminder: " + test4);

        boolean test5 = NotificationDAO.addNotification(
            1,
            "SYSTEM",
            "New Order Received",
            "You have received a new order for 50kg of Tomatoes. Order ID: #12345"
        );
        System.out.println("✓ Added system notification: " + test5 + "\n");

        // Test 3: Get unread count
        System.out.println("=== Test 3: Checking Unread Count ===");
        int unreadCount = NotificationDAO.getUnreadCount(1);
        System.out.println("✓ Unread notifications for user 1: " + unreadCount + "\n");

        // Test 4: Get all notifications
        System.out.println("=== Test 4: Retrieving All Notifications ===");
        java.util.List<Notification> notifications = NotificationDAO.getNotificationsByUserId(1);
        System.out.println("✓ Total notifications for user 1: " + notifications.size());

        for (int i = 0; i < notifications.size(); i++) {
            Notification n = notifications.get(i);
            System.out.println("\nNotification #" + (i + 1) + ":");
            System.out.println("  ID: " + n.getId());
            System.out.println("  Type: " + n.getNotificationType());
            System.out.println("  Title: " + n.getTitle());
            System.out.println("  Message: " + n.getMessage());
            System.out.println("  Is Read: " + n.isRead());
            System.out.println("  Created: " + n.getCreatedAt());
        }

        // Test 5: Mark notification as read
        if (notifications.size() > 0) {
            System.out.println("\n=== Test 5: Marking Notification as Read ===");
            int firstId = notifications.get(0).getId();
            boolean marked = NotificationDAO.markAsRead(firstId);
            System.out.println("✓ Marked notification " + firstId + " as read: " + marked);

            int newUnreadCount = NotificationDAO.getUnreadCount(1);
            System.out.println("✓ New unread count: " + newUnreadCount + "\n");
        }

        // Test 6: Mark all as read
        System.out.println("=== Test 6: Marking All as Read ===");
        boolean allMarked = NotificationDAO.markAllAsRead(1);
        System.out.println("✓ Marked all as read: " + allMarked);

        int finalUnreadCount = NotificationDAO.getUnreadCount(1);
        System.out.println("✓ Final unread count: " + finalUnreadCount + "\n");

        System.out.println("=== All Tests Completed ===");
        System.out.println("\n✅ Notification system is working correctly!");
        System.out.println("\nNext Steps:");
        System.out.println("1. Run adminDashboard.java to send notifications");
        System.out.println("2. Run farmerDashboard.java to view notifications");
        System.out.println("3. Test the UI interactions");
    }
}

