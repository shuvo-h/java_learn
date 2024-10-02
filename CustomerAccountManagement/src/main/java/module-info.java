module cqu.customeraccountmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cqu.customeraccountmanagement to javafx.fxml;
    exports cqu.customeraccountmanagement;
}
