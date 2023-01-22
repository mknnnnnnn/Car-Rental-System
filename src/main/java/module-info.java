module com.example.carrentalsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;



    opens carRentalSystem to javafx.fxml;
    exports carRentalSystem;
}