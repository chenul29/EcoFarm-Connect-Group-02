import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarketplaceDAO {

    // Create marketplace products table
    public static void createMarketplaceTable() {
        String sql = "CREATE TABLE IF NOT EXISTS marketplace_products (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "product_name VARCHAR(200) NOT NULL, " +
                     "farmer_name VARCHAR(200) NOT NULL, " +
                     "price DECIMAL(10,2) NOT NULL, " +
                     "quantity INT DEFAULT 0, " +
                     "unit VARCHAR(50) DEFAULT 'kg', " +
                     "category VARCHAR(100), " +
                     "status VARCHAR(50) DEFAULT 'Active', " +
                     "approval VARCHAR(50) DEFAULT 'Pending', " +
                     "orders INT DEFAULT 0, " +
                     "description TEXT, " +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Marketplace products table created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating marketplace table: " + e.getMessage());
        }
    }

    // Get all marketplace products
    public static List<Object[]> getAllProducts() {
        List<Object[]> products = new ArrayList<>();
        String sql = "SELECT id, product_name, farmer_name, price, quantity, unit, status, approval, orders " +
                     "FROM marketplace_products ORDER BY created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] product = {
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("farmer_name"),
                    "$" + rs.getDouble("price") + "/" + rs.getString("unit"),
                    rs.getInt("quantity") + " " + rs.getString("unit"),
                    rs.getString("status"),
                    rs.getString("approval"),
                    rs.getInt("orders")
                };
                products.add(product);
            }

        } catch (Exception e) {
            System.out.println("Error getting products: " + e.getMessage());
        }

        return products;
    }

    // Add new product
    public static boolean addProduct(String productName, String farmerName, double price,
                                    int quantity, String unit, String category, String description) {
        String sql = "INSERT INTO marketplace_products (product_name, farmer_name, price, quantity, unit, category, description) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productName);
            stmt.setString(2, farmerName);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setString(5, unit);
            stmt.setString(6, category);
            stmt.setString(7, description);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
            return false;
        }
    }

    // Update product
    public static boolean updateProduct(int id, String productName, String farmerName,
                                       double price, int quantity, String unit, String category) {
        String sql = "UPDATE marketplace_products SET product_name=?, farmer_name=?, price=?, " +
                     "quantity=?, unit=?, category=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productName);
            stmt.setString(2, farmerName);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setString(5, unit);
            stmt.setString(6, category);
            stmt.setInt(7, id);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
            return false;
        }
    }

    // Delete product
    public static boolean deleteProduct(int id) {
        String sql = "DELETE FROM marketplace_products WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }

    // Get product by ID
    public static Object[] getProductById(int id) {
        String sql = "SELECT id, product_name, farmer_name, price, quantity, unit, category, status, approval, description " +
                     "FROM marketplace_products WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Object[] {
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("farmer_name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("unit"),
                    rs.getString("category"),
                    rs.getString("status"),
                    rs.getString("approval"),
                    rs.getString("description")
                };
            }

        } catch (Exception e) {
            System.out.println("Error getting product: " + e.getMessage());
        }

        return null;
    }

    // Approve product
    public static boolean approveProduct(int id) {
        String sql = "UPDATE marketplace_products SET approval='Approved', status='Active' WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error approving product: " + e.getMessage());
            return false;
        }
    }

    // Update price
    public static boolean updatePrice(int id, double newPrice) {
        String sql = "UPDATE marketplace_products SET price=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newPrice);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error updating price: " + e.getMessage());
            return false;
        }
    }
}

