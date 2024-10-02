package cqu.customeraccountmanagement;

public abstract class Account {
    private String accountID;           // Unique account ID
    private String type;                // Type of account (e.g., Home Loan, Daily Access, Goal Saver)
    private double monthlyInterestRate; // Monthly interest rate for the account
    private String customerID;          // The ID of the customer who owns the account

    // Constructor to initialize the account details
    public Account(String accountID, String customerID, String type, double rate) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.type = type;
        this.monthlyInterestRate = rate;
    }

    // Getter for the account ID
    public String getAccountID() {
        return accountID;
    }

    // Getter for the type of account
    public String getType() {
        return type;
    }

    // Getter for the monthly interest rate
    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    // Getter for the customer ID
    public String getCustomerID() {
        return customerID;
    }

    // Abstract method to get detailed account information (must be implemented by subclasses)
    public abstract String getAccountDetails();

    // Abstract method to apply monthly interest specific to each account type (must be implemented by subclasses)
    public abstract void applyMonthlyInterest();

    // Abstract method to handle deposits (must be implemented by subclasses)
    public abstract void deposit(double amount);

    // Abstract method to handle withdrawals (must be implemented by subclasses)
    public abstract void withdraw(double amount);
}
