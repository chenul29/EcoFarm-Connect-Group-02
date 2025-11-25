-- Marketplace Products Table
-- This table stores products added by admin for the marketplace

-- Create marketplace_products table
CREATE TABLE IF NOT EXISTS marketplace_products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(200) NOT NULL,
    farmer_name VARCHAR(200) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT DEFAULT 0,
    unit VARCHAR(50) DEFAULT 'kg',
    category VARCHAR(100),
    status VARCHAR(50) DEFAULT 'Active',
    approval VARCHAR(50) DEFAULT 'Pending',
    orders INT DEFAULT 0,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample marketplace products
INSERT INTO marketplace_products (product_name, farmer_name, category, price, quantity, unit, status, approval, orders, description) VALUES
('Organic Wheat', 'John Farmer', 'Grains', 245.00, 500, 'kg', 'Active', 'Approved', 12, 'Fresh organic wheat from our farm'),
('Fresh Tomatoes', 'Alice Brown', 'Vegetables', 85.00, 200, 'kg', 'Active', 'Approved', 8, 'Freshly picked red tomatoes'),
('Sweet Corn', 'Mary Smith', 'Vegetables', 180.00, 350, 'kg', 'Active', 'Pending', 0, 'Sweet and tender corn'),
('Brown Rice', 'Bob Johnson', 'Grains', 320.00, 600, 'kg', 'Active', 'Approved', 15, 'Healthy brown rice'),
('Fresh Strawberries', 'Emma Wilson', 'Fruits', 450.00, 150, 'kg', 'Active', 'Approved', 5, 'Sweet organic strawberries');

-- View all products
SELECT * FROM marketplace_products ORDER BY created_at DESC;

-- View pending approval products
SELECT * FROM marketplace_products WHERE approval = 'Pending';

-- View approved products
SELECT * FROM marketplace_products WHERE approval = 'Approved';

-- View products by category
SELECT category, COUNT(*) as product_count, SUM(quantity) as total_quantity
FROM marketplace_products
GROUP BY category;

-- Update product price example
-- UPDATE marketplace_products SET price = 250.00 WHERE id = 1;

-- Approve a product example
-- UPDATE marketplace_products SET approval = 'Approved', status = 'Active' WHERE id = 3;

