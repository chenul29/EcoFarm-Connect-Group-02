import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Admin Dashboard - EcoFarm Connect
 * Smart Agriculture System
 */
public class adminDashboard extends JFrame {

    private JTabbedPane tabbedPane;

    public adminDashboard(String adminName) {
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
    private JPanel createCropManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("🌾 Crop Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton addCropBtn = createActionButton("Add Crop", new Color(46, 204, 113));
        JButton updateStageBtn = createActionButton("Update Stage", new Color(52, 152, 219));
        JButton viewScheduleBtn = createActionButton("View Schedule", new Color(155, 89, 182));
        JButton exportDataBtn = createActionButton("Export Data", new Color(52, 73, 94));

        // Add button actions
        addCropBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Add Crop form - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        updateStageBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Update Crop Stage - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        viewScheduleBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "View Planting/Harvest Schedule - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        exportDataBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Export crop data as CSV/PDF - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );

        buttonPanel.add(addCropBtn);
        buttonPanel.add(updateStageBtn);
        buttonPanel.add(viewScheduleBtn);
        buttonPanel.add(exportDataBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        // Table
        String[] columns = {"ID", "Crop Name", "Farm", "Farmer", "Stage", "Health", "Planted", "Expected Harvest", "Status"};
        Object[][] data = {
                {1, "Wheat", "Green Valley Farm", "John Farmer", "Growing", "Excellent", "2025-09-15", "2025-12-15", "Active"},
                {2, "Corn", "Sunny Acres", "Mary Smith", "Flowering", "Good", "2025-08-20", "2025-11-30", "Active"},
                {3, "Rice", "River Farm", "Bob Johnson", "Planted", "Good", "2025-10-01", "2026-01-15", "Active"},
                {4, "Tomatoes", "Hill Farm", "Alice Brown", "Harvesting", "Excellent", "2025-07-10", "2025-11-20", "Active"},
                {5, "Potatoes", "Valley Farm", "Tom Wilson", "Growing", "Fair", "2025-09-05", "2025-12-10", "Active"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== FARMER MANAGEMENT ==========
    private JPanel createFarmerManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("👨‍🌾 Farmer Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton addFarmerBtn = createActionButton("Add Farmer", new Color(46, 204, 113));
        JButton approveRegBtn = createActionButton("Approve Registration", new Color(52, 152, 219));
        JButton manageCertBtn = createActionButton("Manage Certification", new Color(155, 89, 182));
        JButton viewDetailsBtn = createActionButton("View Details", new Color(241, 196, 15));

        // Add button actions
        addFarmerBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Add New Farmer form - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        approveRegBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Approve Farmer Registration - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        manageCertBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Manage Farmer Certifications - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        viewDetailsBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "View Farmer Details - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );

        buttonPanel.add(addFarmerBtn);
        buttonPanel.add(approveRegBtn);
        buttonPanel.add(manageCertBtn);
        buttonPanel.add(viewDetailsBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"ID", "Name", "Email", "Phone", "Farm Name", "Farm Size", "Certification", "Status"};
        Object[][] data = {
                {1, "John Farmer", "john@farm.com", "+1234567890", "Green Valley", "50 acres", "Organic", "Approved"},
                {2, "Mary Smith", "mary@farm.com", "+1234567891", "Sunny Acres", "35 acres", "Pending", "Approved"},
                {3, "Bob Johnson", "bob@farm.com", "+1234567892", "River Farm", "60 acres", "Sustainable", "Approved"},
                {4, "Alice Brown", "alice@farm.com", "+1234567893", "Hill Farm", "25 acres", "None", "Pending"},
                {5, "Tom Wilson", "tom@farm.com", "+1234567894", "Valley Farm", "40 acres", "Organic", "Approved"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== INVENTORY ==========
    private JPanel createInventoryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("📦 Inventory Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton addItemBtn = createActionButton("Add Item", new Color(46, 204, 113));
        JButton updateStockBtn = createActionButton("Update Stock", new Color(52, 152, 219));
        JButton approveRestockBtn = createActionButton("Approve Restock", new Color(155, 89, 182));
        JButton lowStockAlertBtn = createActionButton("Low Stock Alert", new Color(231, 76, 60));

        // Add button actions
        addItemBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Add Inventory Item - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        updateStockBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Update Stock Levels - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        approveRestockBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Approve Restock Request - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        lowStockAlertBtn.addActionListener(e -> {
            // Send low stock notification to farmers
            tabbedPane.setSelectedIndex(6); // Switch to Notifications tab
            JOptionPane.showMessageDialog(this, "Low Stock Alert System - Switch to Notifications tab to send alerts", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        buttonPanel.add(addItemBtn);
        buttonPanel.add(updateStockBtn);
        buttonPanel.add(approveRestockBtn);
        buttonPanel.add(lowStockAlertBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"ID", "Item Name", "Category", "Current Stock", "Min Stock", "Unit", "Status", "Last Updated"};
        Object[][] data = {
                {1, "NPK Fertilizer", "Fertilizers", "250", "100", "kg", "In Stock", "2025-11-20"},
                {2, "Organic Pesticide", "Pesticides", "45", "50", "L", "Low Stock", "2025-11-18"},
                {3, "Wheat Seeds", "Seeds", "500", "200", "kg", "In Stock", "2025-11-15"},
                {4, "Irrigation Pipes", "Equipment", "120", "50", "m", "In Stock", "2025-10-30"},
                {5, "Tractor Fuel", "Fuel", "200", "100", "L", "In Stock", "2025-11-22"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== MARKETPLACE ==========
    private JPanel createMarketplacePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title & Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("🛒 Marketplace Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        JButton approveProductBtn = createActionButton("Approve Product", new Color(46, 204, 113));
        JButton updatePricingBtn = createActionButton("Update Pricing", new Color(52, 152, 219));
        JButton manageOrdersBtn = createActionButton("Manage Orders", new Color(155, 89, 182));

        // Add button actions
        approveProductBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Approve Product Listing - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        updatePricingBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Update Product Pricing - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );
        manageOrdersBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Manage Marketplace Orders - Coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE)
        );

        buttonPanel.add(approveProductBtn);
        buttonPanel.add(updatePricingBtn);
        buttonPanel.add(manageOrdersBtn);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"ID", "Product", "Farmer", "Price", "Available Qty", "Status", "Approval", "Orders"};
        Object[][] data = {
                {1, "Organic Wheat", "John Farmer", "$245/ton", "500 kg", "Active", "Approved", "12"},
                {2, "Fresh Tomatoes", "Alice Brown", "$85/ton", "200 kg", "Active", "Approved", "8"},
                {3, "Sweet Corn", "Mary Smith", "$180/ton", "350 kg", "Active", "Pending", "0"},
                {4, "Brown Rice", "Bob Johnson", "$320/ton", "600 kg", "Active", "Approved", "15"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

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

            try {
                int userId = Integer.parseInt(userIdStr);
                boolean success = NotificationDAO.addNotification(userId, type, title, message);

                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Notification sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to send notification!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid User ID!", "Error", JOptionPane.ERROR_MESSAGE);
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
    private JPanel createTraceabilityPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title & Search
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("🔍 Traceability & Tracking");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(new JLabel("Search Batch ID:"));
        searchPanel.add(new JTextField(20));
        searchPanel.add(new JButton("🔍 Track"));

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"Batch ID", "Crop", "Farm", "Event", "Date", "Location", "Status"};
        Object[][] data = {
                {"BATCH-001", "Wheat", "Green Valley", "Harvested", "2025-11-15", "Field A", "QC Passed"},
                {"BATCH-002", "Tomatoes", "Hill Farm", "Packaged", "2025-11-20", "Warehouse B", "Shipped"},
                {"BATCH-003", "Rice", "River Farm", "Processing", "2025-11-18", "Mill C", "In Progress"},
                {"BATCH-004", "Corn", "Sunny Acres", "Delivered", "2025-11-22", "Market D", "Completed"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        // Trace Chain
        JPanel traceChain = new JPanel();
        traceChain.setBackground(new Color(240, 240, 240));
        traceChain.setBorder(BorderFactory.createTitledBorder("Trace Chain Visualization"));
        traceChain.setPreferredSize(new Dimension(1100, 100));

        JLabel chainLabel = new JLabel("Seed → Planted → Growing → Harvested → QC → Packaged → Shipped → Delivered");
        chainLabel.setFont(new Font("Arial", Font.BOLD, 14));
        traceChain.add(chainLabel);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(traceChain, BorderLayout.SOUTH);

        return panel;
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
