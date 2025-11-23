package com.ecofarm.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Admin Dashboard - Main UI
 * EcoFarm Connect - Smart Agriculture System
 */
public class AdminDashboard extends JFrame {

    private JTabbedPane tabbedPane;

    public AdminDashboard(String adminName) {
        setTitle("EcoFarm Connect - Admin Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI(adminName);

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

        // Add tabs for each section
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

        // Left side - Title
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

        // Right side - Logout
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(220, 53, 69));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 14));

        header.add(leftPanel, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        return header;
    }

    // ========== DASHBOARD PANEL ==========
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Summary Cards at top
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

        actionsPanel.add(createActionButton("➕ Add Farmer", new Color(52, 152, 219)));
        actionsPanel.add(createActionButton("🏠 Add Farm", new Color(46, 204, 113)));
        actionsPanel.add(createActionButton("🌱 Add Crop", new Color(155, 89, 182)));
        actionsPanel.add(createActionButton("📦 Add Inventory", new Color(241, 196, 15)));
        actionsPanel.add(createActionButton("📢 Send Announcement", new Color(230, 126, 34)));
        actionsPanel.add(createActionButton("📊 Generate Report", new Color(52, 73, 94)));

        // Combine
        JPanel topSection = new JPanel(new BorderLayout(10, 10));
        topSection.setBackground(Color.WHITE);
        topSection.add(cardsPanel, BorderLayout.CENTER);
        topSection.add(actionsPanel, BorderLayout.SOUTH);

        panel.add(topSection, BorderLayout.NORTH);

        // Recent Activity Table
        panel.add(createRecentActivityTable(), BorderLayout.CENTER);

        return panel;
    }

    // ========== CROP MANAGEMENT PANEL ==========
    private JPanel createCropManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("🌾 Crop Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(createActionButton("Add Crop", new Color(46, 204, 113)));
        buttonPanel.add(createActionButton("Update Stage", new Color(52, 152, 219)));
        buttonPanel.add(createActionButton("View Schedule", new Color(155, 89, 182)));
        buttonPanel.add(createActionButton("Export Data", new Color(52, 73, 94)));

        // Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(new JLabel("Search:"));
        JTextField searchField = new JTextField(20);
        searchPanel.add(searchField);
        searchPanel.add(new JButton("🔍 Search"));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // Crops Table
        String[] columns = {"ID", "Crop Name", "Farm", "Farmer", "Stage", "Health", "Planting Date", "Expected Harvest", "Status"};
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

    // ========== FARMER MANAGEMENT PANEL ==========
    private JPanel createFarmerManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("👨‍🌾 Farmer Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(createActionButton("Add Farmer", new Color(46, 204, 113)));
        buttonPanel.add(createActionButton("Approve Registration", new Color(52, 152, 219)));
        buttonPanel.add(createActionButton("Manage Certification", new Color(155, 89, 182)));
        buttonPanel.add(createActionButton("View Details", new Color(241, 196, 15)));
        buttonPanel.add(createActionButton("Disable Account", new Color(231, 76, 60)));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Farmers Table
        String[] columns = {"ID", "Name", "Email", "Phone", "Farm Name", "Farm Size", "Certification", "Registration Status", "Actions"};
        Object[][] data = {
            {1, "John Farmer", "john@farm.com", "+1234567890", "Green Valley Farm", "50 acres", "Organic", "Approved", "Edit"},
            {2, "Mary Smith", "mary@farm.com", "+1234567891", "Sunny Acres", "35 acres", "Pending", "Approved", "Edit"},
            {3, "Bob Johnson", "bob@farm.com", "+1234567892", "River Farm", "60 acres", "Sustainable", "Approved", "Edit"},
            {4, "Alice Brown", "alice@farm.com", "+1234567893", "Hill Farm", "25 acres", "None", "Pending", "Edit"},
            {5, "Tom Wilson", "tom@farm.com", "+1234567894", "Valley Farm", "40 acres", "Organic", "Approved", "Edit"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== INVENTORY PANEL ==========
    private JPanel createInventoryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("📦 Inventory Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(createActionButton("Add Item", new Color(46, 204, 113)));
        buttonPanel.add(createActionButton("Update Stock", new Color(52, 152, 219)));
        buttonPanel.add(createActionButton("Approve Restock", new Color(155, 89, 182)));
        buttonPanel.add(createActionButton("Low Stock Alert", new Color(231, 76, 60)));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Inventory Table
        String[] columns = {"ID", "Item Name", "Category", "Current Stock", "Min Stock", "Unit", "Status", "Last Updated"};
        Object[][] data = {
            {1, "NPK Fertilizer", "Fertilizers", "250", "100", "kg", "In Stock", "2025-11-20"},
            {2, "Organic Pesticide", "Pesticides", "45", "50", "L", "Low Stock", "2025-11-18"},
            {3, "Wheat Seeds", "Seeds", "500", "200", "kg", "In Stock", "2025-11-15"},
            {4, "Irrigation Pipes", "Equipment", "120", "50", "m", "In Stock", "2025-10-30"},
            {5, "Tractor Fuel", "Fuel", "200", "100", "L", "In Stock", "2025-11-22"},
            {6, "Herbicide", "Pesticides", "15", "30", "L", "Low Stock", "2025-11-10"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== MARKETPLACE PANEL ==========
    private JPanel createMarketplacePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("🛒 Marketplace Management");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(createActionButton("Approve Product", new Color(46, 204, 113)));
        buttonPanel.add(createActionButton("Update Pricing", new Color(52, 152, 219)));
        buttonPanel.add(createActionButton("Manage Orders", new Color(155, 89, 182)));
        buttonPanel.add(createActionButton("View Subscriptions", new Color(241, 196, 15)));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Products Table
        String[] columns = {"ID", "Product", "Farmer", "Price", "Available Qty", "Status", "Approval", "Orders"};
        Object[][] data = {
            {1, "Organic Wheat", "John Farmer", "$245/ton", "500 kg", "Active", "Approved", "12"},
            {2, "Fresh Tomatoes", "Alice Brown", "$85/ton", "200 kg", "Active", "Approved", "8"},
            {3, "Sweet Corn", "Mary Smith", "$180/ton", "350 kg", "Active", "Pending", "0"},
            {4, "Brown Rice", "Bob Johnson", "$320/ton", "600 kg", "Active", "Approved", "15"},
            {5, "Potatoes", "Tom Wilson", "$150/ton", "400 kg", "Active", "Approved", "10"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== REPORTS & ANALYTICS PANEL ==========
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("📈 Reports & Analytics");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Report Types
        JPanel reportTypesPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        reportTypesPanel.setBackground(Color.WHITE);
        reportTypesPanel.setBorder(BorderFactory.createTitledBorder("Generate Reports"));

        reportTypesPanel.add(createReportCard("Crop Growth Report", "📊", new Color(52, 152, 219)));
        reportTypesPanel.add(createReportCard("Farmer Performance", "👨‍🌾", new Color(46, 204, 113)));
        reportTypesPanel.add(createReportCard("Sustainability Metrics", "♻️", new Color(155, 89, 182)));
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
        exportPanel.add(new JLabel("Export Format:"));
        exportPanel.add(createActionButton("📄 PDF", new Color(231, 76, 60)));
        exportPanel.add(createActionButton("📊 CSV", new Color(46, 204, 113)));
        exportPanel.add(createActionButton("📈 Excel", new Color(52, 152, 219)));

        // Date Range
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setBackground(Color.WHITE);
        datePanel.setBorder(BorderFactory.createTitledBorder("Date Range"));
        datePanel.add(new JLabel("From:"));
        datePanel.add(new JTextField(10));
        datePanel.add(new JLabel("To:"));
        datePanel.add(new JTextField(10));
        datePanel.add(new JButton("Apply"));

        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setBackground(Color.WHITE);
        topSection.add(title, BorderLayout.NORTH);
        topSection.add(reportTypesPanel, BorderLayout.CENTER);

        JPanel bottomSection = new JPanel(new GridLayout(2, 1));
        bottomSection.setBackground(Color.WHITE);
        bottomSection.add(datePanel);
        bottomSection.add(exportPanel);

        panel.add(topSection, BorderLayout.CENTER);
        panel.add(bottomSection, BorderLayout.SOUTH);

        return panel;
    }

    // ========== NOTIFICATIONS PANEL ==========
    private JPanel createNotificationsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("🔔 Notifications & Communication");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(createActionButton("Send to Farmers", new Color(46, 204, 113)));
        buttonPanel.add(createActionButton("Send to Consumers", new Color(52, 152, 219)));
        buttonPanel.add(createActionButton("Broadcast Alert", new Color(231, 76, 60)));
        buttonPanel.add(createActionButton("Mark All Read", new Color(149, 165, 166)));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Notifications Table
        String[] columns = {"ID", "Type", "Title", "Recipient", "Priority", "Status", "Date", "Actions"};
        Object[][] data = {
            {1, "Alert", "Low Stock Warning", "All Admins", "High", "Unread", "2025-11-23", "Read"},
            {2, "Announcement", "New Harvest Season", "All Farmers", "Medium", "Sent", "2025-11-22", "View"},
            {3, "Delivery", "Order #1234 Shipped", "John Consumer", "Low", "Read", "2025-11-21", "View"},
            {4, "Reminder", "Planting Window Closing", "Farmer Group", "High", "Sent", "2025-11-20", "View"},
            {5, "System", "Booking Conflict", "Admin", "Critical", "Unread", "2025-11-23", "Resolve"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== TRACEABILITY PANEL ==========
    private JPanel createTraceabilityPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("🔍 Traceability & Tracking");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(new JLabel("Search Batch ID:"));
        JTextField batchField = new JTextField(20);
        searchPanel.add(batchField);
        searchPanel.add(new JButton("🔍 Track"));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // Batch Tracking Table
        String[] columns = {"Batch ID", "Crop", "Farm", "Event", "Date", "Location", "Status", "View Chain"};
        Object[][] data = {
            {"BATCH-001", "Wheat", "Green Valley", "Harvested", "2025-11-15", "Field A", "QC Passed", "View"},
            {"BATCH-002", "Tomatoes", "Hill Farm", "Packaged", "2025-11-20", "Warehouse B", "Shipped", "View"},
            {"BATCH-003", "Rice", "River Farm", "Processing", "2025-11-18", "Mill C", "In Progress", "View"},
            {"BATCH-004", "Corn", "Sunny Acres", "Delivered", "2025-11-22", "Market D", "Completed", "View"},
            {"BATCH-005", "Potatoes", "Valley Farm", "QC Testing", "2025-11-23", "Lab E", "Pending", "View"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        // Trace Chain Visualization (placeholder)
        JPanel traceChain = new JPanel();
        traceChain.setBackground(new Color(240, 240, 240));
        traceChain.setBorder(BorderFactory.createTitledBorder("Trace Chain Visualization"));
        traceChain.setPreferredSize(new Dimension(1100, 150));

        JLabel chainLabel = new JLabel("Seed → Planted → Growing → Fertilized → Harvested → QC Tested → Packaged → Shipped → Delivered");
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

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(Color.WHITE);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 28));
        valueLabel.setForeground(Color.WHITE);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        return button;
    }

    private JPanel createReportCard(String title, String icon, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 40));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(Color.WHITE);

        card.add(iconLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);

        return card;
    }

    private JScrollPane createRecentActivityTable() {
        String[] columns = {"Time", "Activity", "User", "Status"};
        Object[][] data = {
            {"10:30 AM", "New farmer registration - John Doe", "System", "Pending"},
            {"10:15 AM", "Crop stage updated - Wheat Batch #123", "Admin", "Completed"},
            {"09:45 AM", "Low stock alert - NPK Fertilizer", "System", "Active"},
            {"09:30 AM", "New order placed - Order #5678", "Consumer", "Processing"},
            {"09:00 AM", "Equipment booking approved", "Admin", "Approved"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Recent Activity"));

        return scrollPane;
    }

    // ========== MAIN METHOD ==========
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard("Admin User");
        });
    }
}

