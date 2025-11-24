# 📐 Farmer Management System - Architecture

## System Flow Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         USER INTERFACE                          │
│                      (adminDashboard.java)                      │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │          👨‍🌾 Farmer Management Tab                        │  │
│  ├──────────────────────────────────────────────────────────┤  │
│  │                                                           │  │
│  │  [Add Farmer] [Edit Farmer] [Delete Farmer] [Refresh]   │  │
│  │                                                           │  │
│  │  ┌─────────────────────────────────────────────────────┐ │  │
│  │  │  Farmer Table (JTable)                              │ │  │
│  │  │  ┌───┬──────────┬────────┬──────────┬─────────────┐ │ │  │
│  │  │  │ID │ Name     │Farm ID │Farm Size │Score        │ │ │  │
│  │  │  ├───┼──────────┼────────┼──────────┼─────────────┤ │ │  │
│  │  │  │ 1 │John F.   │ F001   │50 acres  │ 85          │ │ │  │
│  │  │  │ 2 │Mary S.   │ F002   │35 acres  │ 78          │ │ │  │
│  │  │  └───┴──────────┴────────┴──────────┴─────────────┘ │ │  │
│  │  └─────────────────────────────────────────────────────┘ │  │
│  └──────────────────────────────────────────────────────────┘  │
│                                                                 │
│                           ↕ (User Actions)                      │
│                                                                 │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │              Dialog Forms (Add/Edit)                      │  │
│  ├──────────────────────────────────────────────────────────┤  │
│  │  Name:              [_____________________]              │  │
│  │  Farmer ID:         [_____________________]              │  │
│  │  Farm Size:         [_____________________]              │  │
│  │  Certifications:    [_____________________]              │  │
│  │  Farm Location:     [_____________________]              │  │
│  │  Phone:             [_____________________]              │  │
│  │  Email:             [_____________________]              │  │
│  │                                                           │  │
│  │               [Save]  [Cancel]                            │  │
│  └──────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
                              ↕
┌─────────────────────────────────────────────────────────────────┐
│                      DATA ACCESS LAYER                          │
│                       (FarmerDAO.java)                          │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  • createTable()               → Create farmers table           │
│  • addFarmer(...)             → INSERT new farmer               │
│  • getAllFarmers()            → SELECT all farmers              │
│  • updateFarmer(...)          → UPDATE farmer data              │
│  • deleteFarmer(id)           → DELETE farmer                   │
│  • calculateSustainabilityScore() → Calculate score             │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
                              ↕
┌─────────────────────────────────────────────────────────────────┐
│                    DATABASE CONNECTION                          │
│                  (DatabaseConnection.java)                      │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  URL:      jdbc:mysql://localhost:3306/ecofarm                 │
│  Username: root                                                 │
│  Password: root                                                 │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
                              ↕
┌─────────────────────────────────────────────────────────────────┐
│                      MySQL DATABASE                             │
│                   (localhost:3306/ecofarm)                      │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  Table: farmers                                                 │
│  ┌───────────────────────┬─────────────────┬──────────────────┐ │
│  │ Field                 │ Type            │ Details          │ │
│  ├───────────────────────┼─────────────────┼──────────────────┤ │
│  │ id                    │ INT             │ PK, Auto Inc     │ │
│  │ name                  │ VARCHAR(100)    │ NOT NULL         │ │
│  │ farmer_id             │ VARCHAR(50)     │ UNIQUE           │ │
│  │ farm_size             │ VARCHAR(50)     │                  │ │
│  │ certifications        │ VARCHAR(100)    │                  │ │
│  │ farm_location         │ VARCHAR(200)    │                  │ │
│  │ sustainability_score  │ INT             │ Calculated       │ │
│  │ phone                 │ VARCHAR(20)     │                  │ │
│  │ email                 │ VARCHAR(100)    │                  │ │
│  │ status                │ VARCHAR(20)     │ Default: Active  │ │
│  │ created_date          │ TIMESTAMP       │ Auto set         │ │
│  └───────────────────────┴─────────────────┴──────────────────┘ │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

## Data Flow Examples

### 1. Adding a New Farmer

```
User Action:
  Click "Add Farmer" button
       ↓
UI Layer:
  Show Add Farmer Dialog
  User fills form & clicks "Save"
       ↓
Validation:
  Check if Name & Farmer ID are filled
       ↓
DAO Layer:
  farmerDAO.addFarmer(name, farmerId, farmSize, ...)
       ↓
Calculate Score:
  calculateSustainabilityScore(certifications, farmSize)
  → Score = 50 + cert_bonus + size_bonus
       ↓
Database:
  INSERT INTO farmers (name, farmer_id, ..., sustainability_score)
  VALUES ('Sarah Green', 'F006', ..., 85)
       ↓
Response:
  Return true (success)
       ↓
UI Update:
  Show success message
  Reload table data
  Close dialog
```

