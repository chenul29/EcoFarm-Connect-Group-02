-- Customer Orders Table
-- This table stores all customer orders from the marketplace

-- Create customer_orders table
CREATE TABLE IF NOT EXISTS customer_orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(200) NOT NULL,
    product_id INT NOT NULL,
    product_name VARCHAR(200) NOT NULL,
    farmer_name VARCHAR(200),
    quantity INT NOT NULL,
    unit VARCHAR(50),
    price DECIMAL(10,2) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    order_status VARCHAR(50) DEFAULT 'Pending',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- View all orders
SELECT * FROM customer_orders ORDER BY order_date DESC;

-- View orders by customer
SELECT * FROM customer_orders WHERE customer_name = 'Guest Customer' ORDER BY order_date DESC;

-- View pending orders
SELECT * FROM customer_orders WHERE order_status = 'Pending';

-- View orders with details
SELECT
    co.id,
    co.customer_name,
    co.product_name,
    co.farmer_name,
    co.quantity,
    co.unit,
    co.price,
    co.total_amount,
    co.order_status,
    co.order_date
FROM customer_orders co
ORDER BY co.order_date DESC;

-- Calculate total sales
SELECT
    SUM(total_amount) as total_sales,
    COUNT(*) as total_orders,
    AVG(total_amount) as average_order_value
FROM customer_orders;

-- Sales by product
SELECT
    product_name,
    COUNT(*) as order_count,
    SUM(quantity) as total_quantity,
    SUM(total_amount) as total_revenue
FROM customer_orders
GROUP BY product_name
ORDER BY total_revenue DESC;

-- Sales by farmer
SELECT
    farmer_name,
    COUNT(*) as order_count,
    SUM(total_amount) as total_revenue
FROM customer_orders
GROUP BY farmer_name
ORDER BY total_revenue DESC;

-- Update order status example
-- UPDATE customer_orders SET order_status = 'Completed' WHERE id = 1;

-- Delete an order example
-- DELETE FROM customer_orders WHERE id = 1;

