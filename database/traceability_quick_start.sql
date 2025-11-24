-- Quick Start SQL for Traceability System
-- Run this in phpMyAdmin to setup tables

USE ecofarm;

-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS traceability_checkpoints;
DROP TABLE IF EXISTS traceability_records;

-- Create Traceability Records Table
CREATE TABLE traceability_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    batch_id VARCHAR(50) UNIQUE NOT NULL,
    crop_name VARCHAR(100) NOT NULL,
    farmer_name VARCHAR(100) NOT NULL,
    created_date DATE NOT NULL,
    status VARCHAR(50) DEFAULT 'Active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Traceability Checkpoints Table
CREATE TABLE traceability_checkpoints (
    id INT AUTO_INCREMENT PRIMARY KEY,
    batch_id VARCHAR(50) NOT NULL,
    checkpoint_name VARCHAR(100) NOT NULL,
    checkpoint_date DATE NOT NULL,
    location VARCHAR(200),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert Sample Data for Testing
INSERT INTO traceability_records (batch_id, crop_name, farmer_name, created_date, status) VALUES
('BATCH-001', 'Wheat', 'John Farmer', '2025-09-15', 'Completed'),
('BATCH-002', 'Tomatoes', 'Alice Brown', '2025-10-01', 'In Progress'),
('BATCH-003', 'Rice', 'Bob Johnson', '2025-10-10', 'Active');

INSERT INTO traceability_checkpoints (batch_id, checkpoint_name, checkpoint_date, location, notes) VALUES
('BATCH-001', 'Planting', '2025-09-15', 'Field A1', 'Seeds planted in optimal conditions'),
('BATCH-001', 'Growing', '2025-10-01', 'Field A1', 'Crop growing well, healthy leaves'),
('BATCH-001', 'Harvesting', '2025-11-15', 'Field A1', 'Harvest completed successfully'),
('BATCH-001', 'Packaging', '2025-11-16', 'Warehouse B', 'Packaged in eco-friendly bags'),
('BATCH-001', 'Delivery', '2025-11-18', 'City Market', 'Delivered to consumer marketplace'),
('BATCH-002', 'Planting', '2025-10-01', 'Hill Farm Field 2', 'Tomato seeds planted'),
('BATCH-002', 'Growing', '2025-10-20', 'Hill Farm Field 2', 'Plants are healthy and flowering'),
('BATCH-003', 'Planting', '2025-10-10', 'River Farm Paddy Field', 'Rice seedlings transplanted');

-- Verify Tables Created
SELECT 'Tables created successfully!' AS Status;
SELECT COUNT(*) AS 'Total Records' FROM traceability_records;
SELECT COUNT(*) AS 'Total Checkpoints' FROM traceability_checkpoints;

-- Show Sample Data
SELECT * FROM traceability_records;
SELECT * FROM traceability_checkpoints;

