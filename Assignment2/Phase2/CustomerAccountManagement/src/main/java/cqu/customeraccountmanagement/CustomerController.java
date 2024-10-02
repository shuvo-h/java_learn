package cqu.customeraccountmanagement;

import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Date;

public class CustomerController implements Initializable {

    private CustomerList customerList;  // Reference to the CustomerList object
    private Customer currentCustomer;   // Reference to the currently selected customer
    private Account currentAccount;     // Reference to the currently selected account
    private int currentAccountIndex;    // Tracks the index of the currently displayed account

    @FXML
    private Button deposit_btn, withdraw_btn, findCustomer_btn, findAccount_btn, AddMonthlyInterest_btn, generateReport_btn, clear_btn, previous_btn, next_btn, exit_btn;
    @FXML
    private TextField customerID, name, phone, email, numberOfAccounts, accountNumber, accountType, deposit, withdraw;
    @FXML
    private TextArea accountInfo, otherMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No initialization required for now
    }

    // Method to inject the CustomerList from App.java
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
            if (!currentCustomer.getAccounts().isEmpty()) {
                currentAccountIndex = 0; // Start with the first account
                currentAccount = currentCustomer.getAccounts().get(currentAccountIndex); // Set the current account to the first one
                displayAccount(currentAccount); // Display the first account
            } else {
                currentAccount = null; // No accounts available
                otherMessage.setText("Customer found, but no accounts available.");
                clearAccountFields();
            }
        } else {
            otherMessage.setText("Customer with ID " + customerId + " not found.");
            clearCustomerFields(); // Clear fields if customer is not found
            currentAccount = null; // Reset currentAccount to null
        }
    }


    // Action Listener for "Find Account" button
    @FXML
    private void onFindAccountAction(ActionEvent event) {
        String accountID = accountNumber.getText();
        currentAccount = customerList.findAccount(accountID);  // Find account by ID

        if (currentAccount != null) {
            currentCustomer = customerList.findCustomer(currentAccount.getCustomerID()); // Find associated customer
            if (currentCustomer != null) {
                displayCustomer();
                currentAccountIndex = currentCustomer.getAccounts().indexOf(currentAccount); // Set current account index
                displayAccount(currentAccount);  // Display account details
            } else {
                otherMessage.setText("Customer not found for the account.");
                clearCustomerFields();
            }
        } else {
            otherMessage.setText("Account with ID " + accountID + " not found.");
            clearAccountFields();  // Clear fields if account is not found
        }
    }

    // Action listener for "Next" button
    @FXML
    private void onNextAction(ActionEvent event) {
         if (currentCustomer != null && !currentCustomer.getAccounts().isEmpty()) {
            currentAccountIndex = (currentAccountIndex + 1) % currentCustomer.getAccounts().size(); // Circular increment
            currentAccount = currentCustomer.getAccounts().get(currentAccountIndex); // Update currentAccount
            displayAccount(currentAccount); // Display the newly selected account
            otherMessage.setText("Showing next account.");
        } else {
            otherMessage.setText("No accounts available or customer not selected.");
        }
    }

    // Action listener for "Previous" button
    @FXML
    private void onPreviousAction(ActionEvent event) {
        if (currentCustomer != null && !currentCustomer.getAccounts().isEmpty()) {
            currentAccountIndex = (currentAccountIndex - 1 + currentCustomer.getAccounts().size()) % currentCustomer.getAccounts().size(); // Circular decrement
            currentAccount = currentCustomer.getAccounts().get(currentAccountIndex); // Update currentAccount
            displayAccount(currentAccount); // Display the newly selected account
            otherMessage.setText("Showing previous account.");
        } else {
            otherMessage.setText("No accounts available or customer not selected.");
        }
    }

    // Helper method to display customer details
    private void displayCustomer() {
        customerID.setText(currentCustomer.getCustomerID());
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
            accountInfo.setText(account.getAccountDetails());

            // Disable withdraw button for Home Loan accounts
            if (account instanceof HomeLoanAccount) {
                withdraw_btn.setDisable(true);
            } else {
                withdraw_btn.setDisable(false);
            }
        }
    }

    // Helper method to clear customer fields
    private void clearCustomerFields() {
        name.clear();
        phone.clear();
        email.clear();
        numberOfAccounts.clear();
        clearAccountFields();  // Also clear account fields when customer is not found
    }

    // Helper method to clear account fields
    private void clearAccountFields() {
        accountNumber.clear();
        accountType.clear();
        accountInfo.clear();
        deposit.clear();
        withdraw.clear();
    }

    // Action listener for "Deposit" button
    @FXML
    private void onDepositAction(ActionEvent event) {
        otherMessage.setText("Deposit button clicked - under development");
    }

    // Action listener for "Withdraw" button
    @FXML
    private void onWithdrawAction(ActionEvent event) {
        otherMessage.setText("Withdraw button clicked - under development");
    }

    // Action listener for "Add Monthly Interest" button
    @FXML
    private void onAddMonthlyInterestAction(ActionEvent event) {
         otherMessage.setText("Add Monthly Interest button clicked - under development");
    }

    // Action listener for "Generate Report" button
    @FXML
    private void onGenerateReportAction(ActionEvent event) {
        otherMessage.setText("Generate Report button clicked - under development");
    }

    // Action listener for "Clear" button
    @FXML
    private void onClearAction(ActionEvent event) {
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

    // Action listener for "Exit" button
    @FXML
    private void onExitAction(ActionEvent event) {
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        stage.close();
    }
}
