-- SQL Script for Farmer Inventory Tables
-- Run this in phpMyAdmin

USE agriculture_db;

-- Create tools table
CREATE TABLE IF NOT EXISTS farmer_tools (
    id INT AUTO_INCREMENT PRIMARY KEY,
    farmer_id INT NOT NULL,
    tool_name VARCHAR(100) NOT NULL,
    condition_status VARCHAR(50) DEFAULT 'Good',
    last_used DATE,
    status VARCHAR(50) DEFAULT 'Available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create seeds table
CREATE TABLE IF NOT EXISTS farmer_seeds (
    id INT AUTO_INCREMENT PRIMARY KEY,
    farmer_id INT NOT NULL,
    seed_name VARCHAR(100) NOT NULL,
    quantity INT DEFAULT 0,
    unit VARCHAR(20) DEFAULT 'packets',
    status VARCHAR(50) DEFAULT 'Sufficient',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample tools data for farmer_id = 1
INSERT INTO farmer_tools (farmer_id, tool_name, condition_status, last_used, status)
VALUES
(1, 'Tractor', 'Good', '2024-11-20', 'Available'),
(1, 'Plow', 'Excellent', '2024-11-15', 'Available'),
(1, 'Irrigation Pump', 'Fair', '2024-11-22', 'In Use'),
(1, 'Sprayer', 'Good', '2024-11-18', 'Available'),
(1, 'Cultivator', 'Excellent', '2024-11-10', 'Available');

-- Insert sample seeds data for farmer_id = 1
INSERT INTO farmer_seeds (farmer_id, seed_name, quantity, unit, status)
VALUES
(1, 'Tomato Seeds', 25, 'packets', 'Sufficient'),
(1, 'Carrot Seeds', 8, 'packets', 'Low Stock'),
(1, 'Lettuce Seeds', 15, 'packets', 'Sufficient'),
(1, 'Rice Seeds', 50, 'kg', 'Sufficient'),
(1, 'Wheat Seeds', 5, 'kg', 'Low Stock'),
(1, 'Corn Seeds', 30, 'packets', 'Sufficient');

