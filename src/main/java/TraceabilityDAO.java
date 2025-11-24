import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TraceabilityDAO {

    // Create a new traceability record
    public static boolean createRecord(String batchId, String cropName, String farmerName, String date) {
        String sql = "INSERT INTO traceability_records (batch_id, crop_name, farmer_name, created_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, batchId);
            pstmt.setString(2, cropName);
            pstmt.setString(3, farmerName);
            pstmt.setString(4, date);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add checkpoint to a batch
    public static boolean addCheckpoint(String batchId, String checkpointName, String date, String location, String notes) {
        String sql = "INSERT INTO traceability_checkpoints (batch_id, checkpoint_name, checkpoint_date, location, notes) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, batchId);
            pstmt.setString(2, checkpointName);
            pstmt.setString(3, date);
            pstmt.setString(4, location);
            pstmt.setString(5, notes);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all traceability records
    public static List<Object[]> getAllRecords() {
        List<Object[]> records = new ArrayList<>();
        String sql = "SELECT batch_id, crop_name, farmer_name, created_date, status FROM traceability_records ORDER BY created_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getString("batch_id"),
                    rs.getString("crop_name"),
                    rs.getString("farmer_name"),
                    rs.getString("created_date"),
                    rs.getString("status")
                };
                records.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    // Get checkpoints for a specific batch
    public static List<Object[]> getCheckpoints(String batchId) {
        List<Object[]> checkpoints = new ArrayList<>();
        String sql = "SELECT checkpoint_name, checkpoint_date, location, notes FROM traceability_checkpoints WHERE batch_id = ? ORDER BY checkpoint_date ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, batchId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getString("checkpoint_name"),
                    rs.getString("checkpoint_date"),
                    rs.getString("location"),
                    rs.getString("notes")
                };
                checkpoints.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return checkpoints;
    }

}

