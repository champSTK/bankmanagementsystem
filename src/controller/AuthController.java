package controller;

import model.User;
import service.BankService;

public class AuthController {
    private BankService bankService;
    
    public AuthController() {
        this.bankService = new BankService();
    }
    
    public User login(String email, String password) {
        return bankService.loginUser(email, password);
    }
    
    public boolean register(String name, String email, String password) {
        return bankService.registerUser(name, email, password);
    }
}