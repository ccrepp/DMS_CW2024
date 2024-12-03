package dev.ccr.dmscw2024.interfaces;

import dev.ccr.dmscw2024.levels.LevelView;
import javafx.scene.Scene;

public interface GameStartEnd {
    Scene initializeScene();

    void startGame();
    void endGame();

    LevelView instantiateLevelView();
}
