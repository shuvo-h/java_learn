package cqu.assignment2.phase1;

public class DailyAccessAccount extends Account {
    private double balance;
    private double minimumBalance;
    private double interestEarned;

    public DailyAccessAccount(String accountID, String customerID, String type, double interestRate, double balance) {
        super(accountID, customerID, type, interestRate);
        this.balance = balance;
        this.minimumBalance = balance;
        this.interestEarned = 0;
    }

    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Current Balance: $%.2f%n", balance));
        sb.append(String.format("Minimum Balance: $%.2f%n", minimumBalance));
        sb.append(String.format("Last Interest Earned: $%.2f%n", interestEarned));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Annual rate
        return sb.toString();
    }


    @Override
    public void applyMonthlyInterest() {
        // Placeholder for monthly interest logic
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
        if (balance < minimumBalance) {
            minimumBalance = balance;
        }
    }
}
