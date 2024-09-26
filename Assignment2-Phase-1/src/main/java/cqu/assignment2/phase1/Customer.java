package cqu.assignment2.phase1;

import java.util.ArrayList;

public class Customer {
    private String customerID;           // Unique customer ID
    private String name;                 // Customer name
    private String phone;                // Customer phone number
    private String email;                // Customer email address
    private ArrayList<Account> accounts; // List of accounts for the customer
    private int currentAccount;          // Index of the currently viewed account

    // Constructor to initialize customer details and the list of accounts
    public Customer(String customerID, String name, String phone, String email) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accounts = new ArrayList<>(); // Initialize accounts list
        this.currentAccount = 0; // Initialize current account index
    }

    // Getter methods for customer details
    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Get the list of accounts for the customer
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Add an account to the customer
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Get the number of accounts associated with this customer
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    // Get the first account in the list (used for initial display of account details)
    public Account getFirstAccount() {
        if (accounts.size() > 0) {
            currentAccount = 0; // Reset the current account index to the first account
            return accounts.get(0);
        }
        return null; // Return null if no accounts are available
    }

    // Get the next account (circular navigation through the list of accounts)
    public Account getNextAccount() {
        if (accounts.size() > 0) {
            currentAccount = (currentAccount + 1) % accounts.size(); // Circular increment
            return accounts.get(currentAccount);
        }
        return null; // Return null if no accounts are available
    }

    // Get the previous account (circular navigation through the list of accounts)
    public Account getPreviousAccount() {
        if (accounts.size() > 0) {
            currentAccount = (currentAccount - 1 + accounts.size()) % accounts.size(); // Circular decrement
            return accounts.get(currentAccount);
        }
        return null; // Return null if no accounts are available
    }

    // Set the current account based on accountID (to facilitate searching by account ID)
    public void setCurrentAccount(String accountID) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountID().equals(accountID)) {
                currentAccount = i; // Update the current account index to match the found account
                break;
            }
        }
    }

    // Optional: toString method for easier debugging and testing (not required in the assignment)
    @Override
    public String toString() {
        return "CustomerID: " + customerID + ", Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}
