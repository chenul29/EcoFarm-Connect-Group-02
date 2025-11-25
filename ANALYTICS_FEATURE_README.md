# Analytics Dashboard - Sustainability Tracking

## Overview
The Analytics Dashboard allows users to track sustainability metrics and automatically calculates the health status based on input values.

## Features

### Input Fields (3 Required Fields)
1. **Carbon Foot Print** - Enter carbon emission value or text (high/medium/low)
2. **Water Usage** - Enter water usage value in liters or text (high/medium/low)
3. **Soil Health** - Enter soil health score or text (high/medium/low)

### Check Status Button
When clicked, the system:
- Validates all 3 fields are filled
- Calculates the status based on the values
- Stores the data in the table below
- Clears the input fields for next entry
- Shows a success message with the status

### Status Calculation Logic

#### For Numeric Values:
- **Good**: Carbon < 50 AND Water < 1000 AND Soil > 70
- **Problem**: Carbon > 100 OR Water > 2000 OR Soil < 50
- **Normal**: Everything else

#### For Text Values (high/medium/low):
- **Good**: Carbon = "low" AND Water = "low" AND Soil = "high"
- **Problem**: Carbon = "high" OR Water = "high" OR Soil = "low"
- **Normal**: Everything else

### Health Report Table
The table displays all checked records with 4 columns:
1. Carbon Foot Print
2. Water Usage
3. Soil Health
4. Status (Normal/Good/Problem)

## How to Use

### Step 1: Run the Application
```bash
java analytics
```

### Step 2: Enter Data
Fill in all three fields:
```
Carbon Foot Print: 45
Water Usage: 800
Soil Health: 85
```

Or use text values:
```
Carbon Foot Print: low
Water Usage: medium
Soil Health: high
```

### Step 3: Check Status
Click the **"Check Status"** button

### Step 4: View Results
- The data is added to the table
- Status is automatically calculated
- A popup shows the result
- Fields are cleared for next entry

## Examples

### Example 1: Good Status
```
Carbon Foot Print: 30
Water Usage: 500
Soil Health: 90
Status: Good ✓
```

### Example 2: Problem Status
```
Carbon Foot Print: 150
Water Usage: 2500
Soil Health: 40
Status: Problem ⚠
```

### Example 3: Normal Status
```
Carbon Foot Print: 75
Water Usage: 1500
Soil Health: 65
Status: Normal ○
```

### Example 4: Text Input
```
Carbon Foot Print: low
Water Usage: low
Soil Health: high
Status: Good ✓
```

## Files

### Form File
- **Location**: `src/main/java/analytics.form`
- **Type**: NetBeans Form (UI Design)

### Java File
- **Location**: `src/main/java/analytics.java`
- **Type**: Java Swing Application

## Code Structure

### Main Methods

#### `setupTable()`
Initializes the table with proper column names

#### `setupButton()`
Attaches the check status action to the button

#### `checkStatus()`
Main logic:
1. Gets values from 3 text fields
2. Validates inputs
3. Calculates status
4. Adds row to table
5. Clears fields
6. Shows confirmation

#### `calculateStatus(String carbon, String water, String soil)`
Determines the status based on input values:
- Returns "Good", "Problem", or "Normal"
- Handles both numeric and text inputs

## Integration

### How to Open from Admin Dashboard
Add this code where you want to launch analytics:

```java
JButton analyticsBtn = new JButton("Analytics");
analyticsBtn.addActionListener(e -> {
    new analytics().setVisible(true);
});
```

### How to Open from Login
After login, add:

```java
if (userWantsAnalytics) {
    new analytics().setVisible(true);
}
```

## Customization

### Change Status Rules
Edit the `calculateStatus()` method in `analytics.java`:

```java
private String calculateStatus(String carbon, String water, String soil) {
    // Add your custom logic here
    // Example: stricter rules
    if (carbonValue < 30 && waterValue < 500 && soilValue > 80) {
        return "Excellent";
    }
    // ... rest of logic
}
```

### Add More Columns
1. Add more text fields in the form
2. Update `setupTable()` with new column names
3. Update `checkStatus()` to read new fields
4. Update `calculateStatus()` to use new values

### Change Status Labels
Replace "Good", "Problem", "Normal" with:
- "Excellent", "Critical", "Average"
- "✓ Healthy", "⚠ Warning", "○ Moderate"
- Or any custom labels

## Tips

### For Beginners
- Always fill all 3 fields before clicking Check Status
- Use simple numbers for easier calculation
- Check the table to see history of all entries

### For Testing
Try these test cases:
```
Test 1: 20, 300, 95  → Should be Good
Test 2: 120, 2100, 30 → Should be Problem
Test 3: 60, 1200, 60 → Should be Normal
Test 4: low, low, high → Should be Good
```

## Error Handling

### Empty Fields
If any field is empty, error message appears:
```
"Please fill all fields!"
```

### Invalid Input
System handles both:
- Numbers: 45, 800, 85
- Text: low, medium, high

## Benefits

✅ Simple interface for data entry
✅ Automatic status calculation
✅ Historical tracking in table
✅ Supports both numeric and text input
✅ Minimal code, easy to understand
✅ Clear visual feedback
✅ Professional sustainability tracking

## Future Enhancements

Possible additions:
- Save data to database
- Generate PDF reports
- Add charts/graphs
- Export to CSV
- Filter by status
- Date/time stamps
- Delete rows
- Edit existing rows

---

**Created:** November 26, 2025  
**Version:** 1.0  
**Status:** ✅ Complete and Working

