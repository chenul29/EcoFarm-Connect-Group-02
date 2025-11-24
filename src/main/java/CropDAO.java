import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CropDAO {

    // Add new crop
    public static boolean addCrop(String name, String variety, String plantingDate,
                                  String expectedHarvest, String soilType, String irrigation,
                                  String farmerName, String fieldLocation, String notes) {
        String sql = "INSERT INTO crops (crop_name, variety, planting_date, expected_harvest, " +
                     "soil_type, irrigation_schedule, farmer_name, field_location, notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, variety);
            pstmt.setString(3, plantingDate);
            pstmt.setString(4, expectedHarvest);
            pstmt.setString(5, soilType);
            pstmt.setString(6, irrigation);
            pstmt.setString(7, farmerName);
            pstmt.setString(8, fieldLocation);
            pstmt.setString(9, notes);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all crops
    public static List<Object[]> getAllCrops() {
        List<Object[]> crops = new ArrayList<>();
        String sql = "SELECT id, crop_name, variety, planting_date, expected_harvest, " +
                     "soil_type, irrigation_schedule, growth_stage, health_status, farmer_name " +
                     "FROM crops ORDER BY planting_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("crop_name"),
                    rs.getString("variety"),
                    rs.getString("planting_date"),
                    rs.getString("expected_harvest"),
                    rs.getString("soil_type"),
                    rs.getString("irrigation_schedule"),
                    rs.getString("growth_stage"),
                    rs.getString("health_status"),
                    rs.getString("farmer_name")
                };
                crops.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return crops;
    }

    // Get crop by ID
    public static Object[] getCropById(int id) {
        String sql = "SELECT * FROM crops WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Object[] {
                    rs.getInt("id"),
                    rs.getString("crop_name"),
                    rs.getString("variety"),
                    rs.getString("planting_date"),
                    rs.getString("expected_harvest"),
                    rs.getString("soil_type"),
                    rs.getString("irrigation_schedule"),
                    rs.getString("growth_stage"),
                    rs.getString("health_status"),
                    rs.getString("farmer_name"),
                    rs.getString("field_location"),
                    rs.getString("notes")
                };
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update crop
    public static boolean updateCrop(int id, String name, String variety, String plantingDate,
                                    String expectedHarvest, String soilType, String irrigation,
                                    String growthStage, String healthStatus, String farmerName,
                                    String fieldLocation, String notes) {
        String sql = "UPDATE crops SET crop_name=?, variety=?, planting_date=?, expected_harvest=?, " +
                     "soil_type=?, irrigation_schedule=?, growth_stage=?, health_status=?, " +
                     "farmer_name=?, field_location=?, notes=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, variety);
            pstmt.setString(3, plantingDate);
            pstmt.setString(4, expectedHarvest);
            pstmt.setString(5, soilType);
            pstmt.setString(6, irrigation);
            pstmt.setString(7, growthStage);
            pstmt.setString(8, healthStatus);
            pstmt.setString(9, farmerName);
            pstmt.setString(10, fieldLocation);
            pstmt.setString(11, notes);
            pstmt.setInt(12, id);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete crop
    public static boolean deleteCrop(int id) {
        String sql = "DELETE FROM crops WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Calculate harvest prediction (days until harvest)
    public static int getDaysUntilHarvest(String expectedHarvestDate) {
        try {
            java.sql.Date harvestDate = java.sql.Date.valueOf(expectedHarvestDate);
            java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            long diff = harvestDate.getTime() - today.getTime();
            return (int) (diff / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            return 0;
        }
    }

    // Initialize table
    public static void initializeTable() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String createTable = "CREATE TABLE IF NOT EXISTS crops (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "crop_name VARCHAR(100) NOT NULL, " +
                    "variety VARCHAR(100), " +
                    "planting_date DATE NOT NULL, " +
                    "expected_harvest DATE NOT NULL, " +
                    "soil_type VARCHAR(50), " +
                    "irrigation_schedule VARCHAR(100), " +
                    "growth_stage VARCHAR(50) DEFAULT 'Planted', " +
                    "health_status VARCHAR(50) DEFAULT 'Good', " +
                    "farmer_name VARCHAR(100), " +
                    "field_location VARCHAR(200), " +
                    "notes TEXT, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)";

            stmt.executeUpdate(createTable);
            System.out.println("Crops table created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

