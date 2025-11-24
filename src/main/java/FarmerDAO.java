import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmerDAO {

    // Create farmers table
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS farmers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "farmer_id VARCHAR(50) UNIQUE NOT NULL," +
                "farm_size VARCHAR(50)," +
                "certifications VARCHAR(100)," +
                "farm_location VARCHAR(200)," +
                "sustainability_score INT DEFAULT 0," +
                "phone VARCHAR(20)," +
                "email VARCHAR(100)," +
                "status VARCHAR(20) DEFAULT 'Active'," +
                "created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Farmers table created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add new farmer
    public boolean addFarmer(String name, String farmerId, String farmSize,
                            String certifications, String farmLocation,
                            String phone, String email) {
        String sql = "INSERT INTO farmers (name, farmer_id, farm_size, certifications, " +
                    "farm_location, phone, email, sustainability_score) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, farmerId);
            pstmt.setString(3, farmSize);
            pstmt.setString(4, certifications);
            pstmt.setString(5, farmLocation);
            pstmt.setString(6, phone);
            pstmt.setString(7, email);
            pstmt.setInt(8, calculateSustainabilityScore(certifications, farmSize));

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all farmers
    public List<Object[]> getAllFarmers() {
        List<Object[]> farmers = new ArrayList<>();
        String sql = "SELECT id, name, farmer_id, farm_size, certifications, " +
                    "farm_location, sustainability_score, phone, email, status FROM farmers";

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
                    rs.getInt("sustainability_score"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("status")
                };
                farmers.add(farmer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return farmers;
    }

    // Update farmer
    public boolean updateFarmer(int id, String name, String farmerId, String farmSize,
                               String certifications, String farmLocation,
                               String phone, String email) {
        String sql = "UPDATE farmers SET name=?, farmer_id=?, farm_size=?, certifications=?, " +
                    "farm_location=?, phone=?, email=?, sustainability_score=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, farmerId);
            pstmt.setString(3, farmSize);
            pstmt.setString(4, certifications);
            pstmt.setString(5, farmLocation);
            pstmt.setString(6, phone);
            pstmt.setString(7, email);
            pstmt.setInt(8, calculateSustainabilityScore(certifications, farmSize));
            pstmt.setInt(9, id);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete farmer
    public boolean deleteFarmer(int id) {
        String sql = "DELETE FROM farmers WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Calculate sustainability score (simple calculation)
    private int calculateSustainabilityScore(String certifications, String farmSize) {
        int score = 50; // Base score

        // Add points for certifications
        if (certifications != null) {
            if (certifications.toLowerCase().contains("organic")) {
                score += 20;
            }
            if (certifications.toLowerCase().contains("sustainable")) {
                score += 15;
            }
        }

        // Add points based on farm size (smaller farms get more points for sustainability)
        if (farmSize != null) {
            try {
                int size = Integer.parseInt(farmSize.replaceAll("[^0-9]", ""));
                if (size < 30) {
                    score += 15;
                } else if (size < 50) {
                    score += 10;
                } else {
                    score += 5;
                }
            } catch (NumberFormatException e) {
                score += 5;
            }
        }

        return Math.min(score, 100); // Cap at 100
    }
}

