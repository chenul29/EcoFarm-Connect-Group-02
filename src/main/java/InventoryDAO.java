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

    // ========== ADMIN INVENTORY MANAGEMENT ==========

    // Create admin inventory table
    public static void createAdminInventoryTable() {
        String sql = "CREATE TABLE IF NOT EXISTS admin_inventory (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "item_name VARCHAR(200) NOT NULL, " +
                     "category VARCHAR(100) NOT NULL, " +
                     "current_stock INT DEFAULT 0, " +
                     "min_stock INT DEFAULT 0, " +
                     "unit VARCHAR(50) DEFAULT 'pcs', " +
                     "status VARCHAR(50) DEFAULT 'In Stock', " +
                     "price DECIMAL(10,2) DEFAULT 0.00, " +
                     "supplier VARCHAR(200), " +
                     "last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Admin inventory table created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating admin inventory table: " + e.getMessage());
        }
    }

    // Get all inventory items
    public static List<Object[]> getAllInventory() {
        List<Object[]> items = new ArrayList<>();
        String sql = "SELECT id, item_name, category, current_stock, min_stock, unit, status, last_updated FROM admin_inventory ORDER BY item_name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] item = {
                    rs.getInt("id"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getInt("current_stock"),
                    rs.getInt("min_stock"),
                    rs.getString("unit"),
                    rs.getString("status"),
                    rs.getString("last_updated")
                };
                items.add(item);
            }

        } catch (Exception e) {
            System.out.println("Error getting inventory: " + e.getMessage());
        }

        return items;
    }

    // Add new inventory item
    public static boolean addInventoryItem(String itemName, String category, int currentStock,
                                          int minStock, String unit, String supplier, double price) {
        String sql = "INSERT INTO admin_inventory (item_name, category, current_stock, min_stock, unit, supplier, price, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemName);
            stmt.setString(2, category);
            stmt.setInt(3, currentStock);
            stmt.setInt(4, minStock);
            stmt.setString(5, unit);
            stmt.setString(6, supplier);
            stmt.setDouble(7, price);

            // Set status based on stock level
            String status = currentStock <= minStock ? "Low Stock" : "In Stock";
            stmt.setString(8, status);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error adding inventory item: " + e.getMessage());
            return false;
        }
    }

    // Update inventory item
    public static boolean updateInventoryItem(int id, String itemName, String category,
                                             int currentStock, int minStock, String unit,
                                             String supplier, double price) {
        String sql = "UPDATE admin_inventory SET item_name=?, category=?, current_stock=?, " +
                     "min_stock=?, unit=?, supplier=?, price=?, status=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, itemName);
            stmt.setString(2, category);
            stmt.setInt(3, currentStock);
            stmt.setInt(4, minStock);
            stmt.setString(5, unit);
            stmt.setString(6, supplier);
            stmt.setDouble(7, price);

            // Set status based on stock level
            String status = currentStock <= minStock ? "Low Stock" : "In Stock";
            stmt.setString(8, status);
            stmt.setInt(9, id);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error updating inventory item: " + e.getMessage());
            return false;
        }
    }

    // Delete inventory item
    public static boolean deleteInventoryItem(int id) {
        String sql = "DELETE FROM admin_inventory WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error deleting inventory item: " + e.getMessage());
            return false;
        }
    }

    // Get inventory item by ID
    public static Object[] getInventoryItemById(int id) {
        String sql = "SELECT id, item_name, category, current_stock, min_stock, unit, status, price, supplier " +
                     "FROM admin_inventory WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Object[] {
                    rs.getInt("id"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getInt("current_stock"),
                    rs.getInt("min_stock"),
                    rs.getString("unit"),
                    rs.getString("status"),
                    rs.getDouble("price"),
                    rs.getString("supplier")
                };
            }

        } catch (Exception e) {
            System.out.println("Error getting inventory item: " + e.getMessage());
        }

        return null;
    }

    // Update stock only
    public static boolean updateStock(int id, int newStock) {
        String sql = "UPDATE admin_inventory SET current_stock=?, status=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Get min stock to determine status
            int minStock = 0;
            String checkSql = "SELECT min_stock FROM admin_inventory WHERE id=?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    minStock = rs.getInt("min_stock");
                }
            }

            stmt.setInt(1, newStock);
            String status = newStock <= minStock ? "Low Stock" : "In Stock";
            stmt.setString(2, status);
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error updating stock: " + e.getMessage());
            return false;
        }
    }

    // Get low stock items
    public static List<Object[]> getLowStockItems() {
        List<Object[]> items = new ArrayList<>();
        String sql = "SELECT id, item_name, category, current_stock, min_stock, unit " +
                     "FROM admin_inventory WHERE current_stock <= min_stock ORDER BY current_stock ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] item = {
                    rs.getInt("id"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getInt("current_stock"),
                    rs.getInt("min_stock"),
                    rs.getString("unit")
                };
                items.add(item);
            }

        } catch (Exception e) {
            System.out.println("Error getting low stock items: " + e.getMessage());
        }

        return items;
    }
}

