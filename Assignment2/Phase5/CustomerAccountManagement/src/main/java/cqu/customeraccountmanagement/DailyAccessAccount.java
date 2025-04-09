package cqu.customeraccountmanagement;

public class DailyAccessAccount extends Account {
    private double balance;            
    private double minimumBalance;    
    private double interestEarned;    

    // Constructor
    public DailyAccessAccount(String accountID, String customerID, double interestRate, double balance) {
        super(accountID, customerID, "Daily Access", interestRate);
        this.balance = balance;
        this.minimumBalance = balance; 
        this.interestEarned = 0.0;     
    }

    // Get detailed information about the account using StringBuilder
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Current Balance: $%.2f%n", balance));
        sb.append(String.format("Minimum Balance: $%.2f%n", minimumBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); 
        return sb.toString();
    }

    // Apply monthly interest based on the minimum balance
    @Override
    public void applyMonthlyInterest() {
        // Interest is calculated based on the minimum balance since last interest calculation
        double interest = minimumBalance * getMonthlyInterestRate();
        interestEarned = interest; 
        balance += interest;       
        minimumBalance = balance;  
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
        
        // If the new balance is less than the previous minimum balance, update the minimum balance
        if (balance < minimumBalance) {
            minimumBalance = balance;
        }
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
