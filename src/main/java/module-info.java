module com.example.nexoia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nexoia to javafx.fxml;
    exports com.example.nexoia;
}