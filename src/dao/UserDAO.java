package dao;

import model.User;
import util.DBConnection;

import java.sql.*;

public class UserDAO {
    
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry
                System.err.println("Error: Email already exists!");
            } else {
                System.err.println("Error registering user: " + e.getMessage());
            }
            return false;
        }
    }
    
    public User loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error logging in: " + e.getMessage());
        }
        
        return null;
    }
    
    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
        }
        
        return null;
    }
}