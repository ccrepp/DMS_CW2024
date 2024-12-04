package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.enemies.TieFighter;

import javafx.stage.Stage;

public class LevelSW extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/backgroundsw.jpg";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelISD";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    public LevelSW(double screenHeight, double screenWidth, Stage stage) {
        super(
                BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/SWBGM.mp3",

                screenHeight,
                screenWidth,
                () -> PlaneFactory.createDefaultXWing(screenHeight),
                stage
        );
    }

    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (userHasReachedKillTarget())
            goToNextLevel(NEXT_LEVEL);
    }

    @Override
    public void initialiseFriendlyUnits() {
        System.out.println("Initializing XWing for LevelSW");
        System.out.println("getUser(): " + getUser());
//        getRoot().getChildren().add(getUser());
        addFriendlyUnit(getUser());
    }


    @Override
    public void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                TieFighter newEnemy = new TieFighter(getScreenWidth(),newEnemyInitialYPosition);
                addEnemyUnits(newEnemy);;
            }
        }
    }

    @Override
    public LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
