package dao;

import model.Transaction;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    
    public boolean addTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions (account_number, type, amount) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, transaction.getAccountNumber());
            stmt.setString(2, transaction.getType());
            stmt.setBigDecimal(3, transaction.getAmount());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding transaction: " + e.getMessage());
            return false;
        }
    }
    
    public List<Transaction> getTransactionsByAccountNumber(int accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE account_number = ? ORDER BY timestamp DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, accountNumber);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getInt("account_number"),
                        rs.getString("type"),
                        rs.getBigDecimal("amount"),
                        rs.getTimestamp("timestamp")
                    ));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error fetching transactions: " + e.getMessage());
        }
        
        return transactions;
    }
}