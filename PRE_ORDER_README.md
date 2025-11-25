# Pre-Order Products Feature - Customer Portal

## Overview
This feature allows customers to pre-order products that may not be currently available in the marketplace. Pre-orders are stored in the database and can be managed by administrators.

## Files Created/Modified

### 1. PreOrderDAO.java
- Handles all database operations for pre-orders
- Methods:
  - `createPreOrdersTable()` - Creates the pre_orders table
  - `addPreOrder()` - Adds a new pre-order to database
  - `getPreOrdersByCustomer()` - Gets all pre-orders for a specific customer
  - `getAllPreOrders()` - Gets all pre-orders (for admin view)

### 2. customerPotral.java (Modified)
- Added pre-order button functionality
- Added `addPreOrderListener()` method
- Added `showPreOrderDialog()` method - Opens a dialog to collect pre-order details

### 3. database/pre_orders_setup.sql
- SQL script to create the pre_orders table manually in phpMyAdmin

## Database Table Structure

**Table Name:** `pre_orders`

| Column | Type | Description |
|--------|------|-------------|
| id | INT (Auto Increment) | Primary key |
| customer_name | VARCHAR(200) | Customer name who placed the order |
| product_name | VARCHAR(200) | Name of the product to pre-order |
| quantity | INT | Quantity requested |
| unit | VARCHAR(50) | Unit of measurement (kg, lbs, pieces, etc.) |
| expected_delivery_date | DATE | When customer expects delivery |
| contact_number | VARCHAR(50) | Customer contact number |
| delivery_address | TEXT | Delivery address |
| notes | TEXT | Additional notes/requirements |
| status | VARCHAR(50) | Order status (Default: 'Pending') |
| order_date | TIMESTAMP | When the pre-order was placed |

## How to Use

### Setup Database (One-time)
1. Open phpMyAdmin
2. Select your database (e.g., `ecofarm`)
3. Go to SQL tab
4. Copy and paste the contents of `database/pre_orders_setup.sql`
5. Click "Go" to execute

### Using the Feature
1. Run the Customer Portal application
2. Click the **"Pre-Order Products"** button (pink button)
3. A dialog box will open with the following fields:
   - **Product Name** - Enter the product you want to pre-order
   - **Quantity** - Enter how much you need
   - **Unit** - Select from dropdown (kg, lbs, pieces, bags, boxes)
   - **Expected Delivery Date** - When you need it delivered
   - **Contact Number** - Your phone number
   - **Delivery Address** - Where to deliver
   - **Additional Notes** - Any special requirements
4. Click **"Submit Pre-Order"** to save
5. You'll get a confirmation message

## Code Example - How It Works

When user clicks Pre-Order button:
```java
jButton4.addActionListener(e -> showPreOrderDialog());
```

The dialog collects information and saves to database:
```java
boolean success = PreOrderDAO.addPreOrder(
    customerName,
    productName,
    quantity,
    unit,
    deliveryDate,
    contactNumber,
    address,
    notes
);
```

## Future Enhancements (Optional)
- Admin panel to view and manage pre-orders
- Email notifications when pre-order is fulfilled
- Pre-order status tracking
- Integration with farmer inventory to auto-match pre-orders

## Testing
1. Open Customer Portal
2. Click "Pre-Order Products"
3. Fill in sample data
4. Submit
5. Check phpMyAdmin to verify data was saved in `pre_orders` table

## Notes
- The table is created automatically when the first pre-order is submitted
- All fields except "Additional Notes" are required
- The customer name is taken from the logged-in customer session
- Status defaults to "Pending"

