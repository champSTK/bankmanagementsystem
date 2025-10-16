package controller;

import model.Account;
import service.BankService;

import java.math.BigDecimal;

public class AccountController {
    private BankService bankService;
    
    public AccountController() {
        this.bankService = new BankService();
    }
    
    public Account getAccountByUserId(int userId) {
        return bankService.getAccountByUserId(userId);
    }
    
    public BigDecimal checkBalance(int accountNumber) {
        return bankService.checkBalance(accountNumber);
    }
}