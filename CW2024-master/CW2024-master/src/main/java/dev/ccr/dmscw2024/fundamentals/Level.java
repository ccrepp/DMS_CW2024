package dev.ccr.dmscw2024.fundamentals;

import javafx.scene.Scene;

public interface Level {
    Scene initializeScene();
    void startGame();
    void endGame();
}