### 2. Loading Farmer Data

```
User Action:
  Open Farmer Management Tab
  OR Click "Refresh" button
       ↓
DAO Layer:
  farmerDAO.getAllFarmers()
       ↓
Database:
  SELECT id, name, farmer_id, farm_size, certifications,
         farm_location, sustainability_score, phone, email, status
  FROM farmers
       ↓
Result:
  List<Object[]> with all farmer records
       ↓
UI Update:
  Convert to table model
  Display in JTable
```

### 3. Editing a Farmer

```
User Action:
  Select farmer row
  Click "Edit Farmer" button
       ↓
UI Layer:
  Get selected farmer data from table
  Show Edit Dialog with pre-filled fields
  User modifies & clicks "Update"
       ↓
Validation:
  Check required fields
       ↓
DAO Layer:
  farmerDAO.updateFarmer(id, name, farmerId, ...)
       ↓
Calculate Score:
  Recalculate sustainability_score
       ↓
Database:
  UPDATE farmers
  SET name=?, farmer_id=?, farm_size=?, ..., sustainability_score=?
  WHERE id=?
       ↓
Response:
  Return true (success)
       ↓
UI Update:
  Show success message
  Reload table data
  Close dialog
```

### 4. Deleting a Farmer

```
User Action:
  Select farmer row
  Click "Delete Farmer" button
       ↓
UI Layer:
  Show confirmation dialog
  User confirms
       ↓
DAO Layer:
  farmerDAO.deleteFarmer(id)
       ↓
Database:
  DELETE FROM farmers WHERE id=?
       ↓
Response:
  Return true (success)
       ↓
UI Update:
  Show success message
  Reload table data
```

## File Structure

```
ecoapporiginal/
│
├── src/main/java/
│   ├── adminDashboard.java          ← UI (Farmer Management Tab)
│   ├── FarmerDAO.java                ← Database operations
│   ├── DatabaseConnection.java       ← Database connection
│   └── TestFarmerManagement.java     ← Test file
│
├── database/
│   └── farmer_management.sql         ← SQL script
│
└── docs/
    ├── FARMER_MANAGEMENT_README.md   ← Full documentation
    └── QUICK_START_FARMER_MANAGEMENT.md  ← Quick setup guide
```

## Class Relationships

```
adminDashboard
    │
    ├── Uses: FarmerDAO (instance variable)
    │   └── Methods:
    │       ├── createTable()
    │       ├── addFarmer()
    │       ├── getAllFarmers()
    │       ├── updateFarmer()
    │       └── deleteFarmer()
    │
    ├── UI Components:
    │   ├── farmerTable (JTable)
    │   └── Action Buttons:
    │       ├── Add Farmer → showAddFarmerDialog()
    │       ├── Edit Farmer → showEditFarmerDialog()
    │       ├── Delete Farmer → deleteFarmer()
    │       └── Refresh → loadFarmersData()
    │
    └── Helper Methods:
        ├── loadFarmersData()
        ├── showAddFarmerDialog()
        ├── showEditFarmerDialog()
        └── deleteFarmer()

FarmerDAO
    │
    └── Uses: DatabaseConnection
        └── getConnection()
```

## Sustainability Score Calculation Logic

```
Function: calculateSustainabilityScore(certifications, farmSize)

Step 1: Base Score
  score = 50

Step 2: Check Certifications
  IF certifications contains "Organic":
    score = score + 20
  
  IF certifications contains "Sustainable":
    score = score + 15

Step 3: Extract Farm Size (in acres)
  Extract numbers from farmSize string
  Example: "50 acres" → 50

Step 4: Size Bonus
  IF size < 30:
    score = score + 15
  ELSE IF size < 50:
    score = score + 10
  ELSE:
    score = score + 5

Step 5: Cap Maximum
  IF score > 100:
    score = 100

Return: score
```

## Button Color Coding

```
🟢 Green (#2ECC71)  - Add/Create actions
🔵 Blue (#3498DB)   - Edit/Update actions
🔴 Red (#E74C3C)    - Delete/Remove actions
⚪ Gray (#95A5A6)   - Refresh/Neutral actions
```

This helps users quickly understand what each button does!

