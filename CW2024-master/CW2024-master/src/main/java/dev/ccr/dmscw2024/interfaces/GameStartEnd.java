package dev.ccr.dmscw2024.interfaces;

import dev.ccr.dmscw2024.levels.LevelView;
import javafx.scene.Scene;

public interface GameStartEnd {
    Scene initializeScene();

    LevelView instantiateLevelView();

    void checkIfGameOver();

    void startGame();
    void endGame();


}
