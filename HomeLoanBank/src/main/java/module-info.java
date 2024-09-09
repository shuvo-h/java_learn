module com.mycompany.homeloanbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.homeloanbank to javafx.fxml;
    exports com.mycompany.homeloanbank;
}
