package cqu.customeraccountmanagement;

import java.util.ArrayList;

public class Customer {
    private String customerID;          
    private String name;                 
    private String phone;                
    private String email;                
    private ArrayList<Account> accounts; 
    private int currentAccount;          

    // Constructor to initialize customer details and the list of accounts
    public Customer(String customerID, String name, String phone, String email) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accounts = new ArrayList<>(); 
        this.currentAccount = 0;
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
            currentAccount = 0; 
            return accounts.get(0);
        }
        return null; // Return null if no accounts are available
    }

    // Get the next account (circular navigation through the list of accounts)
    public Account getNextAccount() {
        if (accounts.size() > 0) {
            currentAccount = (currentAccount + 1) % accounts.size(); 
            return accounts.get(currentAccount);
        }
        return null; 
    }

    // Get the previous account (circular navigation through the list of accounts)
    public Account getPreviousAccount() {
        if (accounts.size() > 0) {
            currentAccount = (currentAccount - 1 + accounts.size()) % accounts.size(); 
            return accounts.get(currentAccount);
        }
        return null; 
    }

    // Set the current account based on accountID (to facilitate searching by account ID)
    public void setCurrentAccount(String accountID) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountID().equals(accountID)) {
                currentAccount = i; 
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
