import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDAO {

    // Create customer orders table
    public static void createOrdersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS customer_orders (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "customer_name VARCHAR(200) NOT NULL, " +
                     "product_id INT NOT NULL, " +
                     "product_name VARCHAR(200) NOT NULL, " +
                     "farmer_name VARCHAR(200), " +
                     "quantity INT NOT NULL, " +
                     "unit VARCHAR(50), " +
                     "price DECIMAL(10,2) NOT NULL, " +
                     "total_amount DECIMAL(10,2) NOT NULL, " +
                     "order_status VARCHAR(50) DEFAULT 'Pending', " +
                     "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Customer orders table created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating orders table: " + e.getMessage());
        }
    }

    // Add new order
    public static boolean addOrder(String customerName, int productId, String productName,
                                  String farmerName, int quantity, String unit,
                                  double price, double totalAmount) {
        String sql = "INSERT INTO customer_orders (customer_name, product_id, product_name, " +
                     "farmer_name, quantity, unit, price, total_amount) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerName);
            stmt.setInt(2, productId);
            stmt.setString(3, productName);
            stmt.setString(4, farmerName);
            stmt.setInt(5, quantity);
            stmt.setString(6, unit);
            stmt.setDouble(7, price);
            stmt.setDouble(8, totalAmount);

            int rows = stmt.executeUpdate();

            // Update order count in marketplace_products
            if (rows > 0) {
                updateProductOrderCount(productId);
            }

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error adding order: " + e.getMessage());
            return false;
        }
    }

    // Update product order count
    private static void updateProductOrderCount(int productId) {
        String sql = "UPDATE marketplace_products SET orders = orders + 1 WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error updating order count: " + e.getMessage());
        }
    }

    // Get all orders for a customer
    public static List<Object[]> getOrdersByCustomer(String customerName) {
        List<Object[]> orders = new ArrayList<>();
        String sql = "SELECT id, product_name, farmer_name, quantity, unit, price, " +
                     "total_amount, order_status, order_date " +
                     "FROM customer_orders WHERE customer_name = ? ORDER BY order_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] order = {
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("farmer_name"),
                    rs.getInt("quantity") + " " + rs.getString("unit"),
                    "$" + rs.getDouble("price"),
                    "$" + rs.getDouble("total_amount"),
                    rs.getString("order_status"),
                    rs.getString("order_date")
                };
                orders.add(order);
            }

        } catch (Exception e) {
            System.out.println("Error getting orders: " + e.getMessage());
        }

        return orders;
    }

    // Get all orders (for admin)
    public static List<Object[]> getAllOrders() {
        List<Object[]> orders = new ArrayList<>();
        String sql = "SELECT id, customer_name, product_name, farmer_name, quantity, unit, " +
                     "price, total_amount, order_status, order_date " +
                     "FROM customer_orders ORDER BY order_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] order = {
                    rs.getInt("id"),
                    rs.getString("customer_name"),
                    rs.getString("product_name"),
                    rs.getString("farmer_name"),
                    rs.getInt("quantity") + " " + rs.getString("unit"),
                    "$" + rs.getDouble("price"),
                    "$" + rs.getDouble("total_amount"),
                    rs.getString("order_status"),
                    rs.getString("order_date")
                };
                orders.add(order);
            }

        } catch (Exception e) {
            System.out.println("Error getting all orders: " + e.getMessage());
        }

        return orders;
    }
}

