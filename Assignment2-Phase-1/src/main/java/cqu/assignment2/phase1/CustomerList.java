package cqu.assignment2.phase1;

import java.util.HashMap;

public class CustomerList {
    private HashMap<String, Customer> customers; // HashMap for customers, key is customerID
    private HashMap<String, Account> accounts;   // HashMap for accounts, key is accountID

    // Constructor
    public CustomerList() {
        customers = new HashMap<>();
        accounts = new HashMap<>();
    }

    // Load customer and account data (hardcoded for testing)
    public void loadCustomerData() {
        // Create accounts for Customer 1
        Account account1 = new DailyAccessAccount("A0001", "C0001", "DailyAccess", 0.01, 1000);
        Account account2 = new GoalSaverAccount("A0002", "C0001", "GoalSaver", 0.02, 1500, 1200);

        // Create Customer 1
        Customer customer1 = new Customer("C0001", "John Doe", "123-4567", "john@example.com");
        customer1.addAccount(account1);
        customer1.addAccount(account2);

        // Create an account for Customer 2
        Account account3 = new HomeLoanAccount("A0003", "C0002", "HomeLoan", 0.03, 200000, 30, "01-01-2022");

        // Create Customer 2
        Customer customer2 = new Customer("C0002", "Jane Smith", "987-6543", "jane@example.com");
        customer2.addAccount(account3);

        // Add customers to the HashMap
        customers.put(customer1.getCustomerID(), customer1);
        customers.put(customer2.getCustomerID(), customer2);

        // Add accounts to the HashMap
        accounts.put(account1.getAccountID(), account1);
        accounts.put(account2.getAccountID(), account2);
        accounts.put(account3.getAccountID(), account3);
    }

    // Find a customer by customerID
    public Customer findCustomer(String customerID) {
        return customers.get(customerID); // Returns null if customer not found
    }

    // Find an account by accountID
    public Account findAccount(String accountID) {
        return accounts.get(accountID); // Returns null if account not found
    }

    // Apply monthly interest to all accounts
    public void applyInterestToAll() {
        for (Account account : accounts.values()) {
            account.applyMonthlyInterest();
        }
    }

    // Generate a report for all customers and their accounts
    public HashMap<String, Customer> getCustomers() {
        return customers; // Returns the map of customers (used for generating reports)
    }
}
