package cqu.assignment2.phase1;

public abstract class Account {
    private String accountID;
    private String type;
    private double monthlyInterestRate;
    private String customerID;

    // Constructor
    public Account(String accountID, String customerID, String type, double rate) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.type = type;
        this.monthlyInterestRate = rate;
    }

    // Getter methods
    public String getAccountID() {
        return accountID;
    }

    public String getType() {
        return type;
    }

    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public String getCustomerID() {
        return customerID;
    }

    // Abstract methods to be implemented by subclasses
    public abstract String getAccountDetails();
    public abstract void applyMonthlyInterest();
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
}
