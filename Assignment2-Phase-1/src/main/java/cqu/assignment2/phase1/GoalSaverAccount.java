package cqu.assignment2.phase1;

public class GoalSaverAccount extends Account {
    private double balance;
    private double startOfMonthBalance;
    private double interestEarned;

    public GoalSaverAccount(String accountID, String customerID, String type, double interestRate, double balance, double startOfMonthBalance) {
        super(accountID, customerID, type, interestRate);
        this.balance = balance;
        this.startOfMonthBalance = startOfMonthBalance;
        this.interestEarned = 0;
    }

    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Current Balance: $%.2f%n", balance));
        sb.append(String.format("Start of Month Balance: $%.2f%n", startOfMonthBalance));
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
    }
}
