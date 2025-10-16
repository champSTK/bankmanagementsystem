package view;

import controller.TransactionController;
import model.Account;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TransactionHistoryFrame extends JFrame {
    private Account account;
    private TransactionController transactionController;
    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JButton closeButton;
    
    public TransactionHistoryFrame(Account account) {
        this.account = account;
        this.transactionController = new TransactionController();
        initComponents();
        loadTransactions();
    }
    
    private void initComponents() {
        setTitle("Transaction History");
        setSize(740, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(52, 152, 219));
        titlePanel.setPreferredSize(new Dimension(700, 80));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        JLabel titleLabel = new JLabel("Transaction History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel accountLabel = new JLabel("Account #" + account.getAccountNumber());
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        accountLabel.setForeground(Color.WHITE);
        
        JPanel titleTextPanel = new JPanel();
        titleTextPanel.setLayout(new BoxLayout(titleTextPanel, BoxLayout.Y_AXIS));
        titleTextPanel.setBackground(new Color(52, 152, 219));
        titleTextPanel.add(titleLabel);
        titleTextPanel.add(Box.createVerticalStrut(5));
        titleTextPanel.add(accountLabel);
        
        titlePanel.add(titleTextPanel, BorderLayout.WEST);
        
        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create table model
        String[] columnNames = {"ID", "Type", "Amount", "Date & Time"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        transactionTable = new JTable(tableModel);
        transactionTable.setFont(new Font("Arial", Font.PLAIN, 13));
        transactionTable.setRowHeight(30);
        transactionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transactionTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        transactionTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        transactionTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        transactionTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        
        // Style table header
        JTableHeader header = transactionTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(236, 240, 241));
        header.setForeground(new Color(52, 73, 94));
        
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonPanel.setBackground(Color.WHITE);
        
        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(new Color(149, 165, 166));
        closeButton.setForeground(Color.BLUE);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setOpaque(true);
        closeButton.setPreferredSize(new Dimension(120, 35));
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(closeButton);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadTransactions() {
        List<Transaction> transactions = transactionController.getTransactionHistory(account.getAccountNumber());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // Clear existing rows
        tableModel.setRowCount(0);
        
        if (transactions.isEmpty()) {
            JLabel noDataLabel = new JLabel("No transactions found.");
            noDataLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            noDataLabel.setForeground(Color.GRAY);
            noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            for (Transaction t : transactions) {
                String type = t.getType().substring(0, 1).toUpperCase() + t.getType().substring(1);
                String amount = "₹" + t.getAmount();
                String timestamp = dateFormat.format(t.getTimestamp());
                
                Object[] row = {t.getId(), type, amount, timestamp};
                tableModel.addRow(row);
            }
        }
    }
}