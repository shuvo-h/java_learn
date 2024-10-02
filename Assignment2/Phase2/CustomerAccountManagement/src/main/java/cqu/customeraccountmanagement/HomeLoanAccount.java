package cqu.customeraccountmanagement;

public class HomeLoanAccount extends Account {
    private final double originalLoan;  // The original loan amount (final because it doesn't change)
    private double amountOwing;         // The current amount owing
    private int loanDuration;           // Duration of the loan in years
    private String startDate;           // Start date of the loan
    private double interestCharged;     // Last amount of interest charged

    // Constructor
    public HomeLoanAccount(String accountID, String customerID, double interestRate, double originalLoan, int duration, String startDate) {
        super(accountID, customerID, "Home Loan", interestRate);
        this.originalLoan = originalLoan;     // Original loan amount
        this.amountOwing = originalLoan;      // Initially, the amount owing is the original loan amount
        this.loanDuration = duration;         // Duration of the loan in years
        this.startDate = startDate;           // Start date of the loan
        this.interestCharged = 0.0;           // Initially, no interest charged
    }

    // Get detailed information about the account using StringBuilder
    @Override
    public String getAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Original Loan Amount: $%.2f%n", originalLoan));
        sb.append(String.format("Amount Owing: $%.2f%n", amountOwing));
        sb.append(String.format("Last Interest Charged: $%.2f%n", interestCharged));
        sb.append(String.format("Annual Interest Rate: %.2f%%%n", getMonthlyInterestRate() * 12 * 100)); // Convert monthly to annual rate
        sb.append(String.format("Loan Duration: %d years%n", loanDuration));
        sb.append(String.format("Loan Start Date: %s%n", startDate));
        return sb.toString();
    }

  
    // Getters for various attributes
    public double getAmountOwing() {
        return amountOwing;
    }

    public double getOriginalLoan() {
        return originalLoan;
    }

    public double getInterestCharged() {
        return interestCharged;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public String getStartDate() {
        return startDate;
    }
}
