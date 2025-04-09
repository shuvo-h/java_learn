package cqu.customeraccountmanagement;

public class HomeLoanAccount extends Account {
    private final double originalLoan;  
    private double amountOwing;         
    private int loanDuration;           
    private String startDate;           
    private double interestCharged;     

    // Constructor
    public HomeLoanAccount(String accountID, String customerID, double interestRate, double originalLoan, int duration, String startDate) {
        super(accountID, customerID, "Home Loan", interestRate);
        this.originalLoan = originalLoan;     
        this.amountOwing = originalLoan;      
        this.loanDuration = duration;         
        this.startDate = startDate;           
        this.interestCharged = 0.0;           
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

    // Apply monthly interest
    @Override
    public void applyMonthlyInterest() {
        // Interest is charged on the current amount owing
        double interest = amountOwing * getMonthlyInterestRate();
        interestCharged = interest;
        amountOwing += interest; 
    }

    // Deposit money to the account (loan repayment reduces the amount owing)
    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }
        amountOwing -= amount; 
        if (amountOwing < 0) {
            amountOwing = 0; 
        }
    }

    // Withdraw money from the account (disabled in the GUI, but required for testing purposes)
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        }
        amountOwing += amount; 
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
