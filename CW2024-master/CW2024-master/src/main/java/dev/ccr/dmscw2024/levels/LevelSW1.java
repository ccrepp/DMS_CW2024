package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.enemies.TieFighter;

import javafx.stage.Stage;

/**
 * Level SW 1
 */
public class LevelSW1 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/backgroundsw.jpg";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelSW2";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     *  Level SW 1 Constructor
     * @param screenHeight Game Screen Height
     * @param screenWidth Game Screen Width
     * @param stage Game JavaFX stage
     */
    public LevelSW1(double screenHeight, double screenWidth, Stage stage) {
        super(
                BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/bgm/SWBGM.mp3",
                screenHeight,
                screenWidth,
                () -> PlaneFactory.createDefaultXWing(screenHeight),
                stage
        );
    }

    /**
     * Create and Return {@link LevelView} for Level SW 1
     * @return {@link LevelView} for Level SW 1
     */
    @Override
    public LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Initialises user-controlled plane for Level SW 1 : <br/>
     * XWing
     */
    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());
    }

    /**
     * Spawns TieFighters for Level SW 1 <br/>
     * uses probability-based spawning
     * ensures total number of enemies on screen does NOT exceed {@code TOTAL_ENEMIES}
     */
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

    /**
     * Checks if Level SW 1 is over if either
     * <ul>
     *     <li>user-controlled plane is destroyed, results in LOSS</li>
     *     <li>user has reached required kill-count to advance, results in level progression</li>
     * </ul>
     */
    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (userHasReachedKillTarget())
            goToNextLevel(NEXT_LEVEL);
    }

    /**
     * Checks if user has reached required kill-count for advancement
     * @return {@code true} if number of kills is greater than or equal to {@code KILLS_TO_ADVANCE},
     * {@code false} otherwise
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
