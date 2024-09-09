package com.mycompany.homeloanbank;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private TextField depositAmount;
    @FXML
    private TextField withdrawAmount;
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
    private Button deposit_btn;
    @FXML
    private Button withdraw_btn;
    @FXML
    private Button findCustomer_btn;
    @FXML
    private Button findAccount_btn;
    @FXML
    private Button addMOnthlyInterest_btn;
    @FXML
    private Button generateReport_btn;
    @FXML
    private Button clear_btn;
    @FXML
    private Button exit_btn;
    @FXML
    private Button previous_btn;
    @FXML
    private Button next_btn;
    @FXML
    private TextField accountNumber;
    @FXML
    private TextField accountType;
    @FXML
    private TextArea account_message;
    @FXML
    private TextArea other_messages;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void onDepositAction(ActionEvent event) {
        other_messages.setText("Deposit button clicked - under development");
    }

    @FXML
    private void onWithdrawAction(ActionEvent event) {
         other_messages.setText("Withdraw button clicked - under development");
    }

    @FXML
    private void onFindCustomerAction(ActionEvent event) {
        other_messages.setText("Find Customer button clicked - under development");
    }

    @FXML
    private void onFindAccountAction(ActionEvent event) {
        other_messages.setText("Find Account button clicked - under development");
    }

    @FXML
    private void onAddMonthlyInterestAction(ActionEvent event) {
        other_messages.setText("Add Monthly Interest button clicked - under development");
    }

    @FXML
    private void onGenerateAction(ActionEvent event) {
        other_messages.setText("Generate Report button clicked - under development");
    }

    @FXML
    private void onClearAction(ActionEvent event) {
         // Clear all the text fields and text areas
        depositAmount.clear();
        withdrawAmount.clear();
        customerID.clear();
        name.clear();
        phone.clear();
        email.clear();
        numberOfAccounts.clear();
        accountNumber.clear();
        accountType.clear();
        account_message.clear();
        other_messages.clear();

        // Enable the withdraw button
        withdraw_btn.setDisable(false);
    }

    @FXML
    private void onExitBtnAction(ActionEvent event) {
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onPreviousBtnAction(ActionEvent event) {
        other_messages.setText("Previous button clicked - under development");
    }

    @FXML
    private void onNextBtnAction(ActionEvent event) {
        other_messages.setText("Next button clicked - under development");
    }
}
