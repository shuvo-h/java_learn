package cqu.assignment2.phase1;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class CustomerController implements Initializable {

    private CustomerList customerList;
    private Customer currentCustomer;
    private Account currentAccount;
    private int currentAccountIndex;

    @FXML
    private Button deposit_btn, withdraw_btn, findCustomer_btn, findAccount_btn, AddMonthlyInterest_btn, generateReport_btn, clear_btn, previous_btn, next_btn, exit_btn;
    @FXML
    private TextField customerID, name, phone, email, numberOfAccounts, accountNumber, accountType, deposit, withdraw;
    @FXML
    private TextArea accountInfo, otherMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization if needed
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

    // Action Listener for "Find Account" button
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
            accountInfo.setText(account.getAccountDetails());

            // Disable withdraw button for Home Loan accounts
            if (account instanceof HomeLoanAccount) {
                withdraw_btn.setDisable(true);
            } else {
                withdraw_btn.setDisable(false);
            }
        }
    }

    // Add the deposit action method
    @FXML
    private void onDepositAction(ActionEvent event) {
        try {
            double amount = Double.parseDouble(deposit.getText());
            if (amount <= 0) throw new NumberFormatException();
            currentAccount.deposit(amount);
            displayAccount(currentAccount);
            otherMessage.setText("Deposit successful.");
        } catch (NumberFormatException e) {
            otherMessage.setText("Invalid deposit amount.");
        }
    }

    // Add the withdraw action method
    @FXML
    private void onWithdrawAction(ActionEvent event) {
        try {
            double amount = Double.parseDouble(withdraw.getText());
            if (amount <= 0) throw new NumberFormatException();
            currentAccount.withdraw(amount);
            displayAccount(currentAccount);
            otherMessage.setText("Withdrawal successful.");
        } catch (NumberFormatException e) {
            otherMessage.setText("Invalid withdrawal amount.");
        }
    }

    // Add monthly interest action method
    @FXML
    private void onAddMonthlyInterestAction(ActionEvent event) {
        customerList.applyInterestToAll();
        displayAccount(currentCustomer.getAccounts().get(currentAccountIndex));
        otherMessage.setText("Monthly interest applied.");
    }

    // Add generate report button handler
    @FXML
    private void onGenerateReportAction(ActionEvent event) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        String reportName = "ReportsForDate_" + dateFormat.format(date) + ".txt";

        try (FileWriter writer = new FileWriter(reportName)) {
            SimpleDateFormat dateStringFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateStringFormat.format(date);
            
            for (Customer customer : customerList.getCustomers().values()) {
                writer.write("Customer ID: " + customer.getCustomerID() + " - " + dateString + "\n");
                writer.write("Name: " + customer.getName() + "\n");
                writer.write("Phone: " + customer.getPhone() + "\n");
                writer.write("Email: " + customer.getEmail() + "\n");
                writer.write("Number of Accounts: " + customer.getNumberOfAccounts() + "\n\n");
                for (Account account : customer.getAccounts()) {
                    writer.write(account.getAccountDetails() + "\n");
                }
                writer.write("\n--------------------------------------------\n");
            }
            otherMessage.setText("Report generated: " + reportName);
        } catch (IOException e) {
            otherMessage.setText("Error generating report: " + e.getMessage());
        }
    }

    // Clear all fields
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

    // Exit application
    @FXML
    private void onExitAction(ActionEvent event) {
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        stage.close();
    }
}
