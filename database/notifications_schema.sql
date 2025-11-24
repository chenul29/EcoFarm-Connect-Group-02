-- SQL Schema for Notifications Functionality
-- Run this in phpMyAdmin to create the notifications table

-- Create notifications table
CREATE TABLE IF NOT EXISTS notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create index for faster queries
CREATE INDEX idx_user_id ON notifications(user_id);
CREATE INDEX idx_created_at ON notifications(created_at);
CREATE INDEX idx_is_read ON notifications(is_read);

-- Insert sample notifications for testing (user_id = 1 for farmer)
INSERT INTO notifications (user_id, notification_type, title, message, is_read) VALUES
(1, 'PLANTING_REMINDER', '🌱 Planting Reminder', 'It\'s time to plant winter wheat in Field A (Due: Nov 25, 2024)', FALSE),
(1, 'HARVEST_READY', '🌾 Harvest Ready', 'Tomatoes in Field B are ready for harvest', FALSE),
(1, 'WEATHER_ALERT', '🌦️ Weather Alert', 'Heavy rain expected tomorrow - postpone irrigation', FALSE),
(1, 'LOW_INVENTORY', '⚠️ Low Inventory Alert', 'Tomato Seeds stock is running low (15 packets remaining)', FALSE),
(1, 'RESOURCE_SHARING', '🤝 Resource Available', 'John\'s tractor is available for rent this weekend', TRUE),
(1, 'ORDER_RECEIVED', '📦 New Order Received', 'New order for 50kg organic tomatoes from Green Market', FALSE),
(1, 'WEATHER_ALERT', '❄️ Temperature Alert', 'Temperature drop forecasted - protect young seedlings', FALSE),
(1, 'PLANTING_REMINDER', '🌾 Optimal Planting', 'Optimal planting conditions next week for leafy greens', TRUE);

-- Query to get unread notifications count for a user
-- SELECT COUNT(*) FROM notifications WHERE user_id = 1 AND is_read = FALSE;

-- Query to get all notifications for a user (newest first)
-- SELECT * FROM notifications WHERE user_id = 1 ORDER BY created_at DESC;

-- Query to mark notification as read
-- UPDATE notifications SET is_read = TRUE WHERE id = ?;

-- Query to mark all notifications as read for a user
-- UPDATE notifications SET is_read = TRUE WHERE user_id = 1;

-- Query to delete old notifications (older than 30 days)
-- DELETE FROM notifications WHERE created_at < DATE_SUB(NOW(), INTERVAL 30 DAY);

