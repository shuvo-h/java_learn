package cqu.customeraccountmanagement;

import java.util.HashMap;

public class CustomerList {
    private HashMap<String, Customer> customers; 
    private HashMap<String, Account> accounts;   

    // Constructor to initialize the HashMaps
    public CustomerList() {
        customers = new HashMap<>();
        accounts = new HashMap<>();
    }

    // Load customer and account data as described in Appendix A
    public void loadCustomerData(){
        // create three customers
       Customer cust1 = new Customer("C0001", "John Smith", "0412345678", "j.smith@cqumail.com");
       Customer cust2 = new Customer("C0002", "Mary Brown","0412456789","m.brown@cqumail.com");
       Customer cust3 = new Customer("C0003", "Peter Green", "0412567890", "p.green@cqumail.com");
       Customer cust4 = new Customer("C0004", "Alice Williams", "0412678901", "a.williams@cqumail.com");
        
        // add them to the HashMap of customers
        customers.put("C0001", cust1);
        customers.put("C0002", cust2);
        customers.put("C0003", cust3);
        customers.put("C0004", cust4);
        
        // create 7 accounts and add them to the HashMap of accounts
        Account acct1 = new HomeLoanAccount("HL0001", "C0001",  0.0005, 800000, 30, "01/01/2024" );
        accounts.put("HL0001", acct1);
        Account acct2 = new HomeLoanAccount("HL0002", "C0003", 0.0005, 600000, 20, "01/03/2024" );
        accounts.put("HL0002", acct2);
        Account acct3 = new GoalSaverAccount("GS0001", "C0002", 0.0075, 2000 );
        accounts.put("GS0001", acct3);
        Account acct4 = new GoalSaverAccount("GS0002", "C0003", 0.0075, 10000 );
        accounts.put("GS0002", acct4);
        Account acct5 = new DailyAccessAccount("DA0001", "C0003", 0.0025, 400 );
        accounts.put("DA0001", acct5);
        Account acct6 = new DailyAccessAccount("DA0002", "C0002", 0.0025, 1000 );
        accounts.put("DA0002", acct6);
        Account acct7 = new DailyAccessAccount("DA0003", "C0001", 0.0025, 3000 );
        accounts.put("DA0003", acct7);
        Account acct8 = new GoalSaverAccount("GS0003", "C0004", 0.0075, 5000);
        accounts.put("GS0003", acct8);
     
        // add accounts to the customers.
        cust1.addAccount(acct1);
        cust1.addAccount(acct7);
        cust2.addAccount(acct3);
        cust2.addAccount(acct6);
        cust3.addAccount(acct2);
        cust3.addAccount(acct4);
        cust3.addAccount(acct5);
        cust4.addAccount(acct8); 
    }

    // Find a customer by their customerID
    public Customer findCustomer(String customerID) {
        return customers.get(customerID);
    }

    // Find an account by its accountID
    public Account findAccount(String accountID) {
        return accounts.get(accountID); 
    }

    // Apply monthly interest to all accounts
    public void applyInterestToAll() {
        for (Account account : accounts.values()) {
            account.applyMonthlyInterest();
        }
    }

    // Generate a report for all customers and their accounts
    public HashMap<String, Customer> getCustomers() {
        return customers; 
    }
}
