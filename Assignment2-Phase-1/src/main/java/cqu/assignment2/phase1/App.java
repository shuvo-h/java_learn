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
        // Load FXML
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Customer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        // Create and load customer data
        CustomerList customerList = new CustomerList();
        customerList.loadCustomerData(); // Loading initial data for testing

        // Inject the CustomerList object into the controller
        CustomerController controller = loader.getController();
        controller.inject(customerList); // Pass the customerList to the controller

        // Set the scene and show it
        stage.setScene(scene);
        stage.setTitle("Customer Account Management");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
