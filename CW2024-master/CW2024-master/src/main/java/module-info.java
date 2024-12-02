module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


//    opens dev.ccr.dmscw2024 to javafx.fxml;
    exports dev.ccr.dmscw2024.controller;
    opens dev.ccr.dmscw2024.levels to javafx.fxml;
    opens dev.ccr.dmscw2024.planes.enemies to javafx.fxml;
    opens dev.ccr.dmscw2024.planes.bosses to javafx.fxml;
    opens dev.ccr.dmscw2024.planes.user to javafx.fxml;
    opens dev.ccr.dmscw2024.fundamentals to javafx.fxml;
    opens dev.ccr.dmscw2024.specials to javafx.fxml;
    opens dev.ccr.dmscw2024.specials.shield to javafx.fxml;
    opens dev.ccr.dmscw2024.projectile to javafx.fxml;
    opens dev.ccr.dmscw2024.planes to javafx.fxml;
}