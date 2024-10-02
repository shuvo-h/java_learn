package cqu.customeraccountmanagement;

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


    // Action Listener for "Find Customer" button
    @FXML
    private void onFindCustomerAction(ActionEvent event) {
        otherMessage.setText("Find Customer button clicked - under development");
    }


    // Action Listener for "Find Account" button
    @FXML
    private void onFindAccountAction(ActionEvent event) {
        otherMessage.setText("Find Account button clicked - under development");
    }

    // Action listener for "Next" button
    @FXML
    private void onNextAction(ActionEvent event) {
        otherMessage.setText("Next button clicked - under development");
    }

    // Action listener for "Previous" button
    @FXML
    private void onPreviousAction(ActionEvent event) {
        otherMessage.setText("Previous button clicked - under development");
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
