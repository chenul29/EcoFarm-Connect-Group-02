-- SQL Script for Notifications System
-- Run this in phpMyAdmin

USE agriculture_db;

-- Create notifications table
CREATE TABLE IF NOT EXISTS farmer_notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    farmer_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,
    notification_type VARCHAR(50) DEFAULT 'INFO',
    is_read BOOLEAN DEFAULT FALSE,
    sent_by VARCHAR(100) DEFAULT 'Admin',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample notifications for farmer_id = 1
INSERT INTO farmer_notifications (farmer_id, title, message, notification_type, is_read, sent_by)
VALUES
(1, 'Welcome to EcoFarm Connect', 'Welcome to your farmer portal! Start managing your crops today.', 'INFO', TRUE, 'System'),
(1, 'Crop Ready to Harvest', 'Your Tomatoes are ready to harvest! Check your crops section.', 'ALERT', FALSE, 'System'),
(1, 'Low Seed Stock Alert', 'Carrot Seeds are running low (8 packets remaining). Please restock.', 'WARNING', FALSE, 'System'),
(1, 'Weather Alert', 'Heavy rain expected tomorrow. Ensure proper drainage for your crops.', 'WARNING', FALSE, 'Weather System'),
(1, 'New Training Available', 'New organic farming training session available next week. Register now!', 'INFO', FALSE, 'Admin');

