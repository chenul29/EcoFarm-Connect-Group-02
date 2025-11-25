# Admin Inventory Management - EcoFarm Connect

## Overview
This document explains the new Inventory Management functionality added to the Admin Dashboard in the EcoFarm Connect Smart Agriculture System.

## What Was Implemented

### 1. Database Table
A new table `admin_inventory` was created to store inventory items with the following fields:
- `id` - Auto-increment primary key
- `item_name` - Name of the inventory item
- `category` - Category (Fertilizers, Seeds, Pesticides, Equipment, Tools, Fuel, Other)
- `current_stock` - Current stock level
- `min_stock` - Minimum stock level for low stock alerts
- `unit` - Unit of measurement (kg, L, pcs, packets, m, tons)
- `status` - Stock status (In Stock, Low Stock)
- `price` - Price per unit
- `supplier` - Supplier name
- `last_updated` - Last update timestamp
- `created_at` - Creation timestamp

### 2. InventoryDAO Class Updates
New methods added to `InventoryDAO.java`:

#### Admin Inventory Methods:
- `createAdminInventoryTable()` - Creates the admin_inventory table
- `getAllInventory()` - Retrieves all inventory items
- `addInventoryItem()` - Adds a new inventory item
- `updateInventoryItem()` - Updates an existing inventory item
- `deleteInventoryItem()` - Deletes an inventory item
- `getInventoryItemById()` - Gets a specific item by ID
- `updateStock()` - Updates only the stock level
- `getLowStockItems()` - Retrieves items with low stock

### 3. Admin Dashboard Features
The Inventory Management panel in the Admin Dashboard now includes:

#### Buttons:
1. **Add Item** - Opens a dialog to add new inventory items
   - Enter item name, category, stock levels, unit, supplier, and price
   - Automatically sets status based on stock level

2. **Edit Item** - Opens a dialog to edit existing inventory items
   - Select an item from the table
   - Update all fields including stock levels

3. **Delete Item** - Deletes selected inventory items
   - Confirmation dialog before deletion
   - Permanent removal from database

4. **Update Stock** - Quick stock level update
   - Select an item
   - Enter new stock quantity
   - Automatically updates status (In Stock/Low Stock)

5. **Low Stock Alert** - Shows all items with low stock
   - Displays items where current_stock <= min_stock
   - Option to send notifications to farmers
   - Redirects to Notifications tab

6. **Refresh** - Reloads inventory data from database

#### Table Display:
- Shows all inventory items in a table format
- Columns: ID, Item Name, Category, Current Stock, Min Stock, Unit, Status, Last Updated
- Auto-updates after any operation

## How to Use

### Step 1: Setup Database Table
Run the SQL script to create the table and add sample data:
```sql
-- Navigate to phpMyAdmin or MySQL client
-- Run the file: database/admin_inventory_setup.sql
```

Or the table will be created automatically when you open the Inventory Management tab.

### Step 2: Run the Application
```bash
# Compile the project
mvn clean compile

# Run the admin dashboard
java -cp target/classes adminDashboard
```

Or run through login:
```bash
java -cp target/classes login_signup
```

### Step 3: Access Inventory Management
1. Login as Admin
2. Click on the "📦 Inventory" tab
3. The table will load with existing inventory items

### Step 4: Add New Inventory Item
1. Click **"Add Item"** button
2. Fill in the form:
   - Item Name (required)
   - Category (dropdown)
   - Current Stock (number)
   - Min Stock (alert level)
   - Unit (dropdown)
   - Supplier (optional)
   - Price per Unit (decimal)
3. Click **"Save Item"**
4. Item will appear in the table

### Step 5: Edit Inventory Item
1. Select an item from the table (click on the row)
2. Click **"Edit Item"** button
3. Update the fields in the dialog
4. Click **"Update Item"**
5. Changes will be saved to database

### Step 6: Update Stock Level
1. Select an item from the table
2. Click **"Update Stock"** button
3. Enter the new stock quantity
4. Click **"Update Stock"**
5. Status will automatically update based on min stock level

### Step 7: Check Low Stock Items
1. Click **"Low Stock Alert"** button
2. View all items where current_stock <= min_stock
3. Optionally send notification to farmers
4. Click **"Close"** to return

### Step 8: Delete Inventory Item
1. Select an item from the table
2. Click **"Delete Item"** button
3. Confirm the deletion
4. Item will be removed from database

## Database Schema

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

## Sample Data

```sql
INSERT INTO admin_inventory (item_name, category, current_stock, min_stock, unit, status, price, supplier) VALUES
('NPK Fertilizer', 'Fertilizers', 250, 100, 'kg', 'In Stock', 45.50, 'AgriSupply Co.'),
('Organic Pesticide', 'Pesticides', 45, 50, 'L', 'Low Stock', 85.00, 'EcoFarm Solutions'),
('Wheat Seeds', 'Seeds', 500, 200, 'kg', 'In Stock', 120.00, 'Seed Bank Ltd.');
```

## Code Structure

### Files Modified:
1. `src/main/java/InventoryDAO.java` - Added admin inventory methods
2. `src/main/java/adminDashboard.java` - Implemented inventory UI and functionality

### Files Created:
1. `database/admin_inventory_setup.sql` - Database setup script

## Features

### Automatic Status Updates
The system automatically sets the status based on stock levels:
- **In Stock** - when current_stock > min_stock
- **Low Stock** - when current_stock <= min_stock

### Low Stock Alerts
When you click "Low Stock Alert":
- Shows all items that need restocking
- Displays item details in a table
- Option to notify farmers
- Helps prevent stockouts

### Data Validation
- Item name is required
- Stock levels must be numbers
- Price must be a valid decimal
- Category and unit selected from predefined lists

## Integration with Other Modules

### Notifications
Low stock alerts can trigger notifications to farmers through the Notifications tab.

### Reports
Inventory data can be included in reports and analytics.

### Marketplace
Inventory items can be linked to marketplace products.

## Troubleshooting

### Issue: Table not loading
**Solution:** 
- Check database connection in DatabaseConnection.java
- Ensure MySQL is running
- Verify database credentials (username: root, password: root)

### Issue: Cannot add item
**Solution:**
- Check that all required fields are filled
- Verify stock and price are valid numbers
- Ensure database table exists

### Issue: Low stock alert not showing items
**Solution:**
- Add items with current_stock <= min_stock
- Click Refresh to reload data
- Check database for existing items

## Future Enhancements
Potential improvements for the inventory system:
1. Barcode/QR code scanning
2. Automatic reorder when low stock
3. Supplier management system
4. Price history tracking
5. Stock movement logs
6. Export inventory reports to PDF/Excel
7. Multi-location inventory tracking
8. Integration with purchase orders

## Code Example - Add Inventory Item

```java
// Example of adding an item programmatically
boolean success = InventoryDAO.addInventoryItem(
    "NPK Fertilizer",    // item name
    "Fertilizers",       // category
    250,                 // current stock
    100,                 // min stock
    "kg",                // unit
    "AgriSupply Co.",    // supplier
    45.50                // price
);

if (success) {
    System.out.println("Item added successfully!");
}
```

## Summary
The Inventory Management system provides a complete solution for:
- Adding and managing inventory items
- Tracking stock levels
- Setting low stock alerts
- Updating stock quantities
- Managing supplier information
- Monitoring inventory status

All functionality is integrated into the Admin Dashboard with a simple, beginner-friendly interface.

