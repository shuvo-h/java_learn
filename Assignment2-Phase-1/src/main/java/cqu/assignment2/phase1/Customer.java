package cqu.assignment2.phase1;

import java.util.ArrayList;

public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String email;
    private ArrayList<Account> accounts = new ArrayList<>();
    private int currentAccount;

    // Constructor
    public Customer(String customerID, String name, String phone, String email) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getter for customer ID
    public String getCustomerID() {
        return customerID;
    }

    // Getter for accounts list
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Add account to the customer
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Getter methods for other customer details
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    // Placeholder methods for iterating accounts
    public Account getFirstAccount() {
        return accounts.get(0);
    }

    public Account getNextAccount() {
        return accounts.get((currentAccount + 1) % accounts.size());
    }

    public Account getPreviousAccount() {
        return accounts.get((currentAccount - 1 + accounts.size()) % accounts.size());
    }
    
    
}
