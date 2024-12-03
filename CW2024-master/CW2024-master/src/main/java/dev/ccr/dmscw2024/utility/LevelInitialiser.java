package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.planes.enemies.EnemyPlane;
import dev.ccr.dmscw2024.planes.user.UserPlane;
import javafx.scene.Group;

import dev.ccr.dmscw2024.levels.LevelView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.function.Supplier;

public abstract class LevelInitialiser {
    private final double screenHeight;
    private final double screenWidth;

    private final Group root;
    private final LevelView levelView;
    private final ImageView background;
    private final KeyHandler keyHandler;

    public LevelInitialiser(String backgroundImageName,
                            double screenHeight,
                            double screenWidth,
                            Group root,
                            LevelView levelView,
                            KeyHandler keyHandler)
    {
        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        this.root = root;
        this.levelView = levelView;

        this.keyHandler = keyHandler;
    }

    public void initialiseScene(Group root, UserPlane user) {
        setUpBackground(String.valueOf(root));
        setUpKeyHandlers();
        setUpHeartDisplay();
        initialiseFriendlyUnits(root, user);
        spawnEnemyUnits(root);
    }

    private void setUpBackground(String backgroundImageURL) {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);

        root.getChildren().add(background);
    }

    private void setUpKeyHandlers() {
        background.setOnKeyPressed(keyHandler::handleKeyPress);
        background.setOnKeyReleased(keyHandler::handleKeyRelease);
    }

    private void setUpHeartDisplay() {
        levelView.showHeartDisplay();
    }

    protected abstract void initialiseFriendlyUnits(Group root, UserPlane user);

    protected abstract void spawnEnemyUnits(Group root);

    public Group getRoot() {
        return root;
    }


}
