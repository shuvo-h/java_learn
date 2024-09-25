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

    // Abstract method to get detailed account information
    public abstract String getAccountDetails();

    // Abstract method to apply monthly interest specific to each account type
    public abstract void applyMonthlyInterest();

    // Abstract method to handle deposits
    public abstract void deposit(double amount);

    // Abstract method to handle withdrawals
    public abstract void withdraw(double amount);
}
