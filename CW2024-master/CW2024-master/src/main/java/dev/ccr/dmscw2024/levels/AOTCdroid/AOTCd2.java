package dev.ccr.dmscw2024.levels.AOTCdroid;

import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.bosses.Padme;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.specials.shield.Anakin;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

/**
 * AOTC d(roid) 2
 */
public class AOTCd2 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/GeonosisArena.jpg";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.AOTCdroid.AOTCd3";
    private static final int PLAYER_INITIAL_HEALTH = 3;
    private final Padme Padme;
    private LevelView levelView;

    /**
     * AOTCd2 Constructor
     * @param screenHeight Game Screen Height
     * @param screenWidth Game Screen Width
     * @param stage Game JavaFX stage
     */
    public AOTCd2(double screenHeight, double screenWidth, Stage stage) {
        super(
                BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/bgm/GeonosisBGM.mp3",
                screenHeight,
                screenWidth,
                () -> PlaneFactory.createDefaultBattleDroid(screenHeight),
                stage
        );
        Padme = new Padme();
    }

    /**
     * Create and Return {@link LevelView} for AOTCd2
     * @return {@link LevelView} for AOTCd2
     */
    @Override
    public LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    /**
     * Initialises user-controlled plane and shield for AOTCd2 : <br/>
     * BattleDroid & Anakin
     */
    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());
        getRoot().getChildren().add((Anakin) Padme.getShield());

    }

    /**
     * Spawn sole Padme enemy unit for AOTCd2
     */
    @Override
    public void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(Padme);
        }
    }

    /**
     * Checks if AOTCd2 is over if either
     * <ul>
     *     <li>user-controlled plane is destroyed, results in Transition Screen</li>
     *     <li>user has killed Padme, results in Transition Screen also</li>
     * </ul>
     */
    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            AOTCd2ScreenTransition();
        }
        else if (Padme.isDestroyed()) {
            AOTCd2ScreenTransition();
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
    private void AOTCd2ScreenTransition() {
        Transition transition = new Transition(
                getStage(),
                "/dev/ccr/dmscw2024/images/AOTC/bg/geonosisbg.jpeg",
                "that Jedi keeps protecting her,\n take her out!",
                () -> goToNextLevel(NEXT_LEVEL)
        );
        transition.display();
    }
}
