-- Admin Inventory Management Table
-- This table stores inventory items managed by the admin in the EcoFarm Connect system

-- Drop table if exists (optional - use with caution)
-- DROP TABLE IF EXISTS admin_inventory;

-- Create admin_inventory table
CREATE TABLE IF NOT EXISTS admin_inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(200) NOT NULL,
    category VARCHAR(100) NOT NULL,
    current_stock INT DEFAULT 0,
    min_stock INT DEFAULT 0,
    unit VARCHAR(50) DEFAULT 'pcs',
    status VARCHAR(50) DEFAULT 'In Stock',
    price DECIMAL(10,2) DEFAULT 0.00,
    supplier VARCHAR(200),
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO admin_inventory (item_name, category, current_stock, min_stock, unit, status, price, supplier) VALUES
('NPK Fertilizer', 'Fertilizers', 250, 100, 'kg', 'In Stock', 45.50, 'AgriSupply Co.'),
('Organic Pesticide', 'Pesticides', 45, 50, 'L', 'Low Stock', 85.00, 'EcoFarm Solutions'),
('Wheat Seeds', 'Seeds', 500, 200, 'kg', 'In Stock', 120.00, 'Seed Bank Ltd.'),
('Irrigation Pipes', 'Equipment', 120, 50, 'm', 'In Stock', 15.75, 'Farm Equipment Inc.'),
('Tractor Fuel', 'Fuel', 200, 100, 'L', 'In Stock', 3.50, 'Fuel Distributors'),
('Corn Seeds', 'Seeds', 300, 150, 'kg', 'In Stock', 95.00, 'Seed Bank Ltd.'),
('Organic Compost', 'Fertilizers', 150, 80, 'kg', 'In Stock', 25.00, 'Green Earth Supplies'),
('Hand Tools Set', 'Tools', 25, 20, 'pcs', 'In Stock', 450.00, 'Farm Tools Direct'),
('Water Pump', 'Equipment', 8, 10, 'pcs', 'Low Stock', 2500.00, 'Irrigation Systems Co.'),
('Rice Seeds', 'Seeds', 400, 180, 'kg', 'In Stock', 110.00, 'Seed Bank Ltd.');

-- View all inventory
SELECT * FROM admin_inventory ORDER BY item_name;

-- View low stock items
SELECT * FROM admin_inventory WHERE current_stock <= min_stock ORDER BY current_stock ASC;

-- View inventory by category
SELECT category, COUNT(*) as item_count, SUM(current_stock) as total_stock
FROM admin_inventory
GROUP BY category;

-- Update stock level example
-- UPDATE admin_inventory SET current_stock = 300 WHERE id = 1;

-- Check inventory status
SELECT
    item_name,
    category,
    current_stock,
    min_stock,
    unit,
    CASE
        WHEN current_stock <= min_stock THEN 'Low Stock - Reorder Required'
        WHEN current_stock <= (min_stock * 1.5) THEN 'Medium Stock'
        ELSE 'Good Stock'
    END as stock_status
FROM admin_inventory
ORDER BY current_stock ASC;

