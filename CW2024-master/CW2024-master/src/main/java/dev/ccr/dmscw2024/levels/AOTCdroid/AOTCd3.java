package dev.ccr.dmscw2024.levels.AOTCdroid;

import dev.ccr.dmscw2024.controller.Controller;
import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.bosses.Padme;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.screens.Win;
import dev.ccr.dmscw2024.specials.shield.Anakin;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

/**
 * AOTC d(roid) 3
 */
public class AOTCd3 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/GeonosisArena.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Padme Padme;
    private LevelView levelView;
    private Controller controller;

    /**
     * AOTCd3 Constructor
     * @param screenHeight Game Screen Height
     * @param screenWidth Game Screen Width
     * @param stage Game JavaFX stage
     * @param controller Game controller
     */
    public AOTCd3(double screenHeight, double screenWidth, Stage stage, Controller controller) {
        super(
                BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/bgm/GeonosisBGM.mp3",
                screenHeight,
                screenWidth,
                () -> PlaneFactory.createDefaultB1Droid(screenHeight),
                stage
        );
        Padme = new Padme();
        this.controller = controller;
    }

    /**
     * Create and Return {@link LevelView} for AOTCd3
     * @return {@link LevelView} for AOTCd3
     */
    @Override
    public LevelView instantiateLevelView() {
        return levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Initialises user-controlled plane and shield for AOTCd3 : <br/>
     * B1Droid & Anakin
     */
    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());
        getRoot().getChildren().add((Anakin) Padme.getShield());

    }

    /**
     * Spawn sole Padme enemy unit for AOTCd3
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
     *     <li>user-controlled plane is destroyed, results in LOSS</li>
     *     <li>user has killed Padme, results in Transition Screen</li>
     * </ul>
     */
    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (Padme.isDestroyed()) {
            winGame();
            AOTCd3ScreenTransition();
        }
    }

    /**
     * Handles AOTCd3 Win Screen transition after Padme is defeated <br/>
     * Proceeds to Win Screen after
     */
    private void AOTCd3ScreenTransition() {
        Transition transition = new Transition(
                getStage(),
                "/dev/ccr/dmscw2024/images/AOTC/bg/geonosisbg.jpeg",
                "they're surrounded! \n it's our victory!",
                this::WinScreen
        );
        transition.display();

    }

    /**
     * Displays Win Screen after AOTCd3 completion
     */
    private void WinScreen() {
        Win win = new Win(getStage(), controller);
        win.display();
    }
}
