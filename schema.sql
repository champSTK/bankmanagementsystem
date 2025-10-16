-- Create Database
CREATE DATABASE IF NOT EXISTS bank_system;
USE bank_system;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Accounts Table
CREATE TABLE IF NOT EXISTS accounts (
    account_number INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    balance DECIMAL(10,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Transactions Table
CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number INT NOT NULL,
    type ENUM('deposit', 'withdraw') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number) ON DELETE CASCADE
);

-- Indexes for better performance
CREATE INDEX idx_user_email ON users(email);
CREATE INDEX idx_account_user ON accounts(user_id);
CREATE INDEX idx_transaction_account ON transactions(account_number);