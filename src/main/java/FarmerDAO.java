import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmerDAO {

    // Create farmers table
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS farmers (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "name VARCHAR(100) NOT NULL, " +
                     "farmer_id VARCHAR(50) UNIQUE NOT NULL, " +
                     "farm_size VARCHAR(50), " +
                     "certifications TEXT, " +
                     "farm_location VARCHAR(200), " +
                     "phone VARCHAR(20), " +
                     "email VARCHAR(100), " +
                     "sustainability_score INT DEFAULT 0, " +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Farmers table created or already exists.");
        } catch (Exception e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // Add new farmer
    public boolean addFarmer(String name, String farmerId, String farmSize,
                            String certifications, String farmLocation, String phone, String email) {
        String sql = "INSERT INTO farmers (name, farmer_id, farm_size, certifications, farm_location, phone, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, farmerId);
            stmt.setString(3, farmSize);
            stmt.setString(4, certifications);
            stmt.setString(5, farmLocation);
            stmt.setString(6, phone);
            stmt.setString(7, email);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error adding farmer: " + e.getMessage());
            return false;
        }
    }

    // Get all farmers
    public List<Object[]> getAllFarmers() {
        List<Object[]> farmers = new ArrayList<>();
        String sql = "SELECT id, name, farmer_id, farm_size, certifications, farm_location, phone, email, sustainability_score " +
                     "FROM farmers ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] farmer = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("farmer_id"),
                    rs.getString("farm_size"),
                    rs.getString("certifications"),
                    rs.getString("farm_location"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getInt("sustainability_score")
                };
                farmers.add(farmer);
            }

        } catch (Exception e) {
            System.out.println("Error getting farmers: " + e.getMessage());
        }

        return farmers;
    }

    // Update farmer
    public boolean updateFarmer(int id, String name, String farmerId, String farmSize,
                               String certifications, String farmLocation, String phone, String email) {
        String sql = "UPDATE farmers SET name=?, farmer_id=?, farm_size=?, certifications=?, " +
                     "farm_location=?, phone=?, email=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, farmerId);
            stmt.setString(3, farmSize);
            stmt.setString(4, certifications);
            stmt.setString(5, farmLocation);
            stmt.setString(6, phone);
            stmt.setString(7, email);
            stmt.setInt(8, id);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error updating farmer: " + e.getMessage());
            return false;
        }
    }

    // Delete farmer
    public boolean deleteFarmer(int id) {
        String sql = "DELETE FROM farmers WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Error deleting farmer: " + e.getMessage());
            return false;
        }
    }
}

