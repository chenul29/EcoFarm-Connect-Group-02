-- =====================================================
-- NOTIFICATIONS SYSTEM - COMPLETE SQL SETUP
-- EcoFarm Connect Smart Agriculture System
-- =====================================================

-- Step 1: Create the database (if not exists)
CREATE DATABASE IF NOT EXISTS agriculture_db;
USE agriculture_db;

-- Step 2: Create users table (if not exists)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(20),
    role ENUM('ADMIN', 'FARMER', 'INSPECTOR', 'CONSUMER') DEFAULT 'FARMER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

-- Step 3: Create notifications table
CREATE TABLE IF NOT EXISTS notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_is_read (is_read),
    INDEX idx_created_at (created_at)
);

-- Step 4: Insert sample users (for testing)
-- Password is plain text for testing, in production use hashed passwords
INSERT INTO users (username, password, full_name, email, phone, role) VALUES
('admin', 'admin123', 'Admin User', 'admin@ecofarm.com', '+1234567890', 'ADMIN'),
('farmer1', 'farmer123', 'John Farmer', 'john@farm.com', '+1234567891', 'FARMER'),
('farmer2', 'farmer123', 'Mary Smith', 'mary@farm.com', '+1234567892', 'FARMER'),
('farmer3', 'farmer123', 'Bob Johnson', 'bob@farm.com', '+1234567893', 'FARMER')
ON DUPLICATE KEY UPDATE username=username;

-- Step 5: Insert sample notifications for testing
INSERT INTO notifications (user_id, notification_type, title, message) VALUES
-- Farmer 1 notifications
(2, 'ALERT', 'Crop Ready for Harvest', 'Your Tomatoes crop in Field A is ready for harvest. Please begin harvesting within the next 3 days.'),
(2, 'REMINDER', 'Planting Window Alert', 'Optimal planting window for Wheat starts in 3 days. Prepare your fields.'),
(2, 'WARNING', 'Low Inventory Alert', 'Your Organic Fertilizer stock is running low (10 kg remaining). Please restock soon.'),
(2, 'INFO', 'Weather Alert', 'Heavy rain expected tomorrow. Please secure your crops and equipment.'),
(2, 'SYSTEM', 'New Order Received', 'You have received a new order for 50kg of Tomatoes. Order ID: #12345'),
(2, 'REMINDER', 'Irrigation Reminder', 'Field B requires irrigation today. Soil moisture level is below optimal.'),
(2, 'INFO', 'Certification Approved', 'Your organic farming certification has been approved. Valid until 2026-11-24.'),

-- Farmer 2 notifications
(3, 'ALERT', 'Pest Alert', 'Pest activity detected in your Corn field. Immediate action recommended.'),
(3, 'INFO', 'Training Workshop', 'Sustainable farming workshop scheduled for next Monday at 10 AM.'),
(3, 'SYSTEM', 'Payment Received', 'Payment of $500 received for Order #12340. Thank you!'),

-- Farmer 3 notifications
(4, 'REMINDER', 'Equipment Maintenance', 'Your tractor is due for maintenance. Please schedule an appointment.'),
(4, 'WARNING', 'Soil Test Required', 'Annual soil testing is due for your farm. Please contact our team.');

-- =====================================================
-- USEFUL QUERIES FOR NOTIFICATIONS
-- =====================================================

-- View all notifications
SELECT * FROM notifications ORDER BY created_at DESC;

-- View all unread notifications for a specific user (e.g., user_id = 2)
SELECT * FROM notifications
WHERE user_id = 2 AND is_read = FALSE
ORDER BY created_at DESC;

-- Count unread notifications for a user
SELECT COUNT(*) as unread_count
FROM notifications
WHERE user_id = 2 AND is_read = FALSE;

-- Mark a specific notification as read
-- UPDATE notifications SET is_read = TRUE WHERE id = 1;

-- Mark all notifications as read for a user
-- UPDATE notifications SET is_read = TRUE WHERE user_id = 2;

-- Delete a specific notification
-- DELETE FROM notifications WHERE id = 1;

-- Delete all read notifications for a user
-- DELETE FROM notifications WHERE user_id = 2 AND is_read = TRUE;

-- Get notifications by type
SELECT * FROM notifications
WHERE notification_type = 'ALERT'
ORDER BY created_at DESC;

-- Get recent notifications (last 7 days)
SELECT * FROM notifications
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 7 DAY)
ORDER BY created_at DESC;

-- Count notifications by type for a user
SELECT notification_type, COUNT(*) as count
FROM notifications
WHERE user_id = 2
GROUP BY notification_type;

-- =====================================================
-- AUTOMATIC NOTIFICATION TRIGGERS (EXAMPLES)
-- =====================================================

-- These are examples of how automatic notifications can be created
-- You would call these from your Java application

-- Example 1: Low Inventory Alert
-- INSERT INTO notifications (user_id, notification_type, title, message)
-- SELECT farmer_id, 'WARNING', 'Low Inventory Alert',
--        CONCAT('Your ', item_name, ' stock is running low (', current_stock, ' ', unit, ' remaining)')
-- FROM inventory
-- WHERE current_stock < minimum_stock;

-- Example 2: Harvest Ready Notification
-- INSERT INTO notifications (user_id, notification_type, title, message)
-- SELECT farmer_id, 'ALERT', 'Crop Ready for Harvest',
--        CONCAT('Your ', crop_name, ' in ', field_name, ' is ready for harvest')
-- FROM crops
-- WHERE growth_stage = 'MATURE' AND harvest_notified = FALSE;

-- =====================================================
-- CLEANUP QUERIES
-- =====================================================

-- Delete old read notifications (older than 30 days)
-- DELETE FROM notifications
-- WHERE is_read = TRUE
-- AND created_at < DATE_SUB(NOW(), INTERVAL 30 DAY);

-- Reset all notifications for testing
-- DELETE FROM notifications;

-- =====================================================
-- ADMIN QUERIES
-- =====================================================

-- Get all farmers for sending notifications
SELECT id, username, full_name, email FROM users WHERE role = 'FARMER' AND is_active = TRUE;

-- Send notification to all farmers
-- INSERT INTO notifications (user_id, notification_type, title, message)
-- SELECT id, 'INFO', 'System Announcement', 'Important system announcement message here'
-- FROM users WHERE role = 'FARMER' AND is_active = TRUE;

-- Count total notifications by user
SELECT u.username, u.full_name, COUNT(n.id) as total_notifications,
       SUM(CASE WHEN n.is_read = FALSE THEN 1 ELSE 0 END) as unread_count
FROM users u
LEFT JOIN notifications n ON u.id = n.user_id
WHERE u.role = 'FARMER'
GROUP BY u.id, u.username, u.full_name;

