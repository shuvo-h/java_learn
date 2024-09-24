package cqu.assignment2.phase1;

public class HomeLoanAccount extends Account {
    private double amountOwing;
    private double originalLoan;
    private int duration;
    private String startDate;
    private double interestCharged;

    public HomeLoanAccount(String accountID, String customerID, String type, double interestRate, double originalLoan, int duration, String startDate) {
        super(accountID, customerID, type, interestRate);
        this.originalLoan = originalLoan;
        this.amountOwing = originalLoan;
        this.duration = duration;
        this.startDate = startDate;
        this.interestCharged = 0;
    }

    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Amount Owing: $%.2f%n", amountOwing));
        sb.append(String.format("Original Loan: $%.2f%n", originalLoan));
        sb.append(String.format("Interest Charged: $%.2f%n", interestCharged));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Annual rate
        sb.append(String.format("Loan Duration: %d years%n", duration));
        sb.append(String.format("Start Date: %s%n", startDate));
        return sb.toString();
    }


    @Override
    public void applyMonthlyInterest() {
        // Placeholder for monthly interest logic
    }

    @Override
    public void deposit(double amount) {
        amountOwing -= amount;
    }

    @Override
    public void withdraw(double amount) {
        amountOwing += amount;
    }
}
