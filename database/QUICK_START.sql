-- ============================================
-- QUICK START: Run this in phpMyAdmin
-- ============================================
-- Database: agriculture_db
-- Just copy and paste this entire file into phpMyAdmin SQL tab
-- ============================================

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

-- Insert sample notifications for testing (user_id = 1 for farmer)
INSERT INTO notifications (user_id, notification_type, title, message, is_read) VALUES
(1, 'HARVEST_READY', '🌾 Harvest Ready', 'Tomatoes in Field B are ready for harvest!', FALSE),
(1, 'LOW_INVENTORY', '⚠️ Low Inventory Alert', 'Organic Fertilizer stock is running low (5 units remaining). Please restock soon.', FALSE),
(1, 'ORDER_RECEIVED', '📦 New Order Received', 'New order: 50kg organic tomatoes from Green Market', FALSE),
(1, 'WEATHER_ALERT', '🌦️ Weather Alert', 'Heavy rain expected tomorrow - postpone irrigation', FALSE),
(1, 'PLANTING_REMINDER', '🌱 Planting Reminder', 'It\'s time to plant Winter Wheat (Due: Nov 25, 2024)', FALSE),
(1, 'WEATHER_ALERT', '❄️ Temperature Alert', 'Temperature drop forecasted - protect young seedlings', TRUE),
(1, 'RESOURCE_SHARING', '🤝 Resource Available', 'John\'s tractor is available for rent this weekend', TRUE);

-- Done! Now run your application and login as farmer

