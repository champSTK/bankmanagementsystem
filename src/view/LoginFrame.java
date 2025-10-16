package view;

import controller.AuthController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private AuthController authController;
    
    public LoginFrame() {
        authController = new AuthController();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Bank System - Login");
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
        titlePanel.setBackground(new Color(41, 128, 185));
        titlePanel.setPreferredSize(new Dimension(400, 80));
        JLabel titleLabel = new JLabel("Bank Management System");
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
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Email Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(emailLabel, gbc);
        
        // Email Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(emailField, gbc);
        
        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(passwordLabel, gbc);
        
        // Password Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(passwordField, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.BLUE);
        loginButton.setFocusPainted(false);
        //loginButton.setBorderPainted(false);
        //loginButton.setOpaque(true);
        loginButton.setPreferredSize(new Dimension(120, 35));
        loginButton.addActionListener(e -> handleLogin());
        
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(new Color(46, 204, 113));
        registerButton.setForeground(Color.BLUE);
        registerButton.setFocusPainted(false);
        //registerButton.setBorderPainted(false);
        //registerButton.setOpaque(true);
        registerButton.setPreferredSize(new Dimension(120, 35));
        registerButton.addActionListener(e -> openRegisterFrame());
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Enter key listener for password field
        passwordField.addActionListener(e -> handleLogin());
    }
    
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both email and password!",
                "Login Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user = authController.login(email, password);
        
        if (user != null) {
            JOptionPane.showMessageDialog(this,
                "Login successful! Welcome, " + user.getName(),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Open Dashboard
            DashboardFrame dashboard = new DashboardFrame(user);
            dashboard.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid email or password!",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
    
    private void openRegisterFrame() {
        RegisterFrame registerFrame = new RegisterFrame(this);
        registerFrame.setVisible(true);
        this.setVisible(false);
    }
}