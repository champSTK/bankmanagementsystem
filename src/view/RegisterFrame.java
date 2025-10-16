package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backButton;
    private AuthController authController;
    private LoginFrame loginFrame;
    
    public RegisterFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        this.authController = new AuthController();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Bank System - Register");
        setSize(740, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 204, 113));
        titlePanel.setPreferredSize(new Dimension(450, 80));
        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);
        
        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameField, gbc);
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(emailLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(emailField, gbc);
        
        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(passwordLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(passwordField, gbc);
        
        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        JLabel confirmLabel = new JLabel("Confirm:");
        confirmLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(confirmLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(confirmPasswordField, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonPanel.setBackground(Color.WHITE);
        
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(new Color(46, 204, 113));
        registerButton.setForeground(Color.BLUE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setOpaque(true);
        registerButton.setPreferredSize(new Dimension(120, 35));
        registerButton.addActionListener(e -> handleRegister());
        
        backButton = new JButton("Back to Login");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(149, 165, 166));
        backButton.setForeground(Color.BLUE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setOpaque(true);
        backButton.setPreferredSize(new Dimension(140, 35));
        backButton.addActionListener(e -> backToLogin());
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void handleRegister() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        // Validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "All fields are required!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid email address!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this,
                "Password must be at least 6 characters long!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                "Passwords do not match!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Register user
        boolean success = authController.register(name, email, password);
        
        if (success) {
            JOptionPane.showMessageDialog(this,
                "Registration successful!\nYou can now login with your credentials.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            backToLogin();
        } else {
            JOptionPane.showMessageDialog(this,
                "Registration failed!\nEmail may already exist.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void backToLogin() {
        loginFrame.setVisible(true);
        this.dispose();
    }
}