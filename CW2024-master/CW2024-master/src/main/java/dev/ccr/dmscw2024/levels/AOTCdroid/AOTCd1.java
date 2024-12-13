package dev.ccr.dmscw2024.levels.AOTCdroid;

import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.bosses.ObiWanShip;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.specials.shield.SWShield;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

/**
 * AOTC d(roid) 1
 */
public class AOTCd1 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/geonosis.png";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.AOTCdroid.AOTCd2";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final ObiWanShip OWS;
    private LevelView levelView;

    /**
     * AOTCd1 Constructor
     * @param screenHeight Game Screen Height
     * @param screenWidth Game Screen Width
     * @param stage Game JavaFX stage
     */
    public AOTCd1(double screenHeight, double screenWidth, Stage stage) {
        super(
                BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/bgm/JFvsOBW.mp3",
                screenHeight,
                screenWidth,
                () -> PlaneFactory.createDefaultSlave1(screenHeight),
                stage
        );
        OWS = new ObiWanShip();
    }

    /**
     * Create and Return {@link LevelView} for AOTCd1
     * @return {@link LevelView} for AOTCd1
     */
    @Override
    public LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    /**
     * Initialises user-controlled plane and shield for AOTCd1 : <br/>
     * Slave1 & SWShield
     */
    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());
        getRoot().getChildren().add((SWShield) OWS.getShield());
    }

    /**
     * Spawn sole OWS enemy unit for AOTCd1
     */
    @Override
    public void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(OWS);
        }
    }

    /**
     * Checks if AOTCd1 is over if either
     * <ul>
     *     <li>user-controlled plane is destroyed, results in LOSS</li>
     *     <li>user has killed OWS, results in Transition Screen</li>
     * </ul>
     */
    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (OWS.isDestroyed()) {
            AOTCd1ScreenTransition();
        }
    }

    /**
     * Displays a transition screen
     * This method creates a {@link Transition} object configured with specific
     * display properties and navigates to the next game level when the transition is completed.
     * Utilizes {@link Transition#display()} to render the transition screen and
     * {@link #goToNextLevel(String)} to proceed to the designated level, as specified
     * by the class field {@code NEXT_LEVEL}.
     */
    private void AOTCd1ScreenTransition() {
        Transition transition = new Transition(
                getStage(),
                "/dev/ccr/dmscw2024/images/AOTC/bg/JFvsOWS.jpg",
                "I'll let him escape...\n for now...",
                () -> goToNextLevel(NEXT_LEVEL)
        );
        transition.display();
    }
}
