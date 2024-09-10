module com.mycompany.homebankmavenarcfxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.homebankmavenarcfxml to javafx.fxml;
    exports com.mycompany.homebankmavenarcfxml;
}
