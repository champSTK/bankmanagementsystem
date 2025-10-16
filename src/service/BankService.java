package service;

import dao.AccountDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import model.Account;
import model.Transaction;
import model.User;

import java.math.BigDecimal;
import java.util.List;

public class BankService {
    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;
    
    public BankService() {
        this.userDAO = new UserDAO();
        this.accountDAO = new AccountDAO();
        this.transactionDAO = new TransactionDAO();
    }
    
    public boolean registerUser(String name, String email, String password) {
        User user = new User(name, email, password);
        boolean userCreated = userDAO.registerUser(user);
        
        if (userCreated) {
            User registeredUser = userDAO.loginUser(email, password);
            if (registeredUser != null) {
                Account account = new Account(registeredUser.getId());
                return accountDAO.createAccount(account);
            }
        }
        
        return false;
    }
    
    public User loginUser(String email, String password) {
        return userDAO.loginUser(email, password);
    }
    
    public Account getAccountByUserId(int userId) {
        return accountDAO.getAccountByUserId(userId);
    }
    
    public boolean deposit(int accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Error: Deposit amount must be positive!");
            return false;
        }
        
        BigDecimal currentBalance = accountDAO.getBalance(accountNumber);
        if (currentBalance == null) {
            System.out.println("Error: Account not found!");
            return false;
        }
        
        BigDecimal newBalance = currentBalance.add(amount);
        boolean balanceUpdated = accountDAO.updateBalance(accountNumber, newBalance);
        
        if (balanceUpdated) {
            Transaction transaction = new Transaction(accountNumber, "deposit", amount);
            return transactionDAO.addTransaction(transaction);
        }
        
        return false;
    }
    
    public boolean withdraw(int accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Error: Withdrawal amount must be positive!");
            return false;
        }
        
        BigDecimal currentBalance = accountDAO.getBalance(accountNumber);
        if (currentBalance == null) {
            System.out.println("Error: Account not found!");
            return false;
        }
        
        if (currentBalance.compareTo(amount) < 0) {
            System.out.println("Error: Insufficient funds!");
            return false;
        }
        
        BigDecimal newBalance = currentBalance.subtract(amount);
        boolean balanceUpdated = accountDAO.updateBalance(accountNumber, newBalance);
        
        if (balanceUpdated) {
            Transaction transaction = new Transaction(accountNumber, "withdraw", amount);
            return transactionDAO.addTransaction(transaction);
        }
        
        return false;
    }
    
    public BigDecimal checkBalance(int accountNumber) {
        return accountDAO.getBalance(accountNumber);
    }
    
    public List<Transaction> getTransactionHistory(int accountNumber) {
        return transactionDAO.getTransactionsByAccountNumber(accountNumber);
    }
    
    public boolean transfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Error: Transfer amount must be positive!");
            return false;
        }
        
        if (fromAccountNumber == toAccountNumber) {
            System.out.println("Error: Cannot transfer to the same account!");
            return false;
        }
        
        // Check if recipient account exists
        BigDecimal toAccountBalance = accountDAO.getBalance(toAccountNumber);
        if (toAccountBalance == null) {
            System.out.println("Error: Recipient account not found!");
            return false;
        }
        
        // Check sender balance
        BigDecimal fromAccountBalance = accountDAO.getBalance(fromAccountNumber);
        if (fromAccountBalance == null) {
            System.out.println("Error: Sender account not found!");
            return false;
        }
        
        if (fromAccountBalance.compareTo(amount) < 0) {
            System.out.println("Error: Insufficient funds!");
            return false;
        }
        
        // Perform transfer (deduct from sender)
        BigDecimal newFromBalance = fromAccountBalance.subtract(amount);
        boolean fromUpdated = accountDAO.updateBalance(fromAccountNumber, newFromBalance);
        
        if (!fromUpdated) {
            System.out.println("Error: Failed to deduct from sender account!");
            return false;
        }
        
        // Add to recipient
        BigDecimal newToBalance = toAccountBalance.add(amount);
        boolean toUpdated = accountDAO.updateBalance(toAccountNumber, newToBalance);
        
        if (!toUpdated) {
            // Rollback sender's balance
            accountDAO.updateBalance(fromAccountNumber, fromAccountBalance);
            System.out.println("Error: Failed to credit recipient account!");
            return false;
        }
        
        // Record transactions
        Transaction withdrawTransaction = new Transaction(fromAccountNumber, "withdraw", amount);
        Transaction depositTransaction = new Transaction(toAccountNumber, "deposit", amount);
        
        transactionDAO.addTransaction(withdrawTransaction);
        transactionDAO.addTransaction(depositTransaction);
        
        return true;
    }
    
    public Account getAccountByAccountNumber(int accountNumber) {
        return accountDAO.getAccountByAccountNumber(accountNumber);
    }
}