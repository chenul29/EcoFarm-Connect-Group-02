-- Create Farmers table
CREATE TABLE IF NOT EXISTS farmers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    farmer_id VARCHAR(50) UNIQUE NOT NULL,
    farm_size VARCHAR(50),
    certifications VARCHAR(100),
    farm_location VARCHAR(200),
    sustainability_score INT DEFAULT 0,
    phone VARCHAR(20),
    email VARCHAR(100),
    status VARCHAR(20) DEFAULT 'Active',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO farmers (name, farmer_id, farm_size, certifications, farm_location, sustainability_score, phone, email, status) VALUES
('John Farmer', 'F001', '50 acres', 'Organic', 'Green Valley, Region A', 85, '+1234567890', 'john@farm.com', 'Active'),
('Mary Smith', 'F002', '35 acres', 'Sustainable', 'Sunny Acres, Region B', 78, '+1234567891', 'mary@farm.com', 'Active'),
('Bob Johnson', 'F003', '60 acres', 'Organic,Sustainable', 'River Farm, Region C', 92, '+1234567892', 'bob@farm.com', 'Active'),
('Alice Brown', 'F004', '25 acres', 'None', 'Hill Farm, Region A', 65, '+1234567893', 'alice@farm.com', 'Pending'),
('Tom Wilson', 'F005', '40 acres', 'Organic', 'Valley Farm, Region D', 88, '+1234567894', 'tom@farm.com', 'Active');

