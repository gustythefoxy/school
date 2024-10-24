module com.mycompany.l1q4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.l1q4 to javafx.fxml;
    exports com.mycompany.l1q4;
}
