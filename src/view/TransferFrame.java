package view;

import controller.TransactionController;
import model.Account;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class TransferFrame extends JFrame {
    private DashboardFrame dashboardFrame;
    private Account account;
    private TransactionController transactionController;
    
    private JTextField toAccountField;
    private JTextField amountField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JButton verifyButton;
    private JLabel recipientInfoLabel;
    
    public TransferFrame(DashboardFrame dashboardFrame, Account account) {
        this.dashboardFrame = dashboardFrame;
        this.account = account;
        this.transactionController = new TransactionController();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Transfer Money");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(dashboardFrame);
        setResizable(false);
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBackground(Color.WHITE);
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(155, 89, 182));
        titlePanel.setPreferredSize(new Dimension(500, 80));
        JLabel titleLabel = new JLabel("Transfer Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Center Panel with form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null); // Using absolute positioning for reliability
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(500, 320));
        
        // From Account Label
        JLabel fromTitleLabel = new JLabel("From Account:");
        fromTitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        fromTitleLabel.setBounds(50, 30, 120, 25);
        centerPanel.add(fromTitleLabel);
        
        JLabel fromAccountLabel = new JLabel(String.valueOf(account.getAccountNumber()));
        fromAccountLabel.setFont(new Font("Arial", Font.BOLD, 15));
        fromAccountLabel.setForeground(new Color(52, 73, 94));
        fromAccountLabel.setBounds(180, 30, 250, 25);
        centerPanel.add(fromAccountLabel);
        
        // To Account Label
        JLabel toTitleLabel = new JLabel("To Account #:");
        toTitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        toTitleLabel.setBounds(50, 70, 120, 25);
        centerPanel.add(toTitleLabel);
        
        // To Account Field
        toAccountField = new JTextField();
        toAccountField.setFont(new Font("Arial", Font.PLAIN, 15));
        toAccountField.setBounds(180, 70, 250, 30);
        centerPanel.add(toAccountField);
        
        // Verify Button
        verifyButton = new JButton("Verify Account");
        verifyButton.setFont(new Font("Arial", Font.PLAIN, 13));
        verifyButton.setBackground(new Color(52, 152, 219));
        verifyButton.setForeground(Color.BLUE);
        verifyButton.setFocusPainted(false);
        verifyButton.setBorderPainted(false);
        verifyButton.setOpaque(true);
        verifyButton.setBounds(175, 110, 150, 35);
        verifyButton.addActionListener(e -> verifyAccount());
        centerPanel.add(verifyButton);
        
        // Recipient Info Label
        recipientInfoLabel = new JLabel(" ");
        recipientInfoLabel.setFont(new Font("Arial", Font.ITALIC, 13));
        recipientInfoLabel.setForeground(new Color(39, 174, 96));
        recipientInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recipientInfoLabel.setBounds(50, 150, 400, 25);
        centerPanel.add(recipientInfoLabel);
        
        // Amount Label
        JLabel amountTitleLabel = new JLabel("Amount:");
        amountTitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        amountTitleLabel.setBounds(50, 190, 120, 25);
        centerPanel.add(amountTitleLabel);
        
        // Amount Field
        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 15));
        amountField.setBounds(180, 190, 250, 30);
        centerPanel.add(amountField);
        
        // Balance Info Label
        JLabel balanceInfoLabel = new JLabel("Available Balance: $" + account.getBalance());
        balanceInfoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        balanceInfoLabel.setForeground(new Color(127, 140, 141));
        balanceInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceInfoLabel.setBounds(50, 230, 400, 25);
        centerPanel.add(balanceInfoLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(500, 80));
        
        confirmButton = new JButton("Transfer");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 15));
        confirmButton.setBackground(new Color(155, 89, 182));
        confirmButton.setForeground(Color.BLUE);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorderPainted(false);
        confirmButton.setOpaque(true);
        confirmButton.setPreferredSize(new Dimension(140, 40));
        confirmButton.addActionListener(e -> handleTransfer());
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
        cancelButton.setBackground(new Color(149, 165, 166));
        cancelButton.setForeground(Color.BLUE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setOpaque(true);
        cancelButton.setPreferredSize(new Dimension(140, 40));
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void verifyAccount() {
        String toAccountText = toAccountField.getText().trim();
        
        if (toAccountText.isEmpty()) {
            recipientInfoLabel.setText("Please enter an account number");
            recipientInfoLabel.setForeground(Color.RED);
            return;
        }
        
        try {
            int toAccountNumber = Integer.parseInt(toAccountText);
            
            if (toAccountNumber == account.getAccountNumber()) {
                recipientInfoLabel.setText("✗ Cannot transfer to your own account");
                recipientInfoLabel.setForeground(Color.RED);
                return;
            }
            
            Account recipientAccount = transactionController.getAccountByAccountNumber(toAccountNumber);
            
            if (recipientAccount != null) {
                recipientInfoLabel.setText("✓ Valid account found");
                recipientInfoLabel.setForeground(new Color(39, 174, 96));
            } else {
                recipientInfoLabel.setText("✗ Account not found");
                recipientInfoLabel.setForeground(Color.RED);
            }
            
        } catch (NumberFormatException e) {
            recipientInfoLabel.setText("✗ Invalid account number");
            recipientInfoLabel.setForeground(Color.RED);
        }
    }
    
    private void handleTransfer() {
        String toAccountText = toAccountField.getText().trim();
        String amountText = amountField.getText().trim();
        
        if (toAccountText.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both account number and amount!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int toAccountNumber = Integer.parseInt(toAccountText);
            BigDecimal amount = new BigDecimal(amountText);
            
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Amount must be greater than zero!",
                    "Invalid Amount",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (toAccountNumber == account.getAccountNumber()) {
                JOptionPane.showMessageDialog(this,
                    "Cannot transfer to your own account!",
                    "Invalid Transfer",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Confirm transfer
            int confirm = JOptionPane.showConfirmDialog(this,
                "Transfer $" + amount + " to account #" + toAccountNumber + "?",
                "Confirm Transfer",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            boolean success = transactionController.transfer(
                account.getAccountNumber(), 
                toAccountNumber, 
                amount
            );
            
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Transfer successful!\nAmount: $" + amount + "\nTo Account: #" + toAccountNumber,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                dashboardFrame.refreshBalance();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Transfer failed!\nPlease check:\n" +
                    "- Recipient account exists\n" +
                    "- Sufficient funds available\n" +
                    "- Amount is valid",
                    "Transfer Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numbers!",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}