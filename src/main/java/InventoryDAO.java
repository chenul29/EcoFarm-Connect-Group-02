import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    // Create tools table
    public static void createToolsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS farmer_tools (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "farmer_id INT NOT NULL, " +
                     "tool_name VARCHAR(100) NOT NULL, " +
                     "condition_status VARCHAR(50) DEFAULT 'Good', " +
                     "last_used DATE, " +
                     "status VARCHAR(50) DEFAULT 'Available', " +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tools table created.");
        } catch (Exception e) {
            System.out.println("Error creating tools table: " + e.getMessage());
        }
    }

    // Create seeds table
    public static void createSeedsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS farmer_seeds (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "farmer_id INT NOT NULL, " +
                     "seed_name VARCHAR(100) NOT NULL, " +
                     "quantity INT DEFAULT 0, " +
                     "unit VARCHAR(20) DEFAULT 'packets', " +
                     "status VARCHAR(50) DEFAULT 'Sufficient', " +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Seeds table created.");
        } catch (Exception e) {
            System.out.println("Error creating seeds table: " + e.getMessage());
        }
    }

    // Get all tools for a farmer
    public static List<Object[]> getAllTools(int farmerId) {
        List<Object[]> tools = new ArrayList<>();
        String sql = "SELECT tool_name, condition_status, last_used, status FROM farmer_tools WHERE farmer_id = ? ORDER BY tool_name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, farmerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] tool = {
                    rs.getString("tool_name"),
                    rs.getString("condition_status"),
                    rs.getString("last_used"),
                    rs.getString("status")
                };
                tools.add(tool);
            }

        } catch (Exception e) {
            System.out.println("Error getting tools: " + e.getMessage());
        }

        return tools;
    }

    // Get all seeds for a farmer
    public static List<Object[]> getAllSeeds(int farmerId) {
        List<Object[]> seeds = new ArrayList<>();
        String sql = "SELECT seed_name, quantity, unit, status FROM farmer_seeds WHERE farmer_id = ? ORDER BY seed_name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, farmerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] seed = {
                    rs.getString("seed_name"),
                    rs.getInt("quantity"),
                    rs.getString("unit"),
                    rs.getString("status")
                };
                seeds.add(seed);
            }

        } catch (Exception e) {
            System.out.println("Error getting seeds: " + e.getMessage());
        }

        return seeds;
    }
}

