package cqu.customeraccountmanagement;

public class GoalSaverAccount extends Account {
    private double balance;                
    private double startOfMonthBalance;    
    private double interestEarned;         

    // Constructor
    public GoalSaverAccount(String accountID, String customerID, double interestRate, double balance) {
        super(accountID, customerID, "Goal Saver", interestRate);
        this.balance = balance;
        this.startOfMonthBalance = balance; 
        this.interestEarned = 0.0;         
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
        // Interest is only applied if the current balance is at least $500 more than the start of month balance
        if (balance >= startOfMonthBalance + 500) {
            double interest = balance * getMonthlyInterestRate();
            interestEarned = interest;
            balance += interest; 
        } else {
            interestEarned = 0;
        }
        
        startOfMonthBalance = balance;
    }

    // Deposit money to the account
    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }
        balance += amount; 
    }

    // Withdraw money from the account
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        }
        if (balance - amount < 0) {
            throw new IllegalArgumentException("Insufficient funds for this withdrawal");
        }
        balance -= amount; 
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
