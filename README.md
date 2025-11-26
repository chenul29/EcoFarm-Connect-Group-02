# EcoFarm Connect - Smart Agriculture System

A comprehensive Java Swing-based agriculture management system that connects farmers, administrators, and customers in a unified platform.

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [User Roles](#user-roles)
- [Module Overview](#module-overview)
- [Project Structure](#project-structure)
- [Contributors](#contributors)

## 🌾 Overview

EcoFarm Connect is a Smart Agriculture System designed to streamline farm management, crop tracking, inventory management, marketplace operations, and customer interactions. The system provides separate interfaces for administrators, farmers, and customers, enabling efficient agricultural operations and traceability.

## ✨ Features

### Admin Dashboard
- **Dashboard Summary**: View total farmers, farms, active crops, inventory status, and orders
- **Crop Management**: Add, edit, and monitor crops with soil and weather data integration
- **Farmer Management**: Register and manage farmer profiles, certifications, and farm details
- **Inventory Management**: Track tools, seeds, fertilizers, and other resources
- **Marketplace Management**: Manage product listings, pricing, and orders
- **Reports & Analytics**: Generate visual reports on crop growth, sustainability metrics, and sales
- **Notifications**: Send announcements and alerts to farmers
- **Traceability System**: Track products from planting to delivery with batch IDs and checkpoints
- **Audit Logs**: Monitor system activities and user actions

### Farmer Portal
- **My Crops**: Add new crops, update growth stages, and track harvest predictions
- **My Inventory**: Manage tools, fertilizers, and seed stock levels
- **Notifications**: Receive planting reminders, weather alerts, and resource-sharing updates
- **Sustainability Score**: View carbon reduction, soil health, and water usage metrics
- **Profile Management**: Edit personal details, certifications, and farm information
- **Crop Soil Management**: Monitor soil conditions and water management

### Customer Portal
- **Product Marketplace**: Browse and purchase farm-fresh products
- **Pre-Orders**: Place advance orders for seasonal products
- **Order History**: Track current and past orders
- **Product Details**: View product information, pricing, and availability

### Additional Features
- **Analytics Dashboard**: Soil and water management analysis with weather integration
- **Traceability Tracking**: Complete supply chain visibility from seed to consumer
- **Multi-user Authentication**: Secure login system for admins and farmers
- **Database Integration**: MySQL-based data persistence

## 🛠 Technologies Used

- **Language**: Java (JDK 24)
- **GUI Framework**: Java Swing
- **Database**: MySQL 8.x
- **Build Tool**: Maven
- **JDBC Driver**: MySQL Connector/J 8.2.0
- **IDE**: IntelliJ IDEA Community Edition 2025.1.3

## 📦 Prerequisites

Before running the application, ensure you have:

1. **Java Development Kit (JDK) 24** or higher
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
   
2. **MySQL Server 8.x**
   - Download from [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
   - Or use XAMPP/WAMP with phpMyAdmin

3. **Maven** (usually bundled with IntelliJ IDEA)

4. **MySQL Credentials**:
   - Username: `root`
   - Password: `root`
   - Port: `3306`

## 🚀 Installation

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd ecoapporiginal
   ```

2. **Open in IntelliJ IDEA**
   - Open IntelliJ IDEA
   - Select "Open" and navigate to the project folder
   - Wait for Maven to download dependencies

3. **Verify Dependencies**
   - Check `pom.xml` for MySQL Connector dependency
   - Maven should automatically download required libraries

## 💾 Database Setup

### Option 1: Using phpMyAdmin (XAMPP/WAMP)

1. Start Apache and MySQL in XAMPP/WAMP Control Panel
2. Open phpMyAdmin: `http://localhost/phpmyadmin`
3. Create a new database named `ecofarm`
4. Import the schema files from the `database/` folder:
   - `ecofarm_complete_schema.sql` (recommended for complete setup)
   - Or import individual schema files as needed

### Option 2: Using MySQL Command Line

1. Open MySQL command line or terminal
2. Login to MySQL:
   ```bash
   mysql -u root -p
   ```
3. Create the database:
   ```sql
   CREATE DATABASE IF NOT EXISTS ecofarm;
   USE ecofarm;
   ```
4. Import schema files:
   ```bash
   mysql -u root -p ecofarm < database/ecofarm_complete_schema.sql
   ```

### Required Tables

The application uses the following main tables:
- `users` - User authentication (admin and farmer credentials)
- `farmers` - Farmer profile information
- `crops` - Crop management data
- `farmer_crops` - Farmer-specific crop records
- `inventory` - Inventory management (tools, seeds, fertilizers)
- `notifications` - System notifications
- `products` - Marketplace products
- `pre_orders` - Customer pre-orders
- `traceability` - Product traceability records
- `traceability_checkpoints` - Supply chain checkpoints

### Create Users Table (Manual)

If you need to create the users table manually:
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert default admin user
INSERT INTO users (username, password, role) 
VALUES ('admin', 'admin123', 'admin');

-- Insert sample farmer user
INSERT INTO users (username, password, role) 
VALUES ('farmer1', 'farmer123', 'farmer');
```

## 🎮 Running the Application

### Method 1: Run Main Login Screen
```bash
java -cp target/classes login_signup
```

### Method 2: Run Individual Modules (for testing)

**Admin Dashboard**:
```bash
java -cp target/classes adminDashboard
```

**Farmer Portal**:
```bash
java -cp target/classes farmerPotral
```

**Customer Portal**:
```bash
java -cp target/classes customerPotral
```

**Analytics Dashboard**:
```bash
java -cp target/classes analytics
```

### Method 3: Using IntelliJ IDEA

1. Navigate to `src/main/java/login_signup.java`
2. Right-click and select "Run 'login_signup.main()'"
3. Or use the Run button in the toolbar

## 👥 User Roles

### Admin
- **Default Credentials**:
  - Username: `admin`
  - Password: `admin123`
- **Capabilities**: Full system access including user management, crop oversight, inventory control, marketplace management, and analytics

### Farmer
- **Default Credentials**:
  - Username: `farmer1`
  - Password: `farmer123`
- **Capabilities**: Manage personal crops, inventory, view notifications, track sustainability metrics

### Customer
- **Access**: Through customer portal
- **Capabilities**: Browse marketplace, place orders, view product details

## 📂 Module Overview

### 1. Authentication Module (`login_signup.java`)
- User login and registration
- Role-based access control
- Session management

### 2. Admin Dashboard (`adminDashboard.java`)
- Centralized admin control panel
- Crop management interface
- Farmer management system
- Inventory oversight
- Marketplace administration
- Notification center
- Traceability management
- Reports and analytics

### 3. Farmer Portal (`farmerPotral.java`)
- Personal crop management
- Inventory tracking
- Notification viewer
- Profile management

### 4. Customer Portal (`customerPotral.java`)
- Product browsing
- Order placement
- Pre-order system

### 5. Analytics Dashboard (`analytics.java`)
- Soil health analysis
- Water management tracking
- Weather data integration

### 6. Crop Soil Management (`cropSoilManagement.java`)
- Soil condition monitoring
- Water usage tracking
- Environmental data logging

### 7. Farmer Notifications (`farmerNotification.java`)
- Real-time alerts
- System announcements
- Action reminders

### 8. Traceability System
- Batch tracking
- Supply chain checkpoints
- Product journey visualization

## 📁 Project Structure

```
ecoapporiginal/
├── src/
│   └── main/
│       ├── java/
│       │   ├── login_signup.java          # Main login interface
│       │   ├── adminDashboard.java        # Admin control panel
│       │   ├── farmerPotral.java          # Farmer interface
│       │   ├── customerPotral.java        # Customer interface
│       │   ├── analytics.java             # Analytics dashboard
│       │   ├── cropSoilManagement.java    # Crop & soil tracking
│       │   ├── farmerNotification.java    # Notification system
│       │   ├── DatabaseConnection.java    # DB connection utility
│       │   ├── UserDAO.java               # User data access
│       │   ├── FarmerDAO.java             # Farmer data access
│       │   ├── CropDAO.java               # Crop data access
│       │   ├── FarmerCropDAO.java         # Farmer crop data access
│       │   ├── InventoryDAO.java          # Inventory data access
│       │   ├── NotificationDAO.java       # Notification data access
│       │   ├── FarmerNotificationDAO.java # Farmer notification data access
│       │   ├── MarketplaceDAO.java        # Marketplace data access
│       │   └── CustomerOrderDAO.java      # Order data access
│       └── resources/
│           └── images/                     # UI images and icons
├── database/
│   ├── ecofarm_complete_schema.sql        # Complete database schema
│   ├── crop_management_schema.sql         # Crop management tables
│   ├── farmer_management.sql              # Farmer tables
│   ├── marketplace_setup.sql              # Marketplace tables
│   ├── notifications_schema.sql           # Notification tables
│   ├── traceability_schema.sql            # Traceability tables
│   └── QUICK_START.sql                    # Quick setup script
├── target/                                 # Compiled classes
├── pom.xml                                 # Maven configuration
└── README.md                               # This file
```

## 🗄 Database Configuration

The application uses the following database connection settings:

```java
URL: jdbc:mysql://localhost:3306/ecofarm
Username: root
Password: root
Driver: com.mysql.cj.jdbc.Driver
```

To change database credentials, edit the `DatabaseConnection.java` file.

## 🔧 Common Issues & Solutions

### Issue 1: MySQL JDBC Driver Not Found
**Error**: `No suitable driver found for jdbc:mysql://localhost:3306/`

**Solution**: 
- Ensure MySQL Connector is in `pom.xml`
- Run `mvn clean install` to download dependencies
- Check if the JAR is in your classpath

### Issue 2: Cannot Connect to Database
**Error**: `Access denied for user 'root'@'localhost'`

**Solution**:
- Verify MySQL is running
- Check username/password in `DatabaseConnection.java`
- Ensure database `ecofarm` exists

### Issue 3: Images Not Showing
**Error**: Images not displaying in UI

**Solution**:
- Verify image files are in `src/main/resources/`
- Check image paths in the code
- Ensure images are copied to `target/classes/`

### Issue 4: Different UI When Logging In
**Solution**:
- Ensure proper initialization of UI components
- Check if the correct constructor is being called
- Verify JFrame settings (size, layout, visibility)

## 📊 Features by Module

### Admin Dashboard Features
✅ Dashboard summary cards  
✅ Crop management (add, edit, view)  
✅ Farmer registration and management  
✅ Inventory tracking and management  
✅ Marketplace product management  
✅ Notification broadcasting  
✅ Traceability record creation  
✅ Report generation  
✅ Analytics visualization  

### Farmer Portal Features
✅ Crop addition and tracking  
✅ Inventory management  
✅ Notification viewing  
✅ Profile editing  
✅ Sustainability metrics  
✅ Soil and water monitoring  

### Customer Portal Features
✅ Product browsing  
✅ Order placement  
✅ Pre-order functionality  
✅ Order history  

## 🎯 Future Enhancements

- [ ] Real-time weather API integration
- [ ] Mobile application support
- [ ] Advanced analytics with charts
- [ ] Email notification system
- [ ] Payment gateway integration
- [ ] GPS-based farm location mapping
- [ ] Image upload for crops and products
- [ ] Export reports as PDF/CSV
- [ ] Multi-language support
- [ ] Dark mode theme

## 🤝 Contributors

Chenul Warnasooriya

## 📄 License

This project is created for educational purposes.

## 📞 Support

For issues or questions, please create an issue in the repository or contact the development team.

---

**Note**: This is a Java Swing desktop application designed for learning purposes. The code is kept simple and minimal for beginner-level understanding.

**Last Updated**: November 26, 2025

