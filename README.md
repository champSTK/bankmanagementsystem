# Bank Management System 🏦

A robust and user-friendly Bank Management System built with Java. This application provides core banking functionalities for managing customer accounts and transactions through a clean graphical user interface (GUI).

## 📜 Description

This project is a desktop-based application that simulates a real-world banking environment. It is designed to handle essential operations such as creating new accounts, processing deposits and withdrawals, checking account balances, and transferring funds. The system uses a MySQL database to ensure data persistence and integrity.

This project serves as an excellent demonstration of Object-Oriented Programming (OOP) principles and database integration in a Java application.

## ✨ Key Features

* **User Authentication:** Secure login for bank staff or administrators.
* **Create New Account:** Onboard new customers by creating new bank accounts with unique account numbers.
* **Deposit Funds:** Add money to any existing customer account.
* **Withdraw Funds:** Withdraw money from an account, with automated checks for sufficient balance.
* **Balance Inquiry:** Instantly check the current balance of any account.
* **Fund Transfer:** Securely transfer funds from one account to another.
* **Database Integration:** All transaction and account data is securely stored and managed in a MySQL database.

## 🛠️ Technologies Used

* **Programming Language:** Java
* **GUI Framework:** Java Swing / AWT
* **Database:** MySQL
* **Database Connectivity:** JDBC (Java Database Connectivity) API
* **IDE:** VS Code, Eclipse, or IntelliJ IDEA

## ⚙️ How to Get Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

Make sure you have the following software installed:

* **Java Development Kit (JDK):** Version 8 or higher.
* **MySQL Server:** To host the application's database.
* **MySQL JDBC Driver:** A `.jar` file to allow the Java application to connect to the database.
* An IDE like **Eclipse**, **IntelliJ IDEA**, or **VS Code** with Java extensions.

### Installation and Setup

1.  **Clone the Repository:**
    ```sh
    git clone [https://github.com/champSTK/bankmanagementsystem.git](https://github.com/champSTK/bankmanagementsystem.git)
    ```

2.  **Navigate to the Project Directory:**
    ```sh
    cd bankmanagementsystem
    ```

3.  **Database Setup:**
    * Open your MySQL command line or a GUI tool like MySQL Workbench.
    * Create a new database for the project.
        ```sql
        CREATE DATABASE bank_system;
        ```
    * Use the new database.
        ```sql
        USE bank_system;
        ```
    * Run any provided SQL scripts to create the necessary tables (e.g., `accounts`, `transactions`).

4.  **Configure JDBC Driver & Connection:**
    * Open the project in your IDE.
    * Add the MySQL JDBC Connector `.jar` file to your project's build path or libraries.
    * Locate the **`db_connection.java`** file.
    * Inside this file, you must update the database connection details. **Find the line for the password and enter your own MySQL root password.**

5.  **Run the Application:**
    * Locate the main entry point of the application (`MainApp.java).
    * Compile and run this file from your IDE to start the application.
