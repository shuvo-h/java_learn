package cqu.assignment2.phase1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerController implements Initializable {

    private CustomerList customerList;
    private Customer currentCustomer;
    private Account currentAccount;
    private int currentAccountIndex;

    @FXML
    private Button deposit_btn;
    @FXML
    private Button withdraw_btn;
    @FXML
    private Button findCustomer_btn;
    @FXML
    private Button findAccount_btn;
    @FXML
    private Button AddMonthlyInterest_btn;
    @FXML
    private Button generateReport_btn;
    @FXML
    private Button clear_btn;
    @FXML
    private Button previous_btn;
    @FXML
    private Button next_btn;
    @FXML
    private Button exit_btn;
    @FXML
    private TextField customerID;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField numberOfAccounts;
    @FXML
    private TextField accountNumber;
    @FXML
    private TextField accountType;
    @FXML
    private TextArea accountInfo;
    @FXML
    private TextArea otherMessage;
    @FXML
    private TextField deposit;
    @FXML
    private TextField withdraw;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initial setup if needed
    }

    // Inject method to receive CustomerList from App.java
    public void inject(CustomerList clist) {
        this.customerList = clist;
    }

    // Action Listener for "Find Customer" button
    @FXML
    private void onFindCustomerAction(ActionEvent event) {
        String customerId = customerID.getText();
        currentCustomer = customerList.findCustomer(customerId);

        if (currentCustomer != null) {
            displayCustomer();
            currentAccountIndex = 0; // Start with the first account
            displayAccount(currentCustomer.getAccounts().get(currentAccountIndex));
        } else {
            otherMessage.setText("Customer with ID " + customerId + " not found.");
        }
    }

    // Action listener for "Next" button
    @FXML
    private void onNextAction(ActionEvent event) {
        if (currentCustomer != null) {
            currentAccountIndex = (currentAccountIndex + 1) % currentCustomer.getAccounts().size();
            displayAccount(currentCustomer.getAccounts().get(currentAccountIndex));
        }
    }

    // Action listener for "Previous" button
    @FXML
    private void onPreviousAction(ActionEvent event) {
        if (currentCustomer != null) {
            currentAccountIndex = (currentAccountIndex - 1 + currentCustomer.getAccounts().size()) % currentCustomer.getAccounts().size();
            displayAccount(currentCustomer.getAccounts().get(currentAccountIndex));
        }
    }

    // Helper method to display customer details
    private void displayCustomer() {
        name.setText(currentCustomer.getName());
        phone.setText(currentCustomer.getPhone());
        email.setText(currentCustomer.getEmail());
        numberOfAccounts.setText(Integer.toString(currentCustomer.getNumberOfAccounts()));
        otherMessage.setText("Customer details loaded.");
    }

    // Helper method to display account details
    private void displayAccount(Account account) {
        if (account != null) {
            accountNumber.setText(account.getAccountID());
            accountType.setText(account.getType());
            accountInfo.setText(account.getAccountDetails()); // Show detailed account information
        }
    }


    
    
    
     // Add the deposit action method
    @FXML
    private void onDepositAction(ActionEvent event) {
        String depositAmount = deposit.getText();
        otherMessage.setText("Deposit button clicked. Deposit amount: " + depositAmount + " - under development");
        // Add deposit logic here later
    }
    
     // Add the missing onWithdrawAction for Withdraw button
    @FXML
    private void onWithdrawAction(ActionEvent event) {
        String withdrawAmount = withdraw.getText();
        otherMessage.setText("Withdraw button clicked. Withdraw amount: " + withdrawAmount + " - under development");
    }
    
    
    @FXML
    private void onFindAccountAction(ActionEvent event) {
        String accountID = accountNumber.getText();
        currentAccount = customerList.findAccount(accountID); // Find the account in the customer list

        if (currentAccount != null) {
            currentCustomer = customerList.findCustomer(currentAccount.getCustomerID()); // Find the customer who owns this account
            if (currentCustomer != null) {
                displayCustomer(); // Display customer details
                currentAccountIndex = currentCustomer.getAccounts().indexOf(currentAccount); // Set the current account index
                displayAccount(currentAccount); // Display the current account details
            } else {
                otherMessage.setText("Customer not found for the account.");
            }
        } else {
            otherMessage.setText("Account with ID " + accountID + " not found.");
        }
    }

    
     // Add the monthly interest action method
    @FXML
    private void onAddMonthlyInterestAction(ActionEvent event) {
        otherMessage.setText("Add Monthly Interest button clicked - under development");
        // Add logic here later
    }
    
    // Add the generate report button handler
    @FXML
    private void onGenerateReportAction(ActionEvent event) {
        otherMessage.setText("Generate Report button clicked - under development");
        // Add your report generation logic here
    }
    
    @FXML
    private void onClearAction(ActionEvent event) {
        // Clear all text fields and text areas
        customerID.clear();
        name.clear();
        phone.clear();
        email.clear();
        numberOfAccounts.clear();
        accountNumber.clear();
        accountType.clear();
        accountInfo.clear();
        otherMessage.clear();
        deposit.clear();
        withdraw.clear();

        otherMessage.setText("All fields cleared.");
    }
    
    
    @FXML
    private void onExitAction(ActionEvent event) {
        // Close the application
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        stage.close();
    }
    
    
    

    
}

