import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login {

    public static void main(String[] args) {
        // Initialize database and create tables if not exists
        System.out.println("Initializing database...");
        UserDAO.initializeDatabase();

        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> createLoginUI());
    }

    private static void createLoginUI() {
        // Create frame
        JFrame frame = new JFrame("Agriculture App - Login");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background

        // Title Panel (Green header)
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 400, 80);
        headerPanel.setBackground(new Color(34, 139, 34)); // Forest green
        headerPanel.setLayout(null);
        frame.add(headerPanel);

        // App Icon/Emoji
        JLabel iconLabel = new JLabel("🌾", SwingConstants.CENTER);
        iconLabel.setBounds(150, 10, 100, 40);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        headerPanel.add(iconLabel);

        // Title Label
        JLabel titleLabel = new JLabel("Agriculture App", SwingConstants.CENTER);
        titleLabel.setBounds(50, 50, 300, 25);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Welcome text
        JLabel welcomeLabel = new JLabel("Please login to continue", SwingConstants.CENTER);
        welcomeLabel.setBounds(50, 100, 300, 25);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeLabel.setForeground(new Color(100, 100, 100));
        frame.add(welcomeLabel);

        // Username Label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 145, 100, 25);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(userLabel);

        // Username TextField
        JTextField userText = new JTextField();
        userText.setBounds(50, 170, 300, 35);
        userText.setFont(new Font("Arial", Font.PLAIN, 14));
        userText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        frame.add(userText);

        // Password Label
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 215, 100, 25);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(passLabel);

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 240, 300, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        frame.add(passwordField);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 290, 300, 35);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBackground(new Color(34, 139, 34)); // Forest green
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(loginBtn);

        // Button Action
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText().trim();
                String password = new String(passwordField.getPassword());

                // Validate input
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter both username and password!",
                            "Input Required",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Authenticate user from database
                User user = UserDAO.authenticateUser(username, password);

                if (user != null) {
                    // Login successful
                    JOptionPane.showMessageDialog(frame,
                            "Login Successful!\nWelcome, " + user.getFullName() + "!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Open appropriate dashboard based on user type
                    frame.dispose();

                    if ("admin".equals(user.getUserType())) {
                        // Open Admin Dashboard
                        openAdminDashboard(user);
                    } else if ("farmer".equals(user.getUserType())) {
                        // Open Farmer Dashboard
                        openFarmerDashboard(user);
                    }

                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(frame,
                            "Invalid username or password!\nPlease try again.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                    passwordField.setText(""); // Clear password field
                }
            }
        });

        // Enter key support
        passwordField.addActionListener(e -> loginBtn.doClick());

        frame.setVisible(true);
    }

    private static void openAdminDashboard(User user) {
        // Open the Admin Dashboard UI (with notification functionality)
        new adminDashboard(user.getFullName());
    }

    private static void openFarmerDashboard(User user) {
        // Open the new Farmer Dashboard UI
        new farmerDashboard();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
}
