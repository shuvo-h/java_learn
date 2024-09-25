package cqu.assignment2.phase1;

import java.util.ArrayList;

public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String email;
    private ArrayList<Account> accounts;
    private int currentAccount;

    // Constructor
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
    
     // Get the list of accounts (add this getter to access the accounts list)
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Add an account to the customer
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Get number of accounts for the customer
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    // Get the first account in the list
    public Account getFirstAccount() {
        if (accounts.size() > 0) {
            currentAccount = 0; // Reset the current account index
            return accounts.get(0);
        }
        return null; // No accounts available
    }

    // Get the next account (circular navigation)
    public Account getNextAccount() {
        if (accounts.size() > 0) {
            currentAccount = (currentAccount + 1) % accounts.size();
            return accounts.get(currentAccount);
        }
        return null; // No accounts available
    }

    // Get the previous account (circular navigation)
    public Account getPreviousAccount() {
        if (accounts.size() > 0) {
            currentAccount = (currentAccount - 1 + accounts.size()) % accounts.size();
            return accounts.get(currentAccount);
        }
        return null; // No accounts available
    }

    // Set the current account based on accountID
    public void setCurrentAccount(String accountID) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountID().equals(accountID)) {
                currentAccount = i; // Set the current account index
                break;
            }
        }
    }
}
