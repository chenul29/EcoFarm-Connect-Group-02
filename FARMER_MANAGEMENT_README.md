# Farmer Management System - Setup Instructions

## Database Setup

1. Open phpMyAdmin in your browser (usually http://localhost/phpmyadmin)
2. Select your database 'ecofarm' (or create it if it doesn't exist)
3. Go to the SQL tab
4. Copy and paste the following SQL:

```sql
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
```

5. Click "Go" to execute the SQL

## Features Implemented

### 1. Register Farmer Form
- Add new farmers with all required details
- Fields: Name, Farmer ID, Farm Size, Certifications, Farm Location, Phone, Email
- Automatic sustainability score calculation based on certifications and farm size

### 2. Farmer Table (View All)
- Displays all farmers from database
- Shows: ID, Name, Farmer ID, Farm Size, Certifications, Farm Location, Sustainability Score, Phone, Email, Status

### 3. Edit Farmer Profile
- Select a farmer from the table
- Click "Edit Farmer" button
- Update farmer details
- Changes are saved to database

### 4. Delete Farmer
- Select a farmer from the table
- Click "Delete Farmer" button
- Confirm deletion
- Farmer is removed from database

### 5. Sustainability Score
- Automatically calculated based on:
  - Base score: 50 points
  - Organic certification: +20 points
  - Sustainable certification: +15 points
  - Farm size (smaller farms get more points):
    - Less than 30 acres: +15 points
    - 30-50 acres: +10 points
    - Over 50 acres: +5 points
  - Maximum score: 100 points

### 6. Farm Location
- Simple text field to enter farm location
- Example: "Green Valley, Region A"

### 7. Refresh Button
- Click to reload farmer data from database
- Updates the table with latest information

## How to Use

1. Login as admin
2. Go to "👨‍🌾 Farmer Management" tab
3. Use buttons to:
   - **Add Farmer**: Opens form to register new farmer
   - **Edit Farmer**: Select a farmer and edit their details
   - **Delete Farmer**: Select a farmer and delete them
   - **Refresh**: Reload data from database

## Database Structure

**Table Name:** farmers

**Columns:**
- id (INT, Primary Key, Auto Increment)
- name (VARCHAR 100, Required)
- farmer_id (VARCHAR 50, Unique, Required)
- farm_size (VARCHAR 50)
- certifications (VARCHAR 100)
- farm_location (VARCHAR 200)
- sustainability_score (INT, Default 0)
- phone (VARCHAR 20)
- email (VARCHAR 100)
- status (VARCHAR 20, Default 'Active')
- created_date (TIMESTAMP, Auto set)

## Files Created/Modified

1. **FarmerDAO.java** - Database operations for farmers
   - createTable() - Creates farmers table
   - addFarmer() - Adds new farmer
   - getAllFarmers() - Gets all farmers
   - updateFarmer() - Updates farmer details
   - deleteFarmer() - Deletes farmer
   - calculateSustainabilityScore() - Calculates score

2. **adminDashboard.java** - Updated Farmer Management panel
   - Added FarmerDAO instance
   - loadFarmersData() - Loads data from database
   - showAddFarmerDialog() - Shows add farmer form
   - showEditFarmerDialog() - Shows edit farmer form
   - deleteFarmer() - Deletes selected farmer

3. **database/farmer_management.sql** - SQL script to create table

## Code Style

The code is written in a simple, beginner-friendly style:
- Minimal error handling
- Simple database operations
- Clear method names
- Easy to understand logic
- No complex patterns or frameworks

