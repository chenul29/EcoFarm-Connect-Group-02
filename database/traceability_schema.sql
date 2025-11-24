-- Traceability System Database Schema

-- Create database if not exists
CREATE DATABASE IF NOT EXISTS ecofarm;
USE ecofarm;

-- Traceability Records Table
CREATE TABLE IF NOT EXISTS traceability_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    batch_id VARCHAR(50) UNIQUE NOT NULL,
    crop_name VARCHAR(100) NOT NULL,
    farmer_name VARCHAR(100) NOT NULL,
    created_date DATE NOT NULL,
    status VARCHAR(50) DEFAULT 'Active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Traceability Checkpoints Table
CREATE TABLE IF NOT EXISTS traceability_checkpoints (
    id INT AUTO_INCREMENT PRIMARY KEY,
    batch_id VARCHAR(50) NOT NULL,
    checkpoint_name VARCHAR(100) NOT NULL,
    checkpoint_date DATE NOT NULL,
    location VARCHAR(200),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (batch_id) REFERENCES traceability_records(batch_id) ON DELETE CASCADE
);

-- Insert sample data
INSERT INTO traceability_records (batch_id, crop_name, farmer_name, created_date, status) VALUES
('BATCH-001', 'Wheat', 'John Farmer', '2025-09-15', 'Completed'),
('BATCH-002', 'Tomatoes', 'Alice Brown', '2025-10-01', 'In Progress'),
('BATCH-003', 'Rice', 'Bob Johnson', '2025-10-10', 'Active');

INSERT INTO traceability_checkpoints (batch_id, checkpoint_name, checkpoint_date, location, notes) VALUES
('BATCH-001', 'Planting', '2025-09-15', 'Field A1', 'Seeds planted'),
('BATCH-001', 'Growing', '2025-10-01', 'Field A1', 'Crop growing well'),
('BATCH-001', 'Harvesting', '2025-11-15', 'Field A1', 'Harvest completed'),
('BATCH-001', 'Packaging', '2025-11-16', 'Warehouse B', 'Packaged for delivery'),
('BATCH-001', 'Delivery', '2025-11-18', 'Market Center', 'Delivered to market'),
('BATCH-002', 'Planting', '2025-10-01', 'Hill Farm Field 2', 'Tomato seeds planted'),
('BATCH-002', 'Growing', '2025-10-20', 'Hill Farm Field 2', 'Plants are healthy');

