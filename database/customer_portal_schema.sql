-- Customer Portal Database Schema
-- Database: agriculture_db

USE agriculture_db;

-- Customers table
CREATE TABLE IF NOT EXISTS customers (
    customer_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Products table
CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    farm_name VARCHAR(100),
    price VARCHAR(20),
    stock VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Orders table
CREATE TABLE IF NOT EXISTS orders (
    order_id VARCHAR(50) PRIMARY KEY,
    order_date DATE NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    quantity VARCHAR(50),
    total_price VARCHAR(20),
    customer_id VARCHAR(50),
    status VARCHAR(50) DEFAULT 'Processing',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Sample data for testing

-- Insert sample customer
INSERT INTO customers (customer_id, name, email, phone, address)
VALUES ('CUST001', 'John Customer', 'john@example.com', '077-1234567', '123 Green Lane, Colombo')
ON DUPLICATE KEY UPDATE name=name;

-- Insert sample products
INSERT INTO products (product_name, category, farm_name, price, stock) VALUES
('Organic Tomatoes', 'Vegetables', 'Green Valley Farm', '250', '50 kg'),
('Fresh Carrots', 'Vegetables', 'Sunrise Farm', '180', '30 kg'),
('Rice (White)', 'Grains', 'Paddy Fields Co.', '120', '100 kg'),
('Green Beans', 'Vegetables', 'Mountain Farm', '200', '25 kg'),
('Organic Lettuce', 'Vegetables', 'Green Valley Farm', '150', '20 kg'),
('Potatoes', 'Vegetables', 'Highland Farm', '80', '75 kg')
ON DUPLICATE KEY UPDATE product_name=product_name;

-- Insert sample orders
INSERT INTO orders (order_id, order_date, product_name, quantity, total_price, customer_id, status) VALUES
('ORD001', '2024-11-20', 'Organic Tomatoes', '5 kg', '1250', 'CUST001', 'Delivered'),
('ORD002', '2024-11-22', 'Fresh Carrots', '3 kg', '540', 'CUST001', 'In Transit'),
('ORD003', '2024-11-23', 'Rice (White)', '10 kg', '1200', 'CUST001', 'Processing')
ON DUPLICATE KEY UPDATE order_id=order_id;

-- Insert sample traceability data
INSERT INTO traceability (batch_id, stage, location, timestamp, details) VALUES
('BATCH001', 'Planting', 'Green Valley Farm', '2024-11-01 08:00:00', 'Seeds planted'),
('BATCH001', 'Growing', 'Green Valley Farm', '2024-11-10 10:00:00', 'Growth stage 1'),
('BATCH001', 'Harvesting', 'Green Valley Farm', '2024-11-20 06:00:00', 'Harvest complete'),
('BATCH001', 'Packaging', 'Packing Center', '2024-11-20 14:00:00', 'Packed and labeled'),
('BATCH001', 'Delivery', 'Distribution Hub', '2024-11-22 09:00:00', 'Out for delivery')
ON DUPLICATE KEY UPDATE batch_id=batch_id;

SELECT 'Customer Portal database setup complete!' AS Message;

