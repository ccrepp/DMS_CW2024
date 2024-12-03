package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.levels.LevelParent;

import dev.ccr.dmscw2024.projectile.ProjectileManager;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class GameLoopManager {
    private static final int MILLISECOND_DELAY = 50;
    private final Timeline gameLoop;
    private final LevelParent levelParent;
    private ProjectileManager projectileManager;

    public GameLoopManager(LevelParent levelParent) {
        this.levelParent = levelParent;
        this.projectileManager = new ProjectileManager(levelParent.root);
        this.gameLoop = new Timeline();
        initializeTimeline();
    }

    public void initializeTimeline() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(frame);
    }

    private void updateScene() {
        projectileManager.updateProjectiles(levelParent.userProjectiles, levelParent.enemyProjectiles);

        levelParent.manageSpawn();
        levelParent.manageActors();
        levelParent.manageCollisions();
        levelParent.manageUpdates();

        levelParent.checkIfGameOver();
    }

    public void startGame() {
        gameLoop.play();
    }

    public void endGame() {
        gameLoop.stop();
        gameLoop.getKeyFrames().clear();

        levelParent.background.setOnKeyPressed(null);
        levelParent.background.setOnKeyReleased(null);

        levelParent.root.getChildren().clear();

        levelParent.friendlyUnits.clear();
        levelParent.enemyUnits.clear();
        levelParent.userProjectiles.clear();
        levelParent.enemyProjectiles.clear();
    }
}
