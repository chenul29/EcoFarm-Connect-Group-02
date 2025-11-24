import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class farmerDashboard extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    // My Crops Components
    private JPanel cropsPanel;
    private JTable cropsTable;
    private JButton addCropButton;
    private JButton updateStageButton;
    private JButton harvestPredictionButton;

    // My Inventory Components
    private JPanel inventoryPanel;
    private JTable toolsTable;
    private JTable stockTable;

    // Notifications Components
    private JPanel notificationsPanel;

    // Sustainability Score Components
    private JPanel sustainabilityPanel;
    private JLabel carbonScoreLabel;
    private JLabel soilHealthLabel;
    private JProgressBar waterUsageBar;

    // Profile Components
    private JPanel profilePanel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField farmNameField;
    private JTextField farmSizeField;
    private JTextArea certificationsArea;
    private JButton saveProfileButton;

    public farmerDashboard() {
        setTitle("Farmer Dashboard - EcoFarm Connect");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header
        JPanel headerPanel = createHeader();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Add tabs
        tabbedPane.addTab("🌾 My Crops", createCropsPanel());
        tabbedPane.addTab("📦 My Inventory", createInventoryPanel());
        tabbedPane.addTab("🔔 Notifications", createNotificationsPanel());
        tabbedPane.addTab("🌱 Sustainability Score", createSustainabilityPanel());
        tabbedPane.addTab("👤 Profile", createProfilePanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(34, 139, 34));
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("🌾 Farmer Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome back, Farmer!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeLabel.setForeground(Color.WHITE);

        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.setOpaque(false);
        leftPanel.add(titleLabel);
        leftPanel.add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setOpaque(true);
        logoutButton.setBorderPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setPreferredSize(new Dimension(100, 40));

        header.add(leftPanel, BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);

        return header;
    }

    private JPanel createCropsPanel() {
        cropsPanel = new JPanel(new BorderLayout(10, 10));
        cropsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("My Crops Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Crops Table
        String[] columnNames = {"Crop Name", "Planting Date", "Growth Stage", "Expected Harvest", "Status"};
        Object[][] data = {
            {"Tomatoes", "2024-10-15", "Flowering", "2024-12-20", "Healthy"},
            {"Carrots", "2024-09-20", "Mature", "2024-11-30", "Ready"},
            {"Lettuce", "2024-11-01", "Seedling", "2024-12-15", "Healthy"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        cropsTable = new JTable(model);
        cropsTable.setRowHeight(30);
        cropsTable.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane tableScrollPane = new JScrollPane(cropsTable);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        addCropButton = new JButton("➕ Add New Crop");
        addCropButton.setBackground(new Color(12, 195, 53));
        addCropButton.setForeground(Color.WHITE);
        addCropButton.setFocusPainted(false);
        addCropButton.setFont(new Font("Arial", Font.BOLD, 13));
        addCropButton.setOpaque(true);
        addCropButton.setBorderPainted(false);
        addCropButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        updateStageButton = new JButton("🔄 Update Growth Stage");
        updateStageButton.setBackground(new Color(0, 123, 255));
        updateStageButton.setForeground(Color.WHITE);
        updateStageButton.setFocusPainted(false);
        updateStageButton.setFont(new Font("Arial", Font.BOLD, 13));
        updateStageButton.setOpaque(true);
        updateStageButton.setBorderPainted(false);
        updateStageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        harvestPredictionButton = new JButton("📊 Check Harvest Prediction");
        harvestPredictionButton.setBackground(new Color(255, 193, 7));
        harvestPredictionButton.setForeground(Color.BLACK);
        harvestPredictionButton.setFocusPainted(false);
        harvestPredictionButton.setFont(new Font("Arial", Font.BOLD, 13));
        harvestPredictionButton.setOpaque(true);
        harvestPredictionButton.setBorderPainted(false);
        harvestPredictionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(addCropButton);
        buttonPanel.add(updateStageButton);
        buttonPanel.add(harvestPredictionButton);

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        cropsPanel.add(topPanel, BorderLayout.NORTH);
        cropsPanel.add(tableScrollPane, BorderLayout.CENTER);

        return cropsPanel;
    }

    private JPanel createInventoryPanel() {
        inventoryPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        inventoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tools Section
        JPanel toolsSection = new JPanel(new BorderLayout(5, 5));
        JLabel toolsLabel = new JLabel("🔧 Tools Assigned to Me");
        toolsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        String[] toolColumns = {"Tool Name", "Condition", "Last Used", "Status"};
        Object[][] toolData = {
            {"Tractor", "Good", "2024-11-20", "Available"},
            {"Plow", "Excellent", "2024-11-15", "Available"},
            {"Irrigation Pump", "Fair", "2024-11-22", "In Use"}
        };

        DefaultTableModel toolModel = new DefaultTableModel(toolData, toolColumns);
        toolsTable = new JTable(toolModel);
        toolsTable.setRowHeight(25);
        toolsTable.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane toolScrollPane = new JScrollPane(toolsTable);

        toolsSection.add(toolsLabel, BorderLayout.NORTH);
        toolsSection.add(toolScrollPane, BorderLayout.CENTER);

        // Stock Section
        JPanel stockSection = new JPanel(new BorderLayout(5, 5));
        JLabel stockLabel = new JLabel("📦 Fertilizers & Seeds Stock Levels");
        stockLabel.setFont(new Font("Arial", Font.BOLD, 16));

        String[] stockColumns = {"Item Name", "Quantity", "Unit", "Status"};
        Object[][] stockData = {
            {"Organic Fertilizer", "50", "kg", "Sufficient"},
            {"Tomato Seeds", "15", "packets", "Low Stock"},
            {"Pesticide", "30", "liters", "Sufficient"},
            {"Carrot Seeds", "8", "packets", "Low Stock"}
        };

        DefaultTableModel stockModel = new DefaultTableModel(stockData, stockColumns);
        stockTable = new JTable(stockModel);
        stockTable.setRowHeight(25);
        stockTable.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane stockScrollPane = new JScrollPane(stockTable);

        stockSection.add(stockLabel, BorderLayout.NORTH);
        stockSection.add(stockScrollPane, BorderLayout.CENTER);

        inventoryPanel.add(toolsSection);
        inventoryPanel.add(stockSection);

        return inventoryPanel;
    }

    private JPanel createNotificationsPanel() {
        notificationsPanel = new JPanel(new BorderLayout(10, 10));
        notificationsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel with title and buttons
        JPanel topPanel = new JPanel(new BorderLayout());

        // Title and unread count
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("🔔 Notifications & Alerts");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);

        // Unread count badge
        int unreadCount = NotificationDAO.getUnreadCount(1); // userId = 1 for testing
        JLabel unreadLabel = new JLabel(" (" + unreadCount + " unread) ");
        unreadLabel.setFont(new Font("Arial", Font.BOLD, 14));
        unreadLabel.setForeground(Color.RED);
        titlePanel.add(unreadLabel);

        topPanel.add(titlePanel, BorderLayout.WEST);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 12));
        refreshButton.setBackground(new Color(0, 123, 255));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setOpaque(true);
        refreshButton.setBorderPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton markAllReadButton = new JButton("✓ Mark All Read");
        markAllReadButton.setFont(new Font("Arial", Font.BOLD, 12));
        markAllReadButton.setBackground(new Color(40, 167, 69));
        markAllReadButton.setForeground(Color.WHITE);
        markAllReadButton.setFocusPainted(false);
        markAllReadButton.setOpaque(true);
        markAllReadButton.setBorderPainted(false);
        markAllReadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton deleteButton = new JButton("🗑️ Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setOpaque(true);
        deleteButton.setBorderPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(refreshButton);
        buttonPanel.add(markAllReadButton);
        buttonPanel.add(deleteButton);

        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Notifications Table
        String[] columnNames = {"Status", "Type", "Title", "Message", "Date/Time"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        JTable notificationsTable = new JTable(model);
        notificationsTable.setRowHeight(35);
        notificationsTable.setFont(new Font("Arial", Font.PLAIN, 12));
        notificationsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        notificationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set column widths
        notificationsTable.getColumnModel().getColumn(0).setPreferredWidth(60);  // Status
        notificationsTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Type
        notificationsTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Title
        notificationsTable.getColumnModel().getColumn(3).setPreferredWidth(350); // Message
        notificationsTable.getColumnModel().getColumn(4).setPreferredWidth(150); // Date

        JScrollPane tableScrollPane = new JScrollPane(notificationsTable);

        // Load notifications from database
        loadNotifications(model, unreadLabel);

        // Button actions
        refreshButton.addActionListener(e -> {
            loadNotifications(model, unreadLabel);
            JOptionPane.showMessageDialog(notificationsPanel, "Notifications refreshed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        markAllReadButton.addActionListener(e -> {
            NotificationDAO.markAllAsRead(1); // userId = 1
            loadNotifications(model, unreadLabel);
            JOptionPane.showMessageDialog(notificationsPanel, "All notifications marked as read!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = notificationsTable.getSelectedRow();
            if (selectedRow >= 0) {
                int notificationId = (int) model.getValueAt(selectedRow, 0).toString().hashCode(); // Get ID from hidden column
                // For simplicity, we'll get the notification ID from the list
                java.util.List<Notification> notifications = NotificationDAO.getNotificationsByUserId(1);
                if (selectedRow < notifications.size()) {
                    int id = notifications.get(selectedRow).getId();
                    NotificationDAO.deleteNotification(id);
                    loadNotifications(model, unreadLabel);
                    JOptionPane.showMessageDialog(notificationsPanel, "Notification deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(notificationsPanel, "Please select a notification to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Double-click to mark as read
        notificationsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = notificationsTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        java.util.List<Notification> notifications = NotificationDAO.getNotificationsByUserId(1);
                        if (selectedRow < notifications.size()) {
                            int id = notifications.get(selectedRow).getId();
                            NotificationDAO.markAsRead(id);
                            loadNotifications(model, unreadLabel);
                        }
                    }
                }
            }
        });

        notificationsPanel.add(topPanel, BorderLayout.NORTH);
        notificationsPanel.add(tableScrollPane, BorderLayout.CENTER);

        return notificationsPanel;
    }

    // Helper method to load notifications into table
    private void loadNotifications(DefaultTableModel model, JLabel unreadLabel) {
        model.setRowCount(0); // Clear existing rows

        java.util.List<Notification> notifications = NotificationDAO.getNotificationsByUserId(1); // userId = 1

        for (Notification notification : notifications) {
            String status = notification.isRead() ? "✓ Read" : "● New";
            String type = notification.getNotificationType().replace("_", " ");
            String title = notification.getTitle();
            String message = notification.getMessage();
            String dateTime = notification.getCreatedAt().toString().substring(0, 16); // Format: YYYY-MM-DD HH:MM

            model.addRow(new Object[]{status, type, title, message, dateTime});
        }

        // Update unread count
        int unreadCount = NotificationDAO.getUnreadCount(1);
        unreadLabel.setText(" (" + unreadCount + " unread) ");
    }

    private JPanel createSustainabilityPanel() {
        sustainabilityPanel = new JPanel(new GridBagLayout());
        sustainabilityPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("🌱 My Farm's Sustainability Score");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        sustainabilityPanel.add(titleLabel, gbc);

        // Carbon Reduction Score
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel carbonLabel = new JLabel("🌍 Carbon Reduction:");
        carbonLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sustainabilityPanel.add(carbonLabel, gbc);

        gbc.gridx = 1;
        carbonScoreLabel = new JLabel("85% - Excellent!");
        carbonScoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        carbonScoreLabel.setForeground(new Color(34, 139, 34));
        sustainabilityPanel.add(carbonScoreLabel, gbc);

        // Carbon Progress Bar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JProgressBar carbonBar = new JProgressBar(0, 100);
        carbonBar.setValue(85);
        carbonBar.setStringPainted(true);
        carbonBar.setPreferredSize(new Dimension(400, 30));
        carbonBar.setForeground(new Color(34, 139, 34));
        sustainabilityPanel.add(carbonBar, gbc);

        // Soil Health Status
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JLabel soilLabel = new JLabel("🌾 Soil Health Status:");
        soilLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sustainabilityPanel.add(soilLabel, gbc);

        gbc.gridx = 1;
        soilHealthLabel = new JLabel("Good - pH: 6.5, Organic: 4.2%");
        soilHealthLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        soilHealthLabel.setForeground(new Color(40, 167, 69));
        sustainabilityPanel.add(soilHealthLabel, gbc);

        // Soil Health Progress Bar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JProgressBar soilBar = new JProgressBar(0, 100);
        soilBar.setValue(75);
        soilBar.setStringPainted(true);
        soilBar.setPreferredSize(new Dimension(400, 30));
        soilBar.setForeground(new Color(40, 167, 69));
        sustainabilityPanel.add(soilBar, gbc);

        // Water Usage Graph
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        JLabel waterLabel = new JLabel("💧 Water Usage This Month:");
        waterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sustainabilityPanel.add(waterLabel, gbc);

        gbc.gridx = 1;
        JLabel waterValue = new JLabel("3,500 L / 5,000 L");
        waterValue.setFont(new Font("Arial", Font.PLAIN, 16));
        waterValue.setForeground(new Color(0, 123, 255));
        sustainabilityPanel.add(waterValue, gbc);

        // Water Usage Bar
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        waterUsageBar = new JProgressBar(0, 5000);
        waterUsageBar.setValue(3500);
        waterUsageBar.setStringPainted(true);
        waterUsageBar.setPreferredSize(new Dimension(400, 30));
        waterUsageBar.setForeground(new Color(0, 123, 255));
        sustainabilityPanel.add(waterUsageBar, gbc);

        // Tips Section
        gbc.gridy = 7;
        JTextArea tipsArea = new JTextArea(
                "💡 Sustainability Tips:\n" +
                "• Continue using organic fertilizers to improve soil health\n" +
                "• Consider drip irrigation to reduce water usage\n" +
                "• Crop rotation detected - great for carbon reduction!"
        );
        tipsArea.setEditable(false);
        tipsArea.setFont(new Font("Arial", Font.PLAIN, 13));
        tipsArea.setBackground(new Color(255, 252, 231));
        tipsArea.setBorder(BorderFactory.createLineBorder(new Color(255, 193, 7), 2));
        tipsArea.setLineWrap(true);
        tipsArea.setWrapStyleWord(true);
        sustainabilityPanel.add(tipsArea, gbc);

        return sustainabilityPanel;
    }

    private JPanel createProfilePanel() {
        profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);

        // Title
        JLabel titleLabel = new JLabel("👤 My Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        profilePanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Personal Details Section
        gbc.gridy = 1;
        JLabel personalLabel = new JLabel("Personal Details:");
        personalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        personalLabel.setForeground(new Color(34, 139, 34));
        gbc.gridwidth = 2;
        profilePanel.add(personalLabel, gbc);

        gbc.gridwidth = 1;

        // Name
        gbc.gridy = 2;
        gbc.gridx = 0;
        profilePanel.add(new JLabel("Full Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField("John Farmer", 20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 13));
        profilePanel.add(nameField, gbc);

        // Email
        gbc.gridy = 3;
        gbc.gridx = 0;
        profilePanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField("john.farmer@ecofarm.com", 20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 13));
        profilePanel.add(emailField, gbc);

        // Phone
        gbc.gridy = 4;
        gbc.gridx = 0;
        profilePanel.add(new JLabel("Phone:"), gbc);

        gbc.gridx = 1;
        phoneField = new JTextField("+1 234 567 8900", 20);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 13));
        profilePanel.add(phoneField, gbc);

        // Farm Details Section
        gbc.gridy = 5;
        gbc.gridx = 0;
        JLabel farmLabel = new JLabel("Farm Details:");
        farmLabel.setFont(new Font("Arial", Font.BOLD, 16));
        farmLabel.setForeground(new Color(34, 139, 34));
        gbc.gridwidth = 2;
        profilePanel.add(farmLabel, gbc);

        gbc.gridwidth = 1;

        // Farm Name
        gbc.gridy = 6;
        gbc.gridx = 0;
        profilePanel.add(new JLabel("Farm Name:"), gbc);

        gbc.gridx = 1;
        farmNameField = new JTextField("Green Valley Farm", 20);
        farmNameField.setFont(new Font("Arial", Font.PLAIN, 13));
        profilePanel.add(farmNameField, gbc);

        // Farm Size
        gbc.gridy = 7;
        gbc.gridx = 0;
        profilePanel.add(new JLabel("Farm Size (acres):"), gbc);

        gbc.gridx = 1;
        farmSizeField = new JTextField("50", 20);
        farmSizeField.setFont(new Font("Arial", Font.PLAIN, 13));
        profilePanel.add(farmSizeField, gbc);

        // Certifications Section
        gbc.gridy = 8;
        gbc.gridx = 0;
        JLabel certLabel = new JLabel("Certifications:");
        certLabel.setFont(new Font("Arial", Font.BOLD, 16));
        certLabel.setForeground(new Color(34, 139, 34));
        gbc.gridwidth = 2;
        profilePanel.add(certLabel, gbc);

        // Certifications Area
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        certificationsArea = new JTextArea(4, 20);
        certificationsArea.setText("• Organic Farming Certified (2022)\n• Sustainable Agriculture Certificate (2023)\n• Water Conservation Expert (2024)");
        certificationsArea.setFont(new Font("Arial", Font.PLAIN, 13));
        certificationsArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        certificationsArea.setLineWrap(true);
        certificationsArea.setWrapStyleWord(true);
        JScrollPane certScrollPane = new JScrollPane(certificationsArea);
        profilePanel.add(certScrollPane, gbc);

        // Save Button
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        saveProfileButton = new JButton("💾 Save Profile");
        saveProfileButton.setBackground(new Color(40, 167, 69));
        saveProfileButton.setForeground(Color.WHITE);
        saveProfileButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveProfileButton.setFocusPainted(false);
        saveProfileButton.setOpaque(true);
        saveProfileButton.setBorderPainted(false);
        saveProfileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveProfileButton.setPreferredSize(new Dimension(200, 40));
        profilePanel.add(saveProfileButton, gbc);

        return profilePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new farmerDashboard());
    }
}
