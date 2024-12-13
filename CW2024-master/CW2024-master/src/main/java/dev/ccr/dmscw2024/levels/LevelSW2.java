package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.planes.enemies.TieFighter;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

/**
 * Level SW 2
 */
public class LevelSW2 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/backgroundsw.jpg";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelISD";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 15;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     *  Level 2 Constructor
     * @param screenHeight Game Screen Height
     * @param screenWidth Game Screen Width
     * @param stage Game JavaFX stage
     */
    public LevelSW2(double screenHeight, double screenWidth, Stage stage) {
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
     * Create and Return {@link LevelView} for Level SW 2
     * @return {@link LevelView} for Level SW 2
     */
    @Override
    public LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Initialises user-controlled plane for Level SW 2 : <br/>
     * XWing
     */
    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());
    }

    /**
     * Spawns TieFighters for Level SW 2 <br/>
     * uses probability-based spawning <br/>
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
     * Checks if Level SW 2 is over if either
     * <ul>
     *     <li>user-controlled plane is destroyed, results in LOSS</li>
     *     <li>user has reached required kill-count to advance, results in Transition Screen</li>
     * </ul>
     */
    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (userHasReachedKillTarget())
            showTransitionScreen();
    }

    /**
     * Checks if user has reached required kill-count for advancement
     * @return {@code true} if number of kills is greater than or equal to {@code KILLS_TO_ADVANCE},
     * {@code false} otherwise
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Displays a transition screen
     * This method creates a {@link Transition} object configured with specific
     * display properties and navigates to the next game level when the transition is completed.
     * Utilizes {@link Transition#display()} to render the transition screen and
     * {@link #goToNextLevel(String)} to proceed to the designated level, as specified
     * by the class field {@code NEXT_LEVEL}.
     */
    private void showTransitionScreen() {
        Transition transition = new Transition(
                getStage(),
                "/dev/ccr/dmscw2024/images/SW/ISDtransition.jpg",
                "those things are huge! \n WAIT, one of them is coming at me!",
                () -> goToNextLevel(NEXT_LEVEL)
        );
        transition.display();
    }
}
