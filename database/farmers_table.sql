-- SQL Query for Farmers Table
-- Run this in phpMyAdmin

USE agriculture_db;

CREATE TABLE IF NOT EXISTS farmers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    farmer_id VARCHAR(50) UNIQUE NOT NULL,
    farm_size VARCHAR(50),
    certifications TEXT,
    farm_location VARCHAR(200),
    phone VARCHAR(20),
    email VARCHAR(100),
    sustainability_score INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample farmers for testing
INSERT INTO farmers (name, farmer_id, farm_size, certifications, farm_location, phone, email, sustainability_score)
VALUES
('John Smith', 'F001', '25 acres', 'Organic Certified', 'Green Valley Farm, CA', '555-0101', 'john@farm.com', 85),
('Mary Johnson', 'F002', '40 acres', 'Sustainable Agriculture', 'Sunny Hills Farm, TX', '555-0102', 'mary@farm.com', 92),
('David Brown', 'F003', '15 acres', 'Water Conservation Expert', 'River Side Farm, FL', '555-0103', 'david@farm.com', 78);

