import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmerCropDAO {

    // Add new crop to database
    public static boolean addCrop(int farmerId, String cropName, String plantingDate,
                                   String growthStage, String expectedHarvest, String status) {
        String sql = "INSERT INTO farmer_crops (farmer_id, crop_name, planting_date, growth_stage, expected_harvest, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, farmerId);
            stmt.setString(2, cropName);
            stmt.setString(3, plantingDate);
            stmt.setString(4, growthStage);
            stmt.setString(5, expectedHarvest);
            stmt.setString(6, status);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error adding crop: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Get all crops for a farmer
    public static List<Object[]> getAllCrops(int farmerId) {
        List<Object[]> crops = new ArrayList<>();
        String sql = "SELECT crop_name, planting_date, growth_stage, expected_harvest, status FROM farmer_crops WHERE farmer_id = ? ORDER BY planting_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, farmerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] crop = {
                    rs.getString("crop_name"),
                    rs.getString("planting_date"),
                    rs.getString("growth_stage"),
                    rs.getString("expected_harvest"),
                    rs.getString("status")
                };
                crops.add(crop);
            }

        } catch (Exception e) {
            System.out.println("Error getting crops: " + e.getMessage());
            e.printStackTrace();
        }

        return crops;
    }

    // Update growth stage
    public static boolean updateGrowthStage(int farmerId, String cropName, String newStage) {
        String sql = "UPDATE farmer_crops SET growth_stage = ? WHERE farmer_id = ? AND crop_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStage);
            stmt.setInt(2, farmerId);
            stmt.setString(3, cropName);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error updating growth stage: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Update complete crop details
    public static boolean updateCrop(int farmerId, String cropName, String plantingDate,
                                     String growthStage, String expectedHarvest, String status) {
        String sql = "UPDATE farmer_crops SET planting_date = ?, growth_stage = ?, " +
                     "expected_harvest = ?, status = ? WHERE farmer_id = ? AND crop_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plantingDate);
            stmt.setString(2, growthStage);
            stmt.setString(3, expectedHarvest);
            stmt.setString(4, status);
            stmt.setInt(5, farmerId);
            stmt.setString(6, cropName);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error updating crop: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

