/**
 * Quick Test for Farmer Management System
 *
 * To test:
 * 1. Make sure MySQL is running
 * 2. Make sure 'ecofarm' database exists
 * 3. Run this file to test FarmerDAO
 */
public class TestFarmerManagement {

    public static void main(String[] args) {
        System.out.println("Testing Farmer Management System...\n");

        FarmerDAO farmerDAO = new FarmerDAO();

        // Test 1: Create Table
        System.out.println("Test 1: Creating farmers table...");
        farmerDAO.createTable();
        System.out.println("✓ Table created successfully!\n");

        // Test 2: Get All Farmers
        System.out.println("Test 2: Getting all farmers...");
        var farmers = farmerDAO.getAllFarmers();
        System.out.println("Found " + farmers.size() + " farmers");

        for (Object[] farmer : farmers) {
            System.out.println("- " + farmer[1] + " (ID: " + farmer[2] + ") - Score: " + farmer[6]);
        }
        System.out.println("✓ Retrieved farmers successfully!\n");

        // Test 3: Add New Farmer
        System.out.println("Test 3: Adding new farmer...");
        boolean added = farmerDAO.addFarmer(
            "Test Farmer",
            "F999",
            "45 acres",
            "Organic",
            "Test Valley, Region X",
            "+9999999999",
            "test@farm.com"
        );

        if (added) {
            System.out.println("✓ Farmer added successfully!\n");
        } else {
            System.out.println("✗ Failed to add farmer\n");
        }

        System.out.println("All tests completed!");
        System.out.println("\nNow you can run adminDashboard and test the UI!");
    }
}

