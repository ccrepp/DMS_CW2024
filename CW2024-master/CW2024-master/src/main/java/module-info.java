module com.example.demo {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires javafx.graphics;
    requires javafx.swt;


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
    opens dev.ccr.dmscw2024.utility to javafx.fxml;
    opens dev.ccr.dmscw2024.interfaces to javafx.fxml;
    opens dev.ccr.dmscw2024.levels.TPM to javafx.fxml;
    opens dev.ccr.dmscw2024.planes.user.AOTC to javafx.fxml;
}