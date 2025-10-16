package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Account {
    private int accountNumber;
    private int userId;
    private BigDecimal balance;
    private Timestamp createdAt;
    
    public Account() {}
    
    public Account(int accountNumber, int userId, BigDecimal balance, Timestamp createdAt) {
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.balance = balance;
        this.createdAt = createdAt;
    }
    
    public Account(int userId) {
        this.userId = userId;
        this.balance = BigDecimal.ZERO;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}