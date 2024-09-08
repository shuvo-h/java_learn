/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package homeloanaccountasg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Biddrup Kumar Mallic
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button deposite_btn;
    @FXML
    private Button withdraw_btn;
    @FXML
    private Button findCustomer_btn;
    @FXML
    private Button findAccount_btn;
    @FXML
    private Button generateReports_btn;
    @FXML
    private Button clear_btn;
    @FXML
    private TextField deposite;
    @FXML
    private TextField withdraw;
    @FXML
    private TextField customerID;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField numberOfAccount;
    @FXML
    private TextField accountNumber;
    @FXML
    private TextField accountType;
    @FXML
    private TextArea message;
    @FXML
    private Button previous_btn;
    @FXML
    private Button next_btn;
    @FXML
    private Button exit_btn;
    @FXML
    private TextArea additionalMesage;
    @FXML
    private Button allMonthlyInterest_btn;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onDepositeAction(ActionEvent event) {
        additionalMesage.setText("deposit button clicked - under development");
    }

    @FXML
    private void onWithdrawAction(ActionEvent event) {
         additionalMesage.setText("withdraw button clicked - under development");
    }

    @FXML
    private void findCustomerAction(ActionEvent event) {
        additionalMesage.setText("find customer button clicked - under development");
    }

    @FXML
    private void onFindAccountAction(ActionEvent event) {
        additionalMesage.setText("find account button clicked - under development");
    }

    @FXML
    private void onAllMonthlyInterestAction(ActionEvent event) {
        additionalMesage.setText("add all monthly interests button clicked - under development");
    }

    @FXML
    private void onGenerateReportsAction(ActionEvent event) {
        additionalMesage.setText("generate reports button clicked - under development");
    }
    
    // Clear button handler: clears all text fields and text areas
    @FXML
    private void clearActionHandler(ActionEvent event) {
        deposite.clear();
        withdraw.clear();
        customerID.clear();
        name.clear();
        phone.clear();
        email.clear();
        numberOfAccount.clear();
        accountNumber.clear();
        accountType.clear();
        message.clear();
        additionalMesage.clear();
        // Enable the withdraw button if it was disabled
        withdraw_btn.setDisable(false);
    }

    @FXML
    private void onPreviousAction(ActionEvent event) {
        additionalMesage.setText("previous button clicked - under development");
    }

    @FXML
    private void onNextAction(ActionEvent event) {
        additionalMesage.setText("next button clicked - under development");
    }

    @FXML
    private void exitActionHandler(ActionEvent event) {
        // Get the stage from the button and close it
        Stage stage = (Stage) exit_btn.getScene().getWindow();
        stage.close();
    }
    
}
