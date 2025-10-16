package view;

import controller.TransactionController;
import model.Account;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class DepositFrame extends JFrame {
    private DashboardFrame dashboardFrame;
    private Account account;
    private TransactionController transactionController;
    
    private JTextField amountField;
    private JButton confirmButton;
    private JButton cancelButton;
    
    public DepositFrame(DashboardFrame dashboardFrame, Account account) {
        this.dashboardFrame = dashboardFrame;
        this.account = account;
        this.transactionController = new TransactionController();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Deposit Money");
        setSize(740, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(dashboardFrame);
        setResizable(false);
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 204, 113));
        titlePanel.setPreferredSize(new Dimension(400, 70));
        JLabel titleLabel = new JLabel("Deposit Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        formPanel.setBackground(Color.WHITE);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        amountField = new JTextField(15);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        formPanel.add(amountLabel);
        formPanel.add(amountField);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonPanel.setBackground(Color.WHITE);
        
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(46, 204, 113));
        confirmButton.setForeground(Color.BLUE);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorderPainted(false);
        confirmButton.setOpaque(true);
        confirmButton.setPreferredSize(new Dimension(120, 35));
        confirmButton.addActionListener(e -> handleDeposit());
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(149, 165, 166));
        cancelButton.setForeground(Color.BLUE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setOpaque(true);
        cancelButton.setPreferredSize(new Dimension(120, 35));
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Enter key listener
        amountField.addActionListener(e -> handleDeposit());
    }
    
    private void handleDeposit() {
        String amountText = amountField.getText().trim();
        
        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter an amount!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            BigDecimal amount = new BigDecimal(amountText);
            
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Amount must be greater than zero!",
                    "Invalid Amount",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean success = transactionController.deposit(account.getAccountNumber(), amount);
            
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Deposit successful!\nAmount: ₹" + amount,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                dashboardFrame.refreshBalance();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Deposit failed! Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid number!",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}