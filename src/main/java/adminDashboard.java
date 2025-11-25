import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Admin Dashboard - EcoFarm Connect
 * Smart Agriculture System
 */
public class adminDashboard extends JFrame {

    private JTabbedPane tabbedPane;
    private FarmerDAO farmerDAO;

    public adminDashboard(String adminName) {
        // Initialize DAO
        farmerDAO = new FarmerDAO();
        // Set window properties
        setTitle("EcoFarm Connect - Admin Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        createUI(adminName);

        // Pack and display
        pack();
        setSize(1200, 800); // Restore size after pack
        setLocationRelativeTo(null); // Re-center after pack
        setVisible(true);
    }

    private void createUI(String adminName) {
        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Header
        mainPanel.add(createHeader(adminName), BorderLayout.NORTH);

        // Tabbed Content
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add all tabs
        tabbedPane.addTab("📊 Dashboard", createDashboardPanel());
        tabbedPane.addTab("🌾 Crop Management", createCropManagementPanel());
        tabbedPane.addTab("👨‍🌾 Farmer Management", createFarmerManagementPanel());
        tabbedPane.addTab("📦 Inventory", createInventoryPanel());
        tabbedPane.addTab("🛒 Marketplace", createMarketplacePanel());
        tabbedPane.addTab("📈 Reports & Analytics", createReportsPanel());
        tabbedPane.addTab("🔔 Notifications", createNotificationsPanel());
        tabbedPane.addTab("🔍 Traceability", createTraceabilityPanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    // ========== HEADER ==========
    private JPanel createHeader(String adminName) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(34, 139, 34));
        header.setPreferredSize(new Dimension(1200, 80));
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Left - Title
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.setOpaque(false);

        JLabel title = new JLabel("🌾 EcoFarm Connect - Admin Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JLabel welcome = new JLabel("Welcome, " + adminName);
        welcome.setFont(new Font("Arial", Font.PLAIN, 14));
        welcome.setForeground(Color.WHITE);

        leftPanel.add(title);
        leftPanel.add(welcome);

        // Right - Logout
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(220, 53, 69));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 14));
        logoutBtn.setOpaque(true);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutBtn.setPreferredSize(new Dimension(100, 40));

