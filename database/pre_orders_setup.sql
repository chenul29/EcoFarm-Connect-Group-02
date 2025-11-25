-- SQL Query to create pre_orders table for customer pre-orders
-- Use this query in phpMyAdmin to create the table manually

CREATE TABLE IF NOT EXISTS pre_orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(200) NOT NULL,
    product_name VARCHAR(200) NOT NULL,
    quantity INT NOT NULL,
    unit VARCHAR(50),
    expected_delivery_date DATE,
    contact_number VARCHAR(50),
    delivery_address TEXT,
    notes TEXT,
    status VARCHAR(50) DEFAULT 'Pending',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Sample data for testing (optional)
INSERT INTO pre_orders (customer_name, product_name, quantity, unit, expected_delivery_date, contact_number, delivery_address, notes)
VALUES
('John Doe', 'Organic Tomatoes', 50, 'kg', '2025-01-15', '123-456-7890', '123 Main St, City', 'Please deliver in the morning'),
('Jane Smith', 'Fresh Carrots', 30, 'kg', '2025-01-20', '098-765-4321', '456 Oak Ave, Town', 'Need for restaurant use');

-- View all pre-orders
SELECT * FROM pre_orders ORDER BY order_date DESC;

-- View pending pre-orders
SELECT * FROM pre_orders WHERE status = 'Pending' ORDER BY order_date DESC;

