package cqu.assignment2.phase1;

public class DailyAccessAccount extends Account {
    private double balance;
    private double minimumBalance;
    private double interestEarned;

    // Constructor
    public DailyAccessAccount(String accountID, String customerID, String type, double interestRate, double balance) {
        super(accountID, customerID, type, interestRate);
        this.balance = balance;
        this.minimumBalance = balance; // Initially, the minimum balance is the same as the current balance
        this.interestEarned = 0.0;
    }

    // Get detailed information about the account using StringBuilder
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Current Balance: $%.2f%n", balance));
        sb.append(String.format("Minimum Balance: $%.2f%n", minimumBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Annual rate
        return sb.toString();
    }

    // Apply monthly interest
    @Override
    public void applyMonthlyInterest() {
        // Interest is calculated based on the minimum balance since last interest calculation
        double interest = minimumBalance * getMonthlyInterestRate();
        interestEarned = interest;
        balance += interest; // Add the earned interest to the balance
        minimumBalance = balance; // Reset minimum balance to current balance after interest is applied
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
        // If the new balance is less than the previous minimum balance, update the minimum balance
        if (balance < minimumBalance) {
            minimumBalance = balance;
        }
    }
}
