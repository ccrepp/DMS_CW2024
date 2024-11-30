package dev.ccr.dmscw2024;

import javafx.scene.Scene;

public interface Level {
    Scene initializeScene();
    void startGame();
    void endGame();
}