        // Add logout action
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                SwingUtilities.invokeLater(() -> login.main(new String[]{}));
            }
        });

        header.add(leftPanel, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        return header;
    }

    // ========== DASHBOARD PANEL ==========
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Summary Cards
        JPanel cardsPanel = new JPanel(new GridLayout(2, 4, 15, 15));
        cardsPanel.setBackground(Color.WHITE);

        cardsPanel.add(createStatCard("Total Farmers", "245", new Color(52, 152, 219)));
        cardsPanel.add(createStatCard("Total Farms", "189", new Color(46, 204, 113)));
        cardsPanel.add(createStatCard("Active Crops", "1,234", new Color(155, 89, 182)));
        cardsPanel.add(createStatCard("Low Stock Items", "12", new Color(231, 76, 60)));
        cardsPanel.add(createStatCard("Orders Today", "45", new Color(241, 196, 15)));
        cardsPanel.add(createStatCard("Active Bookings", "8", new Color(52, 73, 94)));
        cardsPanel.add(createStatCard("Pending Approvals", "23", new Color(230, 126, 34)));
        cardsPanel.add(createStatCard("Notifications", "56", new Color(26, 188, 156)));

        // Quick Actions
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Quick Actions"));

        JButton addFarmerBtn = createActionButton("➕ Add Farmer", new Color(52, 152, 219));
        JButton addFarmBtn = createActionButton("🏠 Add Farm", new Color(46, 204, 113));
        JButton addCropBtn = createActionButton("🌱 Add Crop", new Color(155, 89, 182));
        JButton addInventoryBtn = createActionButton("📦 Add Inventory", new Color(241, 196, 15));
        JButton announcementBtn = createActionButton("📢 Announcement", new Color(230, 126, 34));
        JButton generateReportBtn = createActionButton("📊 Generate Report", new Color(52, 73, 94));

        // Add button actions
        addFarmerBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Add Farmer functionality - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
            tabbedPane.setSelectedIndex(2); // Switch to Farmer Management tab
        });

        addFarmBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Add Farm functionality - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        addCropBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Add Crop functionality - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
            tabbedPane.setSelectedIndex(1); // Switch to Crop Management tab
        });

        addInventoryBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Add Inventory functionality - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
            tabbedPane.setSelectedIndex(3); // Switch to Inventory tab
        });

        announcementBtn.addActionListener(e -> {
            tabbedPane.setSelectedIndex(6); // Switch to Notifications tab
            showSendNotificationDialog("BROADCAST");
        });

        generateReportBtn.addActionListener(e -> {
            tabbedPane.setSelectedIndex(5); // Switch to Reports tab
        });

        actionsPanel.add(addFarmerBtn);
        actionsPanel.add(addFarmBtn);
        actionsPanel.add(addCropBtn);
        actionsPanel.add(addInventoryBtn);
        actionsPanel.add(announcementBtn);
        actionsPanel.add(generateReportBtn);

        JPanel topSection = new JPanel(new BorderLayout(10, 10));
        topSection.setBackground(Color.WHITE);
        topSection.add(cardsPanel, BorderLayout.CENTER);
        topSection.add(actionsPanel, BorderLayout.SOUTH);

        panel.add(topSection, BorderLayout.NORTH);
        panel.add(createRecentActivityTable(), BorderLayout.CENTER);

        return panel;
    }

    // ========== CROP MANAGEMENT ==========
    private JTable cropTable;

    private JPanel createCropManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("🌾 Crop Management System");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton addCropBtn = createActionButton("➕ Add Crop", new Color(46, 204, 113));
        JButton viewEditBtn = createActionButton("✏️ View/Edit", new Color(52, 152, 219));
        JButton deleteCropBtn = createActionButton("🗑️ Delete", new Color(220, 53, 69));
        JButton harvestPredictBtn = createActionButton("📅 Harvest Prediction", new Color(155, 89, 182));
        JButton refreshBtn = createActionButton("🔄 Refresh", new Color(108, 117, 125));

        // Button Actions
        addCropBtn.addActionListener(e -> showAddCropDialog());
        viewEditBtn.addActionListener(e -> showViewEditCropDialog());
        deleteCropBtn.addActionListener(e -> deleteCrop());
        harvestPredictBtn.addActionListener(e -> showHarvestPrediction());
        refreshBtn.addActionListener(e -> loadCrops());

        buttonPanel.add(addCropBtn);
        buttonPanel.add(viewEditBtn);
        buttonPanel.add(deleteCropBtn);
        buttonPanel.add(harvestPredictBtn);
        buttonPanel.add(refreshBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        // Crop List Table
        String[] columns = {"ID", "Crop Name", "Variety", "Planted", "Harvest Date",
                           "Soil Type", "Irrigation", "Stage", "Health", "Farmer"};
        cropTable = new JTable(new Object[0][0], columns);
        cropTable.setRowHeight(30);
        cropTable.setFont(new Font("Arial", Font.PLAIN, 13));
        cropTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(cropTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Crop List"));

        // Load data
        loadCrops();

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Load crops from database
    private void loadCrops() {
        List<Object[]> crops = CropDAO.getAllCrops();
        String[] columns = {"ID", "Crop Name", "Variety", "Planted", "Harvest Date",
                           "Soil Type", "Irrigation", "Stage", "Health", "Farmer"};
        Object[][] data = crops.toArray(new Object[0][0]);
        cropTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Show Add Crop Dialog
    private void showAddCropDialog() {
        JDialog dialog = new JDialog(this, "Add New Crop", true);
        dialog.setSize(500, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Crop Name
        formPanel.add(new JLabel("Crop Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        // Variety
        formPanel.add(new JLabel("Variety:"));
        JTextField varietyField = new JTextField();
        formPanel.add(varietyField);

        // Planting Date
        formPanel.add(new JLabel("Planting Date (YYYY-MM-DD):"));
        JTextField plantingDateField = new JTextField("2025-11-24");
        formPanel.add(plantingDateField);

        // Expected Harvest
        formPanel.add(new JLabel("Expected Harvest (YYYY-MM-DD):"));
        JTextField harvestDateField = new JTextField("2026-02-24");
        formPanel.add(harvestDateField);

        // Soil Type
        formPanel.add(new JLabel("Soil Type:"));
        String[] soilTypes = {"Loamy", "Sandy", "Clay", "Sandy Loam", "Silty"};
        JComboBox<String> soilCombo = new JComboBox<>(soilTypes);
        formPanel.add(soilCombo);

        // Irrigation Schedule
        formPanel.add(new JLabel("Irrigation Schedule:"));
        String[] irrigation = {"Daily", "Twice Weekly", "Weekly", "Flooded Daily", "Drip System"};
        JComboBox<String> irrigationCombo = new JComboBox<>(irrigation);
        formPanel.add(irrigationCombo);

        // Farmer Name
        formPanel.add(new JLabel("Farmer Name:"));
        JTextField farmerField = new JTextField();
        formPanel.add(farmerField);

        // Field Location
        formPanel.add(new JLabel("Field Location:"));
        JTextField locationField = new JTextField();
        formPanel.add(locationField);

        // Notes
        formPanel.add(new JLabel("Notes:"));
        JTextField notesField = new JTextField();
        formPanel.add(notesField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveBtn = new JButton("Save Crop");
        saveBtn.setBackground(new Color(46, 204, 113));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        saveBtn.addActionListener(e -> {
            String name = nameField.getText();
            String variety = varietyField.getText();
            String plantingDate = plantingDateField.getText();
            String harvestDate = harvestDateField.getText();
            String soilType = (String) soilCombo.getSelectedItem();
            String irrigationSch = (String) irrigationCombo.getSelectedItem();
            String farmer = farmerField.getText();
            String location = locationField.getText();
            String notes = notesField.getText();

            if (name.isEmpty() || plantingDate.isEmpty() || harvestDate.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = CropDAO.addCrop(name, variety, plantingDate, harvestDate,
                                             soilType, irrigationSch, farmer, location, notes);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "Crop added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadCrops();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to add crop!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Show View/Edit Crop Dialog
    private void showViewEditCropDialog() {
        int selectedRow = cropTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a crop to view/edit!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int cropId = (int) cropTable.getValueAt(selectedRow, 0);
        Object[] crop = CropDAO.getCropById(cropId);

        if (crop == null) {
            JOptionPane.showMessageDialog(this, "Crop not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "View/Edit Crop", true);
        dialog.setSize(550, 700);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Crop Name
        formPanel.add(new JLabel("Crop Name:"));
        JTextField nameField = new JTextField((String) crop[1]);
        formPanel.add(nameField);

        // Variety
        formPanel.add(new JLabel("Variety:"));
        JTextField varietyField = new JTextField((String) crop[2]);
        formPanel.add(varietyField);

        // Planting Date
        formPanel.add(new JLabel("Planting Date:"));
        JTextField plantingDateField = new JTextField((String) crop[3]);
        formPanel.add(plantingDateField);

        // Expected Harvest
        formPanel.add(new JLabel("Expected Harvest:"));
        JTextField harvestDateField = new JTextField((String) crop[4]);
        formPanel.add(harvestDateField);

        // Soil Type
        formPanel.add(new JLabel("Soil Type:"));
        String[] soilTypes = {"Loamy", "Sandy", "Clay", "Sandy Loam", "Silty"};
        JComboBox<String> soilCombo = new JComboBox<>(soilTypes);
        soilCombo.setSelectedItem(crop[5]);
        formPanel.add(soilCombo);

        // Irrigation
        formPanel.add(new JLabel("Irrigation:"));
        JTextField irrigationField = new JTextField((String) crop[6]);
        formPanel.add(irrigationField);

        // Growth Stage
        formPanel.add(new JLabel("Growth Stage:"));
        String[] stages = {"Planted", "Growing", "Flowering", "Harvesting", "Completed"};
        JComboBox<String> stageCombo = new JComboBox<>(stages);
        stageCombo.setSelectedItem(crop[7]);
        formPanel.add(stageCombo);

        // Health Status
        formPanel.add(new JLabel("Health Status:"));
        String[] health = {"Excellent", "Good", "Fair", "Poor", "Critical"};
        JComboBox<String> healthCombo = new JComboBox<>(health);
        healthCombo.setSelectedItem(crop[8]);
        formPanel.add(healthCombo);

        // Farmer Name
        formPanel.add(new JLabel("Farmer Name:"));
        JTextField farmerField = new JTextField((String) crop[9]);
        formPanel.add(farmerField);

        // Field Location
        formPanel.add(new JLabel("Field Location:"));
        JTextField locationField = new JTextField((String) crop[10]);
        formPanel.add(locationField);

        // Notes
        formPanel.add(new JLabel("Notes:"));
        JTextField notesField = new JTextField((String) crop[11]);
        formPanel.add(notesField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton updateBtn = new JButton("Update Crop");
        updateBtn.setBackground(new Color(52, 152, 219));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        updateBtn.addActionListener(e -> {
            boolean success = CropDAO.updateCrop(
                cropId,
                nameField.getText(),
                varietyField.getText(),
                plantingDateField.getText(),
                harvestDateField.getText(),
                (String) soilCombo.getSelectedItem(),
                irrigationField.getText(),
                (String) stageCombo.getSelectedItem(),
                (String) healthCombo.getSelectedItem(),
                farmerField.getText(),
                locationField.getText(),
                notesField.getText()
            );

            if (success) {
                JOptionPane.showMessageDialog(dialog, "Crop updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadCrops();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to update crop!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(updateBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Delete crop
    private void deleteCrop() {
        int selectedRow = cropTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a crop to delete!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this crop?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int cropId = (int) cropTable.getValueAt(selectedRow, 0);
            boolean success = CropDAO.deleteCrop(cropId);

            if (success) {
                JOptionPane.showMessageDialog(this, "Crop deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadCrops();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete crop!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Show harvest prediction
    private void showHarvestPrediction() {
        List<Object[]> crops = CropDAO.getAllCrops();

        if (crops.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No crops found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Harvest Prediction", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("📅 Harvest Prediction for All Crops");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table
        String[] columns = {"Crop", "Variety", "Harvest Date", "Days Until Harvest", "Status"};
        List<Object[]> predictions = new ArrayList<>();

        for (Object[] crop : crops) {
            String harvestDate = (String) crop[4];
            int daysUntil = CropDAO.getDaysUntilHarvest(harvestDate);
            String status;

            if (daysUntil < 0) {
                status = "⚠️ Overdue";
            } else if (daysUntil == 0) {
                status = "✅ Today!";
            } else if (daysUntil <= 7) {
                status = "🔔 This Week";
            } else if (daysUntil <= 30) {
                status = "📅 This Month";
            } else {
                status = "⏰ Future";
            }

            predictions.add(new Object[]{
                crop[1], // crop name
                crop[2], // variety
                harvestDate,
                daysUntil + " days",
                status
            });
        }

        Object[][] data = predictions.toArray(new Object[0][0]);
        JTable predictionTable = new JTable(data, columns);
        predictionTable.setRowHeight(30);
        predictionTable.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(predictionTable);

        // Close button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(108, 117, 125));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeBtn);

        dialog.add(title, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // ========== FARMER MANAGEMENT ==========
    private JTable farmerTable;

    private JPanel createFarmerManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize database table
        farmerDAO.createTable();

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("👨‍🌾 Farmer Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton addFarmerBtn = createActionButton("Add Farmer", new Color(46, 204, 113));
        JButton editFarmerBtn = createActionButton("Edit Farmer", new Color(52, 152, 219));
        JButton deleteFarmerBtn = createActionButton("Delete Farmer", new Color(231, 76, 60));
        JButton refreshBtn = createActionButton("Refresh", new Color(149, 165, 166));

        // Add button actions
        addFarmerBtn.addActionListener(e -> showAddFarmerDialog());
        editFarmerBtn.addActionListener(e -> showEditFarmerDialog());
        deleteFarmerBtn.addActionListener(e -> deleteFarmer());
        refreshBtn.addActionListener(e -> loadFarmersData());

        buttonPanel.add(addFarmerBtn);
        buttonPanel.add(editFarmerBtn);
        buttonPanel.add(deleteFarmerBtn);
        buttonPanel.add(refreshBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"ID", "Name", "Farmer ID", "Farm Size", "Certifications",
                           "Farm Location", "Score", "Phone", "Email", "Status"};

        farmerTable = new JTable();
        farmerTable.setRowHeight(30);
        farmerTable.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(farmerTable);

        // Load data from database
        loadFarmersData();

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== INVENTORY ==========
    private JTable inventoryTable;

    private JPanel createInventoryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize database table
        InventoryDAO.createAdminInventoryTable();

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("📦 Inventory Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton addItemBtn = createActionButton("Add Item", new Color(46, 204, 113));
        JButton editItemBtn = createActionButton("Edit Item", new Color(52, 152, 219));
        JButton deleteItemBtn = createActionButton("Delete Item", new Color(231, 76, 60));
        JButton updateStockBtn = createActionButton("Update Stock", new Color(155, 89, 182));
        JButton lowStockBtn = createActionButton("Low Stock Alert", new Color(241, 196, 15));
        JButton refreshBtn = createActionButton("🔄 Refresh", new Color(108, 117, 125));

        // Add button actions
        addItemBtn.addActionListener(e -> showAddInventoryDialog());
        editItemBtn.addActionListener(e -> showEditInventoryDialog());
        deleteItemBtn.addActionListener(e -> deleteInventoryItem());
        updateStockBtn.addActionListener(e -> showUpdateStockDialog());
        lowStockBtn.addActionListener(e -> showLowStockAlert());
        refreshBtn.addActionListener(e -> loadInventoryData());

        buttonPanel.add(addItemBtn);
        buttonPanel.add(editItemBtn);
        buttonPanel.add(deleteItemBtn);
        buttonPanel.add(updateStockBtn);
        buttonPanel.add(lowStockBtn);
        buttonPanel.add(refreshBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"ID", "Item Name", "Category", "Current Stock", "Min Stock", "Unit", "Status", "Last Updated"};
        inventoryTable = new JTable();
        inventoryTable.setRowHeight(30);
        inventoryTable.setFont(new Font("Arial", Font.PLAIN, 13));
        inventoryTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        // Load data from database
        loadInventoryData();

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Load inventory data from database
    private void loadInventoryData() {
        List<Object[]> items = InventoryDAO.getAllInventory();
        String[] columns = {"ID", "Item Name", "Category", "Current Stock", "Min Stock", "Unit", "Status", "Last Updated"};
        Object[][] data = items.toArray(new Object[0][0]);
        inventoryTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Show add inventory dialog
    private void showAddInventoryDialog() {
        JDialog dialog = new JDialog(this, "Add Inventory Item", true);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Item Name
        formPanel.add(new JLabel("Item Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        // Category
        formPanel.add(new JLabel("Category:"));
        String[] categories = {"Fertilizers", "Seeds", "Pesticides", "Equipment", "Tools", "Fuel", "Other"};
        JComboBox<String> categoryCombo = new JComboBox<>(categories);
        formPanel.add(categoryCombo);

        // Current Stock
        formPanel.add(new JLabel("Current Stock:"));
        JTextField stockField = new JTextField("0");
        formPanel.add(stockField);

        // Min Stock
        formPanel.add(new JLabel("Min Stock (Alert Level):"));
        JTextField minStockField = new JTextField("0");
        formPanel.add(minStockField);

        // Unit
        formPanel.add(new JLabel("Unit:"));
        String[] units = {"kg", "L", "pcs", "packets", "m", "tons"};
        JComboBox<String> unitCombo = new JComboBox<>(units);
        formPanel.add(unitCombo);

        // Supplier
        formPanel.add(new JLabel("Supplier:"));
        JTextField supplierField = new JTextField();
        formPanel.add(supplierField);

        // Price
        formPanel.add(new JLabel("Price per Unit:"));
        JTextField priceField = new JTextField("0.00");
        formPanel.add(priceField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveBtn = new JButton("Save Item");
        saveBtn.setBackground(new Color(46, 204, 113));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        saveBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String category = (String) categoryCombo.getSelectedItem();
                int currentStock = Integer.parseInt(stockField.getText());
                int minStock = Integer.parseInt(minStockField.getText());
                String unit = (String) unitCombo.getSelectedItem();
                String supplier = supplierField.getText().trim();
                double price = Double.parseDouble(priceField.getText());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Item name is required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = InventoryDAO.addInventoryItem(name, category, currentStock, minStock, unit, supplier, price);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Inventory item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadInventoryData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to add item!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for stock and price!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Show edit inventory dialog
    private void showEditInventoryDialog() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to edit!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int itemId = (int) inventoryTable.getValueAt(selectedRow, 0);
        Object[] item = InventoryDAO.getInventoryItemById(itemId);

        if (item == null) {
            JOptionPane.showMessageDialog(this, "Item not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Edit Inventory Item", true);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Item Name
        formPanel.add(new JLabel("Item Name:"));
        JTextField nameField = new JTextField((String) item[1]);
        formPanel.add(nameField);

        // Category
        formPanel.add(new JLabel("Category:"));
        String[] categories = {"Fertilizers", "Seeds", "Pesticides", "Equipment", "Tools", "Fuel", "Other"};
        JComboBox<String> categoryCombo = new JComboBox<>(categories);
        categoryCombo.setSelectedItem(item[2]);
        formPanel.add(categoryCombo);

        // Current Stock
        formPanel.add(new JLabel("Current Stock:"));
        JTextField stockField = new JTextField(String.valueOf(item[3]));
        formPanel.add(stockField);

        // Min Stock
        formPanel.add(new JLabel("Min Stock:"));
        JTextField minStockField = new JTextField(String.valueOf(item[4]));
        formPanel.add(minStockField);

        // Unit
        formPanel.add(new JLabel("Unit:"));
        String[] units = {"kg", "L", "pcs", "packets", "m", "tons"};
        JComboBox<String> unitCombo = new JComboBox<>(units);
        unitCombo.setSelectedItem(item[5]);
        formPanel.add(unitCombo);

        // Supplier
        formPanel.add(new JLabel("Supplier:"));
        JTextField supplierField = new JTextField((String) item[8]);
        formPanel.add(supplierField);

        // Price
        formPanel.add(new JLabel("Price:"));
        JTextField priceField = new JTextField(String.valueOf(item[7]));
        formPanel.add(priceField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton updateBtn = new JButton("Update Item");
        updateBtn.setBackground(new Color(52, 152, 219));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        updateBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String category = (String) categoryCombo.getSelectedItem();
                int currentStock = Integer.parseInt(stockField.getText());
                int minStock = Integer.parseInt(minStockField.getText());
                String unit = (String) unitCombo.getSelectedItem();
                String supplier = supplierField.getText().trim();
                double price = Double.parseDouble(priceField.getText());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Item name is required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = InventoryDAO.updateInventoryItem(itemId, name, category, currentStock, minStock, unit, supplier, price);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadInventoryData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to update item!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(updateBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Delete inventory item
    private void deleteInventoryItem() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this item?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int itemId = (int) inventoryTable.getValueAt(selectedRow, 0);
            boolean success = InventoryDAO.deleteInventoryItem(itemId);

            if (success) {
                JOptionPane.showMessageDialog(this, "Item deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadInventoryData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete item!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Show update stock dialog
    private void showUpdateStockDialog() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to update stock!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int itemId = (int) inventoryTable.getValueAt(selectedRow, 0);
        String itemName = (String) inventoryTable.getValueAt(selectedRow, 1);
        int currentStock = (int) inventoryTable.getValueAt(selectedRow, 3);

        JDialog dialog = new JDialog(this, "Update Stock - " + itemName, true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        formPanel.add(new JLabel("Current Stock:"));
        formPanel.add(new JLabel(String.valueOf(currentStock)));

        formPanel.add(new JLabel("New Stock:"));
        JTextField newStockField = new JTextField(String.valueOf(currentStock));
        formPanel.add(newStockField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton updateBtn = new JButton("Update Stock");
        updateBtn.setBackground(new Color(155, 89, 182));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        updateBtn.addActionListener(e -> {
            try {
                int newStock = Integer.parseInt(newStockField.getText());

                boolean success = InventoryDAO.updateStock(itemId, newStock);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Stock updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadInventoryData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to update stock!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(updateBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Show low stock alert
    private void showLowStockAlert() {
        List<Object[]> lowStockItems = InventoryDAO.getLowStockItems();

        if (lowStockItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No low stock items found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Low Stock Alert", true);
        dialog.setSize(700, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("⚠️ Low Stock Items - Immediate Action Required");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(231, 76, 60));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table
        String[] columns = {"ID", "Item Name", "Category", "Current Stock", "Min Stock", "Unit"};
        Object[][] data = lowStockItems.toArray(new Object[0][0]);
        JTable lowStockTable = new JTable(data, columns);
        lowStockTable.setRowHeight(30);
        lowStockTable.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(lowStockTable);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton notifyBtn = new JButton("Send Notification to Farmers");
        notifyBtn.setBackground(new Color(241, 196, 15));
        notifyBtn.setForeground(Color.WHITE);
        notifyBtn.setFocusPainted(false);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(108, 117, 125));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);

        notifyBtn.addActionListener(e -> {
            // Send notification to farmers about low stock
            tabbedPane.setSelectedIndex(6); // Switch to Notifications tab
            dialog.dispose();
            JOptionPane.showMessageDialog(this,
                "Switched to Notifications tab.\nYou can now send low stock alerts to farmers.",
                "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        closeBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(notifyBtn);
        buttonPanel.add(closeBtn);

        dialog.add(title, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }


    // ========== MARKETPLACE ==========
    private JTable marketplaceTable;

    private JPanel createMarketplacePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize database table
        MarketplaceDAO.createMarketplaceTable();

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("🛒 Marketplace Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton addProductBtn = createActionButton("Add Product", new Color(46, 204, 113));
        JButton editProductBtn = createActionButton("Edit Product", new Color(52, 152, 219));
        JButton deleteProductBtn = createActionButton("Delete Product", new Color(231, 76, 60));
        JButton approveProductBtn = createActionButton("Approve Product", new Color(155, 89, 182));
        JButton updatePriceBtn = createActionButton("Update Price", new Color(241, 196, 15));
        JButton refreshBtn = createActionButton("🔄 Refresh", new Color(108, 117, 125));

        // Add button actions
        addProductBtn.addActionListener(e -> showAddProductDialog());
        editProductBtn.addActionListener(e -> showEditProductDialog());
        deleteProductBtn.addActionListener(e -> deleteProduct());
        approveProductBtn.addActionListener(e -> approveProduct());
        updatePriceBtn.addActionListener(e -> showUpdatePriceDialog());
        refreshBtn.addActionListener(e -> loadMarketplaceData());

        buttonPanel.add(addProductBtn);
        buttonPanel.add(editProductBtn);
        buttonPanel.add(deleteProductBtn);
        buttonPanel.add(approveProductBtn);
        buttonPanel.add(updatePriceBtn);
        buttonPanel.add(refreshBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"ID", "Product", "Farmer", "Price", "Available Qty", "Status", "Approval", "Orders"};
        marketplaceTable = new JTable();
        marketplaceTable.setRowHeight(30);
        marketplaceTable.setFont(new Font("Arial", Font.PLAIN, 13));
        marketplaceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(marketplaceTable);

        // Load data from database
        loadMarketplaceData();

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== REPORTS ==========
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("📈 Reports & Analytics");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Report Types Grid
        JPanel reportTypesPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        reportTypesPanel.setBackground(Color.WHITE);
        reportTypesPanel.setBorder(BorderFactory.createTitledBorder("Generate Reports"));

        reportTypesPanel.add(createReportCard("Crop Growth Report", "📊", new Color(52, 152, 219)));
        reportTypesPanel.add(createReportCard("Farmer Performance", "👨‍🌾", new Color(46, 204, 113)));
        reportTypesPanel.add(createReportCard("Sustainability", "♻️", new Color(155, 89, 182)));
        reportTypesPanel.add(createReportCard("Inventory Trends", "📦", new Color(241, 196, 15)));
        reportTypesPanel.add(createReportCard("Sales Analytics", "💰", new Color(230, 126, 34)));
        reportTypesPanel.add(createReportCard("Marketplace Stats", "🛒", new Color(52, 73, 94)));
        reportTypesPanel.add(createReportCard("Financial Report", "💵", new Color(26, 188, 156)));
        reportTypesPanel.add(createReportCard("Custom Report", "⚙️", new Color(149, 165, 166)));
        reportTypesPanel.add(createReportCard("Export All Data", "📥", new Color(231, 76, 60)));

        // Export Options
        JPanel exportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exportPanel.setBackground(Color.WHITE);
        exportPanel.setBorder(BorderFactory.createTitledBorder("Export Options"));

        JButton pdfBtn = createActionButton("📄 PDF", new Color(231, 76, 60));
        JButton csvBtn = createActionButton("📊 CSV", new Color(46, 204, 113));
        JButton excelBtn = createActionButton("📈 Excel", new Color(52, 152, 219));

        // Add export actions
        pdfBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Export to PDF - Coming soon!", "Export", JOptionPane.INFORMATION_MESSAGE)
        );
        csvBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Export to CSV - Coming soon!", "Export", JOptionPane.INFORMATION_MESSAGE)
        );
        excelBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Export to Excel - Coming soon!", "Export", JOptionPane.INFORMATION_MESSAGE)
        );

        exportPanel.add(pdfBtn);
        exportPanel.add(csvBtn);
        exportPanel.add(excelBtn);

        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setBackground(Color.WHITE);
        topSection.add(title, BorderLayout.NORTH);
        topSection.add(reportTypesPanel, BorderLayout.CENTER);

        panel.add(topSection, BorderLayout.CENTER);
        panel.add(exportPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ========== NOTIFICATIONS ==========
    private JPanel createNotificationsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("🔔 Notifications & Communication");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton sendToFarmersBtn = createActionButton("Send to Farmers", new Color(46, 204, 113));
        JButton sendToConsumersBtn = createActionButton("Send to Consumers", new Color(52, 152, 219));
        JButton broadcastAlertBtn = createActionButton("Broadcast Alert", new Color(231, 76, 60));
        JButton refreshBtn = createActionButton("🔄 Refresh", new Color(108, 117, 125));

        buttonPanel.add(sendToFarmersBtn);
        buttonPanel.add(sendToConsumersBtn);
        buttonPanel.add(broadcastAlertBtn);
        buttonPanel.add(refreshBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"ID", "Type", "Title", "Recipient", "Priority", "Status", "Date"};
        Object[][] data = {
                {1, "Alert", "Low Stock Warning", "All Admins", "High", "Unread", "2025-11-23"},
                {2, "Announcement", "New Harvest Season", "All Farmers", "Medium", "Sent", "2025-11-22"},
                {3, "Delivery", "Order #1234 Shipped", "Consumer", "Low", "Read", "2025-11-21"},
                {4, "Reminder", "Planting Window", "Farmer Group", "High", "Sent", "2025-11-20"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        // Button Actions
        sendToFarmersBtn.addActionListener(e -> showSendNotificationDialog("FARMER"));
        sendToConsumersBtn.addActionListener(e -> showSendNotificationDialog("CONSUMER"));
        broadcastAlertBtn.addActionListener(e -> showSendNotificationDialog("BROADCAST"));
        refreshBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "Notifications refreshed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Dialog to send notifications
    private void showSendNotificationDialog(String recipientType) {
        JDialog dialog = new JDialog(this, "Send Notification", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Recipient Type
        formPanel.add(new JLabel("Recipient Type:"));
        JTextField recipientField = new JTextField(recipientType);
        recipientField.setEditable(false);
        formPanel.add(recipientField);

        // Notification Type
        formPanel.add(new JLabel("Notification Type:"));
        String[] types = {"ALERT", "REMINDER", "WARNING", "INFO", "SYSTEM"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        formPanel.add(typeCombo);

        // Title
        formPanel.add(new JLabel("Title:"));
        JTextField titleField = new JTextField();
        formPanel.add(titleField);

        // Message
        formPanel.add(new JLabel("Message:"));
        JTextArea messageArea = new JTextArea(3, 20);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane messageScroll = new JScrollPane(messageArea);
        formPanel.add(messageScroll);

        // User ID (for testing - send to specific farmer)
        formPanel.add(new JLabel("Farmer User ID:"));
        JTextField userIdField = new JTextField("1"); // Default to user 1
        formPanel.add(userIdField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton sendBtn = new JButton("Send Notification");
        sendBtn.setBackground(new Color(46, 204, 113));
        sendBtn.setForeground(Color.WHITE);
        sendBtn.setFocusPainted(false);
        sendBtn.setOpaque(true);
        sendBtn.setBorderPainted(false);
        sendBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setOpaque(true);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        sendBtn.addActionListener(e -> {
            String type = (String) typeCombo.getSelectedItem();
            String title = titleField.getText();
            String message = messageArea.getText();
            String userIdStr = userIdField.getText();

            if (title.isEmpty() || message.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = false;

            if ("BROADCAST".equals(recipientType)) {
                // Send to all farmers
                success = FarmerNotificationDAO.broadcastToAllFarmers(title, message, type, "Admin");
            } else if ("FARMER".equals(recipientType)) {
                // Send to specific farmer
                try {
                    int userId = Integer.parseInt(userIdStr);
                    success = FarmerNotificationDAO.addNotification(userId, title, message, type, "Admin");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid User ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                // For CONSUMER, use old method or skip
                JOptionPane.showMessageDialog(dialog, "Consumer notifications coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (success) {
                JOptionPane.showMessageDialog(dialog, "Notification sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to send notification!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(sendBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    // ========== TRACEABILITY ==========
    private JTable traceabilityTable;
    private JTable checkpointsTable;

    private JPanel createTraceabilityPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("🔍 Traceability & Tracking");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton createRecordBtn = createActionButton("Create Record", new Color(46, 204, 113));
        JButton addCheckpointBtn = createActionButton("Add Checkpoint", new Color(52, 152, 219));
        JButton viewTimelineBtn = createActionButton("View Timeline", new Color(155, 89, 182));
        JButton refreshBtn = createActionButton("🔄 Refresh", new Color(108, 117, 125));

        // Button Actions
        createRecordBtn.addActionListener(e -> showCreateRecordDialog());
        addCheckpointBtn.addActionListener(e -> showAddCheckpointDialog());
        viewTimelineBtn.addActionListener(e -> showTimelineDialog());
        refreshBtn.addActionListener(e -> loadTraceabilityRecords());

        buttonPanel.add(createRecordBtn);
        buttonPanel.add(addCheckpointBtn);
        buttonPanel.add(viewTimelineBtn);
        buttonPanel.add(refreshBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Traceability Records Table
        String[] columns = {"Batch ID", "Crop", "Farmer", "Created Date", "Status"};
        traceabilityTable = new JTable(new Object[0][0], columns);
        traceabilityTable.setRowHeight(30);
        traceabilityTable.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(traceabilityTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Traceability Records"));

        // Load data
        loadTraceabilityRecords();

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Load traceability records from database
    private void loadTraceabilityRecords() {
        List<Object[]> records = TraceabilityDAO.getAllRecords();
        String[] columns = {"Batch ID", "Crop", "Farmer", "Created Date", "Status"};
        Object[][] data = records.toArray(new Object[0][0]);
        traceabilityTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Show dialog to create new traceability record
    private void showCreateRecordDialog() {
        JDialog dialog = new JDialog(this, "Create Traceability Record", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Batch ID
        formPanel.add(new JLabel("Batch ID:"));
        JTextField batchIdField = new JTextField();
        formPanel.add(batchIdField);

        // Crop Name
        formPanel.add(new JLabel("Crop Name:"));
        JTextField cropField = new JTextField();
        formPanel.add(cropField);

        // Farmer Name
        formPanel.add(new JLabel("Farmer Name:"));
        JTextField farmerField = new JTextField();
        formPanel.add(farmerField);

        // Date
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        JTextField dateField = new JTextField();
        formPanel.add(dateField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton createBtn = new JButton("Create");
        createBtn.setBackground(new Color(46, 204, 113));
        createBtn.setForeground(Color.WHITE);
        createBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        createBtn.addActionListener(e -> {
            String batchId = batchIdField.getText();
            String crop = cropField.getText();
            String farmer = farmerField.getText();
            String date = dateField.getText();

            if (batchId.isEmpty() || crop.isEmpty() || farmer.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = TraceabilityDAO.createRecord(batchId, crop, farmer, date);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "Record created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTraceabilityRecords();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to create record!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(createBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Show dialog to add checkpoint
    private void showAddCheckpointDialog() {
        JDialog dialog = new JDialog(this, "Add Checkpoint", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Batch ID
        formPanel.add(new JLabel("Batch ID:"));
        JTextField batchIdField = new JTextField();
        formPanel.add(batchIdField);

        // Checkpoint Name
        formPanel.add(new JLabel("Checkpoint:"));
        String[] checkpoints = {"Planting", "Growing", "Harvesting", "Packaging", "Delivery"};
        JComboBox<String> checkpointCombo = new JComboBox<>(checkpoints);
        formPanel.add(checkpointCombo);

        // Date
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        JTextField dateField = new JTextField();
        formPanel.add(dateField);

        // Location
        formPanel.add(new JLabel("Location:"));
        JTextField locationField = new JTextField();
        formPanel.add(locationField);

        // Notes
        formPanel.add(new JLabel("Notes:"));
        JTextField notesField = new JTextField();
        formPanel.add(notesField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = new JButton("Add Checkpoint");
        addBtn.setBackground(new Color(52, 152, 219));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        addBtn.addActionListener(e -> {
            String batchId = batchIdField.getText();
            String checkpoint = (String) checkpointCombo.getSelectedItem();
            String date = dateField.getText();
            String location = locationField.getText();
            String notes = notesField.getText();

            if (batchId.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Batch ID and Date are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = TraceabilityDAO.addCheckpoint(batchId, checkpoint, date, location, notes);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "Checkpoint added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to add checkpoint!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(addBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Show timeline dialog for a specific batch
    private void showTimelineDialog() {
        // Get batch ID from user
        String batchId = JOptionPane.showInputDialog(this, "Enter Batch ID:", "View Timeline", JOptionPane.QUESTION_MESSAGE);

        if (batchId == null || batchId.isEmpty()) {
            return;
        }

        // Get checkpoints for this batch
        List<Object[]> checkpoints = TraceabilityDAO.getCheckpoints(batchId);

        if (checkpoints.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No checkpoints found for batch: " + batchId, "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Create dialog to show timeline
        JDialog dialog = new JDialog(this, "Timeline for " + batchId, true);
        dialog.setSize(700, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        // Timeline visualization
        JPanel timelinePanel = new JPanel();
        timelinePanel.setBackground(new Color(240, 240, 240));
        timelinePanel.setBorder(BorderFactory.createTitledBorder("Timeline Visualization"));

        StringBuilder timeline = new StringBuilder();
        for (int i = 0; i < checkpoints.size(); i++) {
            Object[] checkpoint = checkpoints.get(i);
            timeline.append(checkpoint[0]); // checkpoint name
            if (i < checkpoints.size() - 1) {
                timeline.append(" → ");
            }
        }

        JLabel timelineLabel = new JLabel(timeline.toString());
        timelineLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timelinePanel.add(timelineLabel);

        // Table with checkpoint details
        String[] columns = {"Checkpoint", "Date", "Location", "Notes"};
        Object[][] data = checkpoints.toArray(new Object[0][0]);
        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        // Close button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(108, 117, 125));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeBtn);

        dialog.add(timelinePanel, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // ========== MARKETPLACE MANAGEMENT METHODS ==========

    // Load marketplace data from database
    private void loadMarketplaceData() {
        List<Object[]> products = MarketplaceDAO.getAllProducts();
        String[] columns = {"ID", "Product", "Farmer", "Price", "Available Qty", "Status", "Approval", "Orders"};
        Object[][] data = products.toArray(new Object[0][0]);
        marketplaceTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Show add product dialog
    private void showAddProductDialog() {
        JDialog dialog = new JDialog(this, "Add Marketplace Product", true);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Product Name
        formPanel.add(new JLabel("Product Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        // Farmer Name
        formPanel.add(new JLabel("Farmer Name:"));
        JTextField farmerField = new JTextField();
        formPanel.add(farmerField);

        // Category
        formPanel.add(new JLabel("Category:"));
        String[] categories = {"Vegetables", "Fruits", "Grains", "Dairy", "Organic", "Seeds", "Other"};
        JComboBox<String> categoryCombo = new JComboBox<>(categories);
        formPanel.add(categoryCombo);

        // Price
        formPanel.add(new JLabel("Price per Unit:"));
        JTextField priceField = new JTextField("0.00");
        formPanel.add(priceField);

        // Quantity
        formPanel.add(new JLabel("Quantity Available:"));
        JTextField quantityField = new JTextField("0");
        formPanel.add(quantityField);

        // Unit
        formPanel.add(new JLabel("Unit:"));
        String[] units = {"kg", "ton", "L", "pcs", "dozen", "box"};
        JComboBox<String> unitCombo = new JComboBox<>(units);
        formPanel.add(unitCombo);

        // Description
        formPanel.add(new JLabel("Description:"));
        JTextField descField = new JTextField();
        formPanel.add(descField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveBtn = new JButton("Add Product");
        saveBtn.setBackground(new Color(46, 204, 113));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        saveBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String farmer = farmerField.getText().trim();
                String category = (String) categoryCombo.getSelectedItem();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String unit = (String) unitCombo.getSelectedItem();
                String description = descField.getText().trim();

                if (name.isEmpty() || farmer.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Product name and farmer name are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = MarketplaceDAO.addProduct(name, farmer, price, quantity, unit, category, description);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadMarketplaceData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to add product!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for price and quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Show edit product dialog
    private void showEditProductDialog() {
        int selectedRow = marketplaceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to edit!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int productId = (int) marketplaceTable.getValueAt(selectedRow, 0);
        Object[] product = MarketplaceDAO.getProductById(productId);

        if (product == null) {
            JOptionPane.showMessageDialog(this, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Edit Product", true);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Product Name
        formPanel.add(new JLabel("Product Name:"));
        JTextField nameField = new JTextField((String) product[1]);
        formPanel.add(nameField);

        // Farmer Name
        formPanel.add(new JLabel("Farmer Name:"));
        JTextField farmerField = new JTextField((String) product[2]);
        formPanel.add(farmerField);

        // Category
        formPanel.add(new JLabel("Category:"));
        String[] categories = {"Vegetables", "Fruits", "Grains", "Dairy", "Organic", "Seeds", "Other"};
        JComboBox<String> categoryCombo = new JComboBox<>(categories);
        categoryCombo.setSelectedItem(product[6]);
        formPanel.add(categoryCombo);

        // Price
        formPanel.add(new JLabel("Price:"));
        JTextField priceField = new JTextField(String.valueOf(product[3]));
        formPanel.add(priceField);

        // Quantity
        formPanel.add(new JLabel("Quantity:"));
        JTextField quantityField = new JTextField(String.valueOf(product[4]));
        formPanel.add(quantityField);

        // Unit
        formPanel.add(new JLabel("Unit:"));
        String[] units = {"kg", "ton", "L", "pcs", "dozen", "box"};
        JComboBox<String> unitCombo = new JComboBox<>(units);
        unitCombo.setSelectedItem(product[5]);
        formPanel.add(unitCombo);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton updateBtn = new JButton("Update Product");
        updateBtn.setBackground(new Color(52, 152, 219));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(108, 117, 125));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        updateBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String farmer = farmerField.getText().trim();
                String category = (String) categoryCombo.getSelectedItem();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String unit = (String) unitCombo.getSelectedItem();

                if (name.isEmpty() || farmer.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Product name and farmer name are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = MarketplaceDAO.updateProduct(productId, name, farmer, price, quantity, unit, category);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadMarketplaceData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to update product!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(updateBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Delete product
    private void deleteProduct() {
        int selectedRow = marketplaceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this product?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int productId = (int) marketplaceTable.getValueAt(selectedRow, 0);
            boolean success = MarketplaceDAO.deleteProduct(productId);

            if (success) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadMarketplaceData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete product!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Approve product
    private void approveProduct() {
        int selectedRow = marketplaceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to approve!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int productId = (int) marketplaceTable.getValueAt(selectedRow, 0);
        boolean success = MarketplaceDAO.approveProduct(productId);

        if (success) {
            JOptionPane.showMessageDialog(this, "Product approved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadMarketplaceData();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to approve product!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Show update price dialog
    private void showUpdatePriceDialog() {
        int selectedRow = marketplaceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to update price!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int productId = (int) marketplaceTable.getValueAt(selectedRow, 0);
        String productName = (String) marketplaceTable.getValueAt(selectedRow, 1);

        String priceStr = JOptionPane.showInputDialog(this,
            "Enter new price for " + productName + ":",
            "Update Price",
            JOptionPane.QUESTION_MESSAGE);

        if (priceStr != null && !priceStr.trim().isEmpty()) {
            try {
                double newPrice = Double.parseDouble(priceStr);
                boolean success = MarketplaceDAO.updatePrice(productId, newPrice);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Price updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadMarketplaceData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update price!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid price!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ========== HELPER METHODS ==========

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(Color.WHITE);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 28));
        valueLabel.setForeground(Color.WHITE);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        // Add hover effect
        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(card,
                        "Viewing details for: " + title + "\nThis feature is coming soon!",
                        "Details",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            public void mouseEntered(MouseEvent evt) {
                card.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent evt) {
                card.setBackground(color);
            }
        });

        return card;
    }

    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });

        return button;
    }

    private JPanel createReportCard(String title, String icon, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 40));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(Color.WHITE);

        card.add(iconLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);

        // Add click functionality
        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(card,
                        "Generating " + title + "...\nThis feature is coming soon!",
                        "Generate Report",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            public void mouseEntered(MouseEvent evt) {
                card.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent evt) {
                card.setBackground(color);
            }
        });

        return card;
    }

    private JScrollPane createRecentActivityTable() {
        String[] columns = {"Time", "Activity", "User", "Status"};
        Object[][] data = {
                {"10:30 AM", "New farmer registration", "System", "Pending"},
                {"10:15 AM", "Crop stage updated", "Admin", "Completed"},
                {"09:45 AM", "Low stock alert", "System", "Active"},
                {"09:30 AM", "New order placed", "Consumer", "Processing"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Recent Activity"));

        return scrollPane;
    }

    // ========== FARMER MANAGEMENT METHODS ==========

    // Load farmers from database
    private void loadFarmersData() {
        List<Object[]> farmers = farmerDAO.getAllFarmers();
        String[] columns = {"ID", "Name", "Farmer ID", "Farm Size", "Certifications",
                           "Farm Location", "Score", "Phone", "Email", "Status"};

        Object[][] data = farmers.toArray(new Object[0][]);
        farmerTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Show add farmer dialog
    private void showAddFarmerDialog() {
        JDialog dialog = new JDialog(this, "Add New Farmer", true);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);

        // Form fields
        JTextField nameField = new JTextField();
        JTextField farmerIdField = new JTextField();
        JTextField farmSizeField = new JTextField();
        JTextField certificationsField = new JTextField();
        JTextField farmLocationField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Farmer ID:"));
        formPanel.add(farmerIdField);
        formPanel.add(new JLabel("Farm Size (e.g., 50 acres):"));
        formPanel.add(farmSizeField);
        formPanel.add(new JLabel("Certifications:"));
        formPanel.add(certificationsField);
        formPanel.add(new JLabel("Farm Location:"));
        formPanel.add(farmLocationField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(46, 204, 113));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(149, 165, 166));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        saveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String farmerId = farmerIdField.getText().trim();
            String farmSize = farmSizeField.getText().trim();
            String certifications = certificationsField.getText().trim();
            String farmLocation = farmLocationField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || farmerId.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                    "Name and Farmer ID are required!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = farmerDAO.addFarmer(name, farmerId, farmSize,
                                                 certifications, farmLocation, phone, email);

            if (success) {
                JOptionPane.showMessageDialog(dialog, "Farmer added successfully!");
                loadFarmersData();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog,
                    "Error adding farmer. Farmer ID may already exist.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Show edit farmer dialog
    private void showEditFarmerDialog() {
        int selectedRow = farmerTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a farmer to edit!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get selected farmer data
        int id = (int) farmerTable.getValueAt(selectedRow, 0);
        String currentName = (String) farmerTable.getValueAt(selectedRow, 1);
        String currentFarmerId = (String) farmerTable.getValueAt(selectedRow, 2);
        String currentFarmSize = (String) farmerTable.getValueAt(selectedRow, 3);
        String currentCerts = (String) farmerTable.getValueAt(selectedRow, 4);
        String currentLocation = (String) farmerTable.getValueAt(selectedRow, 5);
        String currentPhone = (String) farmerTable.getValueAt(selectedRow, 7);
        String currentEmail = (String) farmerTable.getValueAt(selectedRow, 8);

        JDialog dialog = new JDialog(this, "Edit Farmer", true);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);

        // Form fields
        JTextField nameField = new JTextField(currentName);
        JTextField farmerIdField = new JTextField(currentFarmerId);
        JTextField farmSizeField = new JTextField(currentFarmSize);
        JTextField certificationsField = new JTextField(currentCerts);
        JTextField farmLocationField = new JTextField(currentLocation);
        JTextField phoneField = new JTextField(currentPhone);
        JTextField emailField = new JTextField(currentEmail);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Farmer ID:"));
        formPanel.add(farmerIdField);
        formPanel.add(new JLabel("Farm Size:"));
        formPanel.add(farmSizeField);
        formPanel.add(new JLabel("Certifications:"));
        formPanel.add(certificationsField);
        formPanel.add(new JLabel("Farm Location:"));
        formPanel.add(farmLocationField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBackground(new Color(52, 152, 219));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(149, 165, 166));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        updateBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String farmerId = farmerIdField.getText().trim();
            String farmSize = farmSizeField.getText().trim();
            String certifications = certificationsField.getText().trim();
            String farmLocation = farmLocationField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || farmerId.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                    "Name and Farmer ID are required!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = farmerDAO.updateFarmer(id, name, farmerId, farmSize,
                                                    certifications, farmLocation, phone, email);

            if (success) {
                JOptionPane.showMessageDialog(dialog, "Farmer updated successfully!");
                loadFarmersData();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog,
                    "Error updating farmer.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(updateBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Delete farmer
    private void deleteFarmer() {
        int selectedRow = farmerTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a farmer to delete!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) farmerTable.getValueAt(selectedRow, 0);
        String name = (String) farmerTable.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete farmer: " + name + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = farmerDAO.deleteFarmer(id);

            if (success) {
                JOptionPane.showMessageDialog(this, "Farmer deleted successfully!");
                loadFarmersData();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error deleting farmer.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ========== MAIN ==========
    public static void main(String[] args) {
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new adminDashboard("Admin User");
        });
    }
}
