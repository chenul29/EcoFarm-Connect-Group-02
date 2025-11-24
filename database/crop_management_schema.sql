-- Crop Management System Database Schema

USE ecofarm;

-- Drop table if exists (for clean setup)
DROP TABLE IF EXISTS crops;

-- Create Crops Table
CREATE TABLE crops (
    id INT AUTO_INCREMENT PRIMARY KEY,
    crop_name VARCHAR(100) NOT NULL,
    variety VARCHAR(100),
    planting_date DATE NOT NULL,
    expected_harvest DATE NOT NULL,
    soil_type VARCHAR(50),
    irrigation_schedule VARCHAR(100),
    growth_stage VARCHAR(50) DEFAULT 'Planted',
    health_status VARCHAR(50) DEFAULT 'Good',
    farmer_name VARCHAR(100),
    field_location VARCHAR(200),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert Sample Data
INSERT INTO crops (crop_name, variety, planting_date, expected_harvest, soil_type, irrigation_schedule, growth_stage, health_status, farmer_name, field_location, notes) VALUES
('Wheat', 'Winter Wheat', '2025-09-15', '2025-12-15', 'Loamy', 'Weekly - 2 inches', 'Growing', 'Excellent', 'John Farmer', 'Green Valley Field A', 'Premium quality seeds used'),
('Corn', 'Sweet Corn', '2025-08-20', '2025-11-30', 'Sandy Loam', 'Twice Weekly', 'Flowering', 'Good', 'Mary Smith', 'Sunny Acres Field 2', 'High yield expected'),
('Rice', 'Basmati', '2025-10-01', '2026-01-15', 'Clay', 'Flooded Daily', 'Planted', 'Good', 'Bob Johnson', 'River Farm Paddy Field', 'Organic farming method'),
('Tomatoes', 'Cherry Tomatoes', '2025-07-10', '2025-11-20', 'Loamy', 'Daily - Drip System', 'Harvesting', 'Excellent', 'Alice Brown', 'Hill Farm Field 3', 'Ready for harvest'),
('Potatoes', 'Russet', '2025-09-05', '2025-12-10', 'Sandy', 'Weekly', 'Growing', 'Fair', 'Tom Wilson', 'Valley Farm Field 1', 'Monitor for pests'),
('Carrots', 'Nantes', '2025-10-15', '2026-01-20', 'Loamy', 'Twice Weekly', 'Planted', 'Good', 'Sarah Davis', 'Garden Farm Plot 4', 'First season crop');

-- Verify Data
SELECT 'Crops table created successfully!' AS Status;
SELECT COUNT(*) AS 'Total Crops' FROM crops;
SELECT * FROM crops;

