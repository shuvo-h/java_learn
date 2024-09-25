package cqu.assignment2.phase1;

public class HomeLoanAccount extends Account {
    private double originalLoan;       // The original loan amount
    private double amountOwing;        // The current amount owing
    private int loanDuration;          // Duration of the loan in years
    private String startDate;          // Start date of the loan
    private double interestCharged;    // Last amount of interest charged

    // Constructor
    public HomeLoanAccount(String accountID, String customerID, String type, double interestRate, double originalLoan, int duration, String startDate) {
        super(accountID, customerID, type, interestRate);
        this.originalLoan = originalLoan;
        this.amountOwing = originalLoan; // Initially, the amount owing is the same as the original loan amount
        this.loanDuration = duration;
        this.startDate = startDate;
        this.interestCharged = 0.0;
    }

    // Get detailed information about the account using StringBuilder
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Original Loan: $%.2f%n", originalLoan));
        sb.append(String.format("Amount Owing: $%.2f%n", amountOwing));
        sb.append(String.format("Interest Charged: $%.2f%n", interestCharged));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Annual rate
        sb.append(String.format("Loan Duration: %d years%n", loanDuration));
        sb.append(String.format("Loan Start Date: %s%n", startDate));
        return sb.toString();
    }

    // Apply monthly interest
    @Override
    public void applyMonthlyInterest() {
        // Interest is charged on the amount owing
        double interest = amountOwing * getMonthlyInterestRate();
        interestCharged = interest;
        amountOwing += interest; // Add the interest to the amount owing
    }

    // Deposit money to the account (payment reduces the amount owing)
    @Override
    public void deposit(double amount) {
        amountOwing -= amount; // Deduct deposit from amount owing (loan repayment)
    }

    // Withdraw money from the account (this is unusual but allowed in this case)
    @Override
    public void withdraw(double amount) {
        amountOwing += amount; // Increase the amount owing by the withdrawal amount
    }
}
