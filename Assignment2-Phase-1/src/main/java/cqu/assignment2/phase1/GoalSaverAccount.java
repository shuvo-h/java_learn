package cqu.assignment2.phase1;

public class GoalSaverAccount extends Account {
    private double balance;
    private double startOfMonthBalance; // Balance at the start of the month (used to track interest eligibility)
    private double interestEarned;

    // Constructor
    public GoalSaverAccount(String accountID, String customerID, String type, double interestRate, double balance, double startOfMonthBalance) {
        super(accountID, customerID, type, interestRate);
        this.balance = balance;
        this.startOfMonthBalance = startOfMonthBalance; // Set initial start of month balance
        this.interestEarned = 0.0;
    }

    // Get detailed information about the account using StringBuilder
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Current Balance: $%.2f%n", balance));
        sb.append(String.format("Start of Month Balance: $%.2f%n", startOfMonthBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Annual rate
        return sb.toString();
    }

    // Apply monthly interest
    @Override
    public void applyMonthlyInterest() {
        // Interest is only applied if the balance is $500 more than the start of the month balance
        if (balance >= startOfMonthBalance + 500) {
            double interest = balance * getMonthlyInterestRate();
            interestEarned = interest;
            balance += interest; // Add interest to the current balance
        }
        // Reset the startOfMonthBalance after interest is calculated
        startOfMonthBalance = balance;
    }

    // Deposit money to the account
    @Override
    public void deposit(double amount) {
        balance += amount; // Add deposit to balance
    }

    // Withdraw money from the account
    @Override
    public void withdraw(double amount) {
        balance -= amount; // Deduct withdrawal from balance
    }
}
