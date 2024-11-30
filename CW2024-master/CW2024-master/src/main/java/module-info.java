module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.ccr.dmscw2024 to javafx.fxml;
    exports dev.ccr.dmscw2024.controller;
}