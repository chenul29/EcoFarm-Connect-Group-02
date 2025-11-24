# 🚀 QUICK START - Farmer Management System

## Step-by-Step Setup (5 minutes)

### 1️⃣ Setup Database (2 minutes)

**Open phpMyAdmin:**
- Go to: http://localhost/phpmyadmin
- Login with your credentials

**Create/Select Database:**
- If 'ecofarm' database exists: Click on it
- If not: Click "New" → Name it "ecofarm" → Create

**Run SQL Script:**
1. Click "SQL" tab
2. Copy this entire code:

```sql
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

INSERT INTO farmers (name, farmer_id, farm_size, certifications, farm_location, sustainability_score, phone, email, status) VALUES
('John Farmer', 'F001', '50 acres', 'Organic', 'Green Valley, Region A', 85, '+1234567890', 'john@farm.com', 'Active'),
('Mary Smith', 'F002', '35 acres', 'Sustainable', 'Sunny Acres, Region B', 78, '+1234567891', 'mary@farm.com', 'Active'),
('Bob Johnson', 'F003', '60 acres', 'Organic,Sustainable', 'River Farm, Region C', 92, '+1234567892', 'bob@farm.com', 'Active'),
('Alice Brown', 'F004', '25 acres', 'None', 'Hill Farm, Region A', 65, '+1234567893', 'alice@farm.com', 'Pending'),
('Tom Wilson', 'F005', '40 acres', 'Organic', 'Valley Farm, Region D', 88, '+1234567894', 'tom@farm.com', 'Active');
```

3. Click "Go" button
4. You should see: "5 rows inserted" ✅

---

### 2️⃣ Run the Application (1 minute)

**In IntelliJ IDEA:**
1. Open `login.java`
2. Click the green ▶ Run button
3. Login with admin credentials

**Or run directly:**
1. Open `adminDashboard.java`
2. Click the green ▶ Run button

---

### 3️⃣ Test Farmer Management (2 minutes)

**View Farmers:**
1. Click on "👨‍🌾 Farmer Management" tab
2. You should see 5 farmers in the table ✅

**Add New Farmer:**
1. Click "Add Farmer" button (green)
2. Fill in:
   - Name: Sarah Green
   - Farmer ID: F006
   - Farm Size: 30 acres
   - Certifications: Organic
   - Farm Location: Green Hills, Region F
   - Phone: +1234567896
   - Email: sarah@farm.com
3. Click "Save"
4. See success message ✅
5. Table refreshes automatically ✅

**Edit Farmer:**
1. Click on any farmer row in table
2. Click "Edit Farmer" button (blue)
3. Change any field (e.g., phone number)
4. Click "Update"
5. See changes in table ✅

**Delete Farmer:**
1. Click on a farmer row
2. Click "Delete Farmer" button (red)
3. Confirm deletion
4. Farmer removed from table ✅

**Refresh Data:**
1. Click "Refresh" button (gray)
2. Table reloads from database ✅

---

## 🎯 What You Should See

### Farmer Table Columns:
```
| ID | Name | Farmer ID | Farm Size | Certifications | Farm Location | Score | Phone | Email | Status |
```

### Sample Row:
```
| 1 | John Farmer | F001 | 50 acres | Organic | Green Valley, Region A | 85 | +1234567890 | john@farm.com | Active |
```

---

## ✅ Success Checklist

- [ ] Database created with 5 sample farmers
- [ ] Application runs without errors
- [ ] Farmer table displays all 5 farmers
- [ ] Add Farmer button works
- [ ] Edit Farmer button works
- [ ] Delete Farmer button works
- [ ] Refresh button works
- [ ] Sustainability scores are calculated
- [ ] All data persists in database

---

## 🐛 Troubleshooting

### Problem: "No suitable driver found"
**Solution:** Check that MySQL JDBC driver is in pom.xml:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Problem: "Access denied for user"
**Solution:** Check database credentials in DatabaseConnection.java:
```java
username = "root"
password = "root"
```

### Problem: "Table doesn't exist"
**Solution:** Run the SQL script again in phpMyAdmin

### Problem: "Farmer ID already exists"
**Solution:** Each farmer needs a unique Farmer ID (e.g., F001, F002, F006)

---

## 📊 Sustainability Score Formula

```
Base Score: 50 points

Certifications:
+ Organic: +20 points
+ Sustainable: +15 points

Farm Size:
+ Under 30 acres: +15 points
+ 30-50 acres: +10 points
+ Over 50 acres: +5 points

Maximum: 100 points
```

**Examples:**
- Small organic farm (25 acres, Organic): 50 + 20 + 15 = **85**
- Medium farm (40 acres, Sustainable): 50 + 15 + 10 = **75**
- Large farm (60 acres, Organic): 50 + 20 + 5 = **75**

---

## 🎉 You're Done!

Your Farmer Management System is now fully functional with:
✅ Database integration
✅ CRUD operations
✅ Professional UI
✅ Automatic scoring
✅ Data persistence

**Enjoy your agriculture application!** 🌾🚜

