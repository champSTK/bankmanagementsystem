package view;

import controller.AccountController;
import model.Account;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class DashboardFrame extends JFrame {
    private User currentUser;
    private Account currentAccount;
    private AccountController accountController;
    
    private JLabel balanceLabel;
    private JLabel accountNumberLabel;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton transactionHistoryButton;
    private JButton logoutButton;
    
    public DashboardFrame(User user) {
        this.currentUser = user;
        this.accountController = new AccountController();
        this.currentAccount = accountController.getAccountByUserId(user.getId());
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Bank System - Dashboard");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(700, 100));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(new Color(41, 128, 185));
        
        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        
        accountNumberLabel = new JLabel("Account #" + currentAccount.getAccountNumber());
        accountNumberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        accountNumberLabel.setForeground(Color.WHITE);
        
        userInfoPanel.add(welcomeLabel);
        userInfoPanel.add(Box.createVerticalStrut(8));
        userInfoPanel.add(accountNumberLabel);
        
        headerPanel.add(userInfoPanel, BorderLayout.WEST);
        
        // Center Panel (Balance + Buttons)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Balance Card Panel
        JPanel balanceCardPanel = new JPanel();
        balanceCardPanel.setLayout(new BoxLayout(balanceCardPanel, BoxLayout.Y_AXIS));
        balanceCardPanel.setBackground(new Color(236, 240, 241));
        balanceCardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        balanceCardPanel.setMaximumSize(new Dimension(600, 150));
        
        JLabel balanceTitleLabel = new JLabel("Current Balance");
        balanceTitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        balanceTitleLabel.setForeground(new Color(52, 73, 94));
        balanceTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        balanceLabel = new JLabel("₹" + currentAccount.getBalance());
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 48));
        balanceLabel.setForeground(new Color(39, 174, 96));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        balanceCardPanel.add(balanceTitleLabel);
        balanceCardPanel.add(Box.createVerticalStrut(15));
        balanceCardPanel.add(balanceLabel);
        
        // Add vertical spacing
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(balanceCardPanel);
        centerPanel.add(Box.createVerticalStrut(30));
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setMaximumSize(new Dimension(600, 240));
        
        depositButton = createStyledButton("Deposit", new Color(46, 204, 113));
        depositButton.setForeground(Color.BLUE);
        depositButton.addActionListener(e -> openDepositFrame());
        
        withdrawButton = createStyledButton("Withdraw", new Color(231, 76, 60));
        withdrawButton.setForeground(Color.BLUE);
        withdrawButton.addActionListener(e -> openWithdrawFrame());
        
        transferButton = createStyledButton("Transfer", new Color(155, 89, 182));
        transferButton.setForeground(Color.BLUE);
        transferButton.addActionListener(e -> openTransferFrame());
        
        transactionHistoryButton = createStyledButton("Transaction History", new Color(52, 152, 219));
        transactionHistoryButton.setForeground(Color.BLUE);
        transactionHistoryButton.addActionListener(e -> openTransactionHistoryFrame());
        
        logoutButton = createStyledButton("Logout", new Color(149, 165, 166));
        logoutButton.setForeground(Color.BLUE);
        logoutButton.addActionListener(e -> handleLogout());
        
        // Add empty panel for 6th position
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.WHITE);
        
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(transactionHistoryButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(emptyPanel);
        
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        
        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(250, 70));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private void openDepositFrame() {
        DepositFrame depositFrame = new DepositFrame(this, currentAccount);
        depositFrame.setVisible(true);
    }
    
    private void openWithdrawFrame() {
        WithdrawFrame withdrawFrame = new WithdrawFrame(this, currentAccount);
        withdrawFrame.setVisible(true);
    }
    
    private void openTransferFrame() {
        TransferFrame transferFrame = new TransferFrame(this, currentAccount);
        transferFrame.setVisible(true);
    }
    
    private void openTransactionHistoryFrame() {
        TransactionHistoryFrame historyFrame = new TransactionHistoryFrame(currentAccount);
        historyFrame.setVisible(true);
    }
    
    private void handleLogout() {
        int choice = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Logout Confirmation",
            JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.dispose();
        }
    }
    
    public void refreshBalance() {
        currentAccount = accountController.getAccountByUserId(currentUser.getId());
        balanceLabel.setText("₹" + currentAccount.getBalance());
    }
}