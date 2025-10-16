package controller;

import model.Transaction;
import service.BankService;
import model.Account;

import java.math.BigDecimal;
import java.util.List;

public class TransactionController {
    private BankService bankService;
    
    public TransactionController() {
        this.bankService = new BankService();
    }
    
    public boolean deposit(int accountNumber, BigDecimal amount) {
        return bankService.deposit(accountNumber, amount);
    }
    
    public boolean withdraw(int accountNumber, BigDecimal amount) {
        return bankService.withdraw(accountNumber, amount);
    }
    
    public List<Transaction> getTransactionHistory(int accountNumber) {
        return bankService.getTransactionHistory(accountNumber);
    }
    
    public boolean transfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount) {
        return bankService.transfer(fromAccountNumber, toAccountNumber, amount);
    }
    
    public Account getAccountByAccountNumber(int accountNumber) {
        return bankService.getAccountByAccountNumber(accountNumber);
    }
}