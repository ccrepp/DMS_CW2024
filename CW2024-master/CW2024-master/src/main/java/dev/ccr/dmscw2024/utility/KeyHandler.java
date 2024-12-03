package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.levels.LevelParent;

import javafx.scene.input.*;

public class KeyHandler {
    private final LevelParent levelParent;

    public KeyHandler(LevelParent levelParent) {
        this.levelParent = levelParent;
    }

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

    }

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
