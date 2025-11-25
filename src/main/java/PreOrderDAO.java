import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreOrderDAO {

    // Create pre-orders table
    public static void createPreOrdersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS pre_orders (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "customer_name VARCHAR(200) NOT NULL, " +
                     "product_name VARCHAR(200) NOT NULL, " +
                     "quantity INT NOT NULL, " +
                     "unit VARCHAR(50), " +
                     "expected_delivery_date DATE, " +
                     "contact_number VARCHAR(50), " +
                     "delivery_address TEXT, " +
                     "notes TEXT, " +
                     "status VARCHAR(50) DEFAULT 'Pending', " +
                     "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Pre-orders table created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating pre-orders table: " + e.getMessage());
        }
    }

    // Add new pre-order
    public static boolean addPreOrder(String customerName, String productName,
                                      int quantity, String unit,
                                      String expectedDeliveryDate, String contactNumber,
                                      String deliveryAddress, String notes) {
        String sql = "INSERT INTO pre_orders (customer_name, product_name, quantity, unit, " +
                     "expected_delivery_date, contact_number, delivery_address, notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerName);
            stmt.setString(2, productName);
            stmt.setInt(3, quantity);
            stmt.setString(4, unit);
            stmt.setString(5, expectedDeliveryDate);
            stmt.setString(6, contactNumber);
            stmt.setString(7, deliveryAddress);
            stmt.setString(8, notes);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error adding pre-order: " + e.getMessage());
            return false;
        }
    }

    // Get all pre-orders for a customer
    public static List<Object[]> getPreOrdersByCustomer(String customerName) {
        List<Object[]> preOrders = new ArrayList<>();
        String sql = "SELECT id, product_name, quantity, unit, expected_delivery_date, " +
                     "status, order_date FROM pre_orders WHERE customer_name = ? ORDER BY order_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] order = {
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getInt("quantity") + " " + rs.getString("unit"),
                    rs.getDate("expected_delivery_date"),
                    rs.getString("status"),
                    rs.getTimestamp("order_date")
                };
                preOrders.add(order);
            }

        } catch (Exception e) {
            System.out.println("Error getting pre-orders: " + e.getMessage());
        }

        return preOrders;
    }

    // Get all pre-orders (for admin)
    public static List<Object[]> getAllPreOrders() {
        List<Object[]> preOrders = new ArrayList<>();
        String sql = "SELECT * FROM pre_orders ORDER BY order_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] order = {
                    rs.getInt("id"),
                    rs.getString("customer_name"),
                    rs.getString("product_name"),
                    rs.getInt("quantity") + " " + rs.getString("unit"),
                    rs.getDate("expected_delivery_date"),
                    rs.getString("contact_number"),
                    rs.getString("status")
                };
                preOrders.add(order);
            }

        } catch (Exception e) {
            System.out.println("Error getting all pre-orders: " + e.getMessage());
        }

        return preOrders;
    }
}

