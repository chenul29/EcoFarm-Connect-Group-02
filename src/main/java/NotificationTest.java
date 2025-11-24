/**
 * Test class to demonstrate automatic notification triggers
 * This shows how to trigger notifications automatically based on system events
 */
public class NotificationTest {

    public static void main(String[] args) {
        System.out.println("=== Notification System Test ===\n");

        // Ensure notifications table exists
        NotificationDAO.createNotificationsTable();

        int userId = 1; // Test with user ID 1 (farmer)

        // Test 1: Harvest Ready Notification
        System.out.println("1. Triggering Harvest Ready notification...");
        NotificationDAO.checkAndNotifyHarvestReady(userId, "Tomatoes", "Field B");

        // Test 2: Low Inventory Alert
        System.out.println("2. Triggering Low Inventory alert...");
        NotificationDAO.checkAndNotifyLowInventory(userId, "Organic Fertilizer", 5);

        // Test 3: New Order Received
        System.out.println("3. Triggering New Order notification...");
        NotificationDAO.notifyNewOrder(userId, "50kg organic tomatoes from Green Market");

        // Test 4: Weather Alert
        System.out.println("4. Triggering Weather alert...");
        NotificationDAO.notifyWeatherAlert(userId, "Heavy rain expected tomorrow - postpone irrigation");

        // Test 5: Planting Reminder
        System.out.println("5. Triggering Planting Reminder...");
        NotificationDAO.notifyPlantingReminder(userId, "Winter Wheat", "Nov 25, 2024");

        // Display all notifications
        System.out.println("\n=== All Notifications for User " + userId + " ===");
        java.util.List<Notification> notifications = NotificationDAO.getNotificationsByUserId(userId);

        if (notifications.isEmpty()) {
            System.out.println("No notifications found.");
        } else {
            for (Notification notification : notifications) {
                System.out.println("\n" + notification.getTitle());
                System.out.println("Type: " + notification.getNotificationType());
                System.out.println("Message: " + notification.getMessage());
                System.out.println("Status: " + (notification.isRead() ? "Read" : "Unread"));
                System.out.println("Created: " + notification.getCreatedAt());
                System.out.println("---");
            }
        }

        // Get unread count
        int unreadCount = NotificationDAO.getUnreadCount(userId);
        System.out.println("\n=== Summary ===");
        System.out.println("Total Notifications: " + notifications.size());
        System.out.println("Unread Notifications: " + unreadCount);
    }

    /**
     * Example: Automatic trigger when crop growth stage changes
     * Call this method when a crop reaches mature stage
     */
    public static void onCropMature(int userId, String cropName, String fieldName) {
        // Check if crop is ready for harvest
        NotificationDAO.checkAndNotifyHarvestReady(userId, cropName, fieldName);
    }

    /**
     * Example: Automatic trigger when inventory falls below threshold
     * Call this method after any inventory transaction
     */
    public static void onInventoryUpdate(int userId, String itemName, int currentStock, int threshold) {
        if (currentStock < threshold) {
            NotificationDAO.checkAndNotifyLowInventory(userId, itemName, currentStock);
        }
    }

    /**
     * Example: Automatic trigger when new order is placed
     * Call this method when a new order is created
     */
    public static void onNewOrder(int userId, String customerName, String orderDetails) {
        String message = "Order from " + customerName + ": " + orderDetails;
        NotificationDAO.notifyNewOrder(userId, message);
    }

    /**
     * Example: Scheduled weather alerts
     * This could be called by a scheduled task that checks weather API
     */
    public static void sendWeatherAlert(int userId, String weatherCondition) {
        NotificationDAO.notifyWeatherAlert(userId, weatherCondition);
    }
}

