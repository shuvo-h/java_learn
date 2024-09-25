package cqu.assignment2.phase1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the GUI layout
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Customer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        // Create and load customer data into the CustomerList
        CustomerList customerList = new CustomerList();
        customerList.loadCustomerData(); // Load initial data for testing purposes

        // Inject the CustomerList object into the controller
        CustomerController controller = loader.getController();
        controller.inject(customerList); // Pass customerList to the controller

        // Set up the stage and scene
        stage.setScene(scene);
        stage.setTitle("Customer Account Management System");
        stage.show();
    }

    // Main entry point for the JavaFX application
    public static void main(String[] args) {
        launch();
    }
}
