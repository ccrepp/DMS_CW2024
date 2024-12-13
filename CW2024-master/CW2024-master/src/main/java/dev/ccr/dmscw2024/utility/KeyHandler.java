package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.screens.Pause;

import javafx.scene.input.*;

/**
 * Key Handler - handles key inputs of user
 */
public class KeyHandler {
    private final LevelParent levelParent;

    /**
     * KeyHandler constructor
     * @param levelParent current level's parent object
     */
    public KeyHandler(LevelParent levelParent) {
        this.levelParent = levelParent;
    }

    /**
     * handles key events, mapping keys to actions
     * @param e represent key inputs
     */
    public void handleKeyPress(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.W) {
            levelParent.getUser().moveUp();
        }
        if (kc == KeyCode.DOWN || kc == KeyCode.S) {
            levelParent.getUser().moveDown();
        }
        if (kc == KeyCode.LEFT || kc == KeyCode.A) {
            levelParent.getUser().moveLeft();
        }
        if (kc == KeyCode.RIGHT || kc == KeyCode.D) {
            levelParent.getUser().moveRight();
        }
        if (kc == KeyCode.SPACE) {
            levelParent.fireProjectile();
        }
        if (kc == KeyCode.P || kc == KeyCode.ESCAPE) {
            Pause pauseScreen = new Pause(levelParent.getStage(), levelParent);
            pauseScreen.display();
        }

    }

    /**
     * handles key release events, stopping actions
     * @param e represent key release
     */
    public void handleKeyRelease(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.W || kc == KeyCode.DOWN || kc == KeyCode.S) {
            levelParent.getUser().stopVertically();
        }
        if (kc == KeyCode.LEFT || kc == KeyCode.A || kc == KeyCode.RIGHT || kc == KeyCode.D) {
            levelParent.getUser().stopHorizontal();
        }
    }
}
