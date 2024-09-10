/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
/**
 * FXML Controller class
 *
 * @author Biddrup Kumar Mallic
 */
public class CustomerController implements Initializable {


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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onDepositAction(ActionEvent event) {
        otherMessage.setText("Deposit button clicked - under development");
    }

    @FXML
    private void onWithdrawAction(ActionEvent event) {
        otherMessage.setText("Withdraw button clicked - under development");
    }

    @FXML
    private void onFindCustomerAction(ActionEvent event) {
        otherMessage.setText("Find Customer button clicked - under development");
    }

    @FXML
    private void onFindAccountAction(ActionEvent event) {
        otherMessage.setText("Find Account button clicked - under development");
    }

    @FXML
    private void onAddMonthlyInterestAction(ActionEvent event) {
        otherMessage.setText("Add Monthly Interest button clicked - under development");
    }

    @FXML
    private void onGenerateReportAction(ActionEvent event) {
        otherMessage.setText("Generate Report button clicked - under development");
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
        
        // Enable the withdraw button
        withdraw_btn.setDisable(false);
        
        otherMessage.setText("All fields cleared.");
    }

    @FXML
    private void onPreviousAction(ActionEvent event) {
        otherMessage.setText("Previous button clicked - under development");
    }

    @FXML
    private void onNextAction(ActionEvent event) {
        otherMessage.setText("Next button clicked - under development");
    }

    @FXML
    private void onExitAction(ActionEvent event) {
        // Close the application
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        stage.close();
    }

}
