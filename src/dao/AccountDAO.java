package dao;

import model.Account;
import util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;

public class AccountDAO {
    
    public boolean createAccount(Account account) {
        String query = "INSERT INTO accounts (user_id, balance) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, account.getUserId());
            stmt.setBigDecimal(2, BigDecimal.ZERO);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error creating account: " + e.getMessage());
            return false;
        }
    }
    
    public Account getAccountByUserId(int userId) {
        String query = "SELECT * FROM accounts WHERE user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                        rs.getInt("account_number"),
                        rs.getInt("user_id"),
                        rs.getBigDecimal("balance"),
                        rs.getTimestamp("created_at")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error fetching account: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean updateBalance(int accountNumber, BigDecimal newBalance) {
        String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setBigDecimal(1, newBalance);
            stmt.setInt(2, accountNumber);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating balance: " + e.getMessage());
            return false;
        }
    }
    
    public BigDecimal getBalance(int accountNumber) {
        String query = "SELECT balance FROM accounts WHERE account_number = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, accountNumber);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("balance");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error fetching balance: " + e.getMessage());
        }
        
        return null;
    }
    
    public Account getAccountByAccountNumber(int accountNumber) {
        String query = "SELECT * FROM accounts WHERE account_number = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, accountNumber);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                        rs.getInt("account_number"),
                        rs.getInt("user_id"),
                        rs.getBigDecimal("balance"),
                        rs.getTimestamp("created_at")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error fetching account: " + e.getMessage());
        }
        
        return null;
    }
}