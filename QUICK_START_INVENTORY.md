# Quick Start - Admin Inventory Management

## Step 1: Create Database Table

Open phpMyAdmin and run this SQL query:

```sql
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
```

## Step 2: Add Sample Data (Optional)

```sql
INSERT INTO admin_inventory (item_name, category, current_stock, min_stock, unit, status, price, supplier) VALUES
('NPK Fertilizer', 'Fertilizers', 250, 100, 'kg', 'In Stock', 45.50, 'AgriSupply Co.'),
('Organic Pesticide', 'Pesticides', 45, 50, 'L', 'Low Stock', 85.00, 'EcoFarm Solutions'),
('Wheat Seeds', 'Seeds', 500, 200, 'kg', 'In Stock', 120.00, 'Seed Bank Ltd.'),
('Irrigation Pipes', 'Equipment', 120, 50, 'm', 'In Stock', 15.75, 'Farm Equipment Inc.'),
('Tractor Fuel', 'Fuel', 200, 100, 'L', 'In Stock', 3.50, 'Fuel Distributors');
```

## Step 3: Run the Application

1. Open IntelliJ IDEA
2. Navigate to `src/main/java/adminDashboard.java`
3. Right-click and select "Run 'adminDashboard.main()'"

OR

1. Navigate to `src/main/java/login_signup.java`
2. Right-click and select "Run 'login_signup.main()'"
3. Login as admin
4. Admin Dashboard will open

## Step 4: Test Inventory Management

1. Click on **"📦 Inventory"** tab in Admin Dashboard
2. You should see the inventory table with items
3. Try these actions:
   - **Add Item**: Click "Add Item" button and fill the form
   - **Edit Item**: Select an item and click "Edit Item"
   - **Update Stock**: Select an item and click "Update Stock"
   - **Low Stock Alert**: Click to see items needing restock
   - **Delete Item**: Select an item and click "Delete Item"

## Verify Everything Works

### Check if table was created:
```sql
SHOW TABLES LIKE 'admin_inventory';
```

### View all inventory items:
```sql
SELECT * FROM admin_inventory;
```

### Check low stock items:
```sql
SELECT * FROM admin_inventory WHERE current_stock <= min_stock;
```

### Update stock example:
```sql
UPDATE admin_inventory SET current_stock = 300 WHERE id = 1;
```

### Delete an item example:
```sql
DELETE FROM admin_inventory WHERE id = 5;
```

## Troubleshooting

**Problem:** Table doesn't load in UI
- Check if MySQL is running
- Verify database connection settings
- Check if table exists in database

**Problem:** Cannot add items
- Ensure all required fields are filled
- Check that numbers are valid (no text in number fields)

**Problem:** Changes not saving
- Verify database connection
- Check MySQL logs for errors
- Ensure user has write permissions

## Database Connection Settings

Default settings in `DatabaseConnection.java`:
- **Host:** localhost
- **Port:** 3306
- **Database:** ecofarm (or your database name)
- **Username:** root
- **Password:** root

If your settings are different, update them in `DatabaseConnection.java`.

## Done!

Your inventory management system is now ready to use. You can:
✅ Add inventory items
✅ Update stock levels
✅ Track low stock items
✅ Edit item details
✅ Delete items
✅ View all inventory in a table

All data is saved in the MySQL database table `admin_inventory`.

