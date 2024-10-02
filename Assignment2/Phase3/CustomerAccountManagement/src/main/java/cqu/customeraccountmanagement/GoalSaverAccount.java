package cqu.customeraccountmanagement;

public class GoalSaverAccount extends Account {
    private double balance;                // The current balance in the account
    private double startOfMonthBalance;    // Balance at the start of the month (for interest eligibility)
    private double interestEarned;         // The last amount of interest earned

    // Constructor
    public GoalSaverAccount(String accountID, String customerID, double interestRate, double balance) {
        super(accountID, customerID, "Goal Saver", interestRate);
        this.balance = balance;
        this.startOfMonthBalance = balance; // Initially, the start of month balance is the same as the current balance
        this.interestEarned = 0.0;          // No interest earned initially
    }

    // Get detailed information about the account using StringBuilder
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Current Balance: $%.2f%n", balance));
        sb.append(String.format("Start of Month Balance: $%.2f%n", startOfMonthBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Convert monthly to annual rate
        return sb.toString();
    }

    // Apply monthly interest based on balance growth condition
    @Override
    public void applyMonthlyInterest() {
       
    }

    // Deposit money to the account
    @Override
    public void deposit(double amount) {
       
    }

    // Withdraw money from the account
    @Override
    public void withdraw(double amount) {
        
    }

    // Getter for current balance
    public double getBalance() {
        return balance;
    }

    // Getter for start of month balance
    public double getStartOfMonthBalance() {
        return startOfMonthBalance;
    }

    // Getter for last interest earned
    public double getInterestEarned() {
        return interestEarned;
    }
}
