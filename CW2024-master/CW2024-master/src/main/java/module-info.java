module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


//    opens dev.ccr.dmscw2024 to javafx.fxml;
    exports dev.ccr.dmscw2024.controller;
    opens dev.ccr.dmscw2024.levels to javafx.fxml;
    opens dev.ccr.dmscw2024.enemies to javafx.fxml;
    opens dev.ccr.dmscw2024.bosses to javafx.fxml;
    opens dev.ccr.dmscw2024.user to javafx.fxml;
    opens dev.ccr.dmscw2024.fundamentals to javafx.fxml;
    opens dev.ccr.dmscw2024.specials to javafx.fxml;
    opens dev.ccr.dmscw2024.specials.shield to javafx.fxml;
}