/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package test1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Biddrup Kumar Mallic
 */
public class Scene2Controller implements Initializable {

    
    @FXML
    private TextField name;
    @FXML
    private TextArea bio;
    @FXML
    private TextField email;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void returnToMainMenuButtonAction(ActionEvent event) throws Exception {
        /*
        Parent root = FXMLLoader.load (getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
        */
        Utililty.changeToScene (getClass(), event, "FXMLDocument.fxml");
    }

    @FXML
    private void onNameChange(ActionEvent event) {
    }

    @FXML
    private void onEmailChangeHandler(ActionEvent event) {
    }

    @FXML
    private void handleSignupHandler(ActionEvent event) {
        String nameValue = name.getText();
        String emailValue = email.getText();
        String bioValue = bio.getText();
        //  lbWelcome.setText("Welcome"+nameValue+" "+emailValue);
        System.out.println("Welcome"+nameValue+" "+emailValue + " " + bioValue);
        
    }
}
