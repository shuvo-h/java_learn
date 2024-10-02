package cqu.customeraccountmanagement;

public class DailyAccessAccount extends Account {
    private double balance;            // The current balance of the account
    private double minimumBalance;     // The minimum balance since the last interest was applied
    private double interestEarned;     // The last interest earned

    // Constructor
    public DailyAccessAccount(String accountID, String customerID, double interestRate, double balance) {
        super(accountID, customerID, "Daily Access", interestRate);
        this.balance = balance;
        this.minimumBalance = balance; // Initially, the minimum balance is the same as the current balance
        this.interestEarned = 0.0;     // No interest earned initially
    }

    // Get detailed information about the account using StringBuilder
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Current Balance: $%.2f%n", balance));
        sb.append(String.format("Minimum Balance: $%.2f%n", minimumBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Convert monthly to annual rate
        return sb.toString();
    }

    

    // Getter for current balance
    public double getBalance() {
        return balance;
    }

    // Getter for minimum balance
    public double getMinimumBalance() {
        return minimumBalance;
    }

    // Getter for interest earned
    public double getInterestEarned() {
        return interestEarned;
    }
}
