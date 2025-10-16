package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int accountNumber;
    private String type;
    private BigDecimal amount;
    private Timestamp timestamp;
    
    public Transaction() {}
    
    public Transaction(int id, int accountNumber, String type, BigDecimal amount, Timestamp timestamp) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }
    
    public Transaction(int accountNumber, String type, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}