package dev.ccr.dmscw2024.levels.TPM;

import dev.ccr.dmscw2024.controller.Controller;
import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.bosses.TFS;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.screens.Win;
import dev.ccr.dmscw2024.specials.shield.SWShield;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

/**
 * TPM 3
 */
public class TPM3 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/spacebg.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final TFS tfs;
    private final Controller controller;
    private LevelView levelView;

    /**
     * TPM3 Constructor
     * @param screenHeight Game Screen Height
     * @param screenWidth Game Screen Width
     * @param stage Game JavaFX stage
     * @param controller Game controller
     */
    public TPM3(double screenHeight, double screenWidth, Stage stage, Controller controller) {
        super(
                BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/bgm/SWBossBGM.mp3",
                screenHeight,
                screenWidth,
                () -> PlaneFactory.createCustomN1SF(
                        "/dev/ccr/dmscw2024/images/TPM/n1sf.png",
                        25,
                        50.0,
                        screenHeight / 2,
                        5
                ),
                stage
        );
        tfs = new TFS();
        this.controller = controller;
    }

    /**
     * Create and Return {@link LevelView} for TPM3
     * @return {@link LevelView} for TPM3
     */
    @Override
    public LevelView instantiateLevelView() {
        return levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Initialises user-controlled plane and shield for TPM3 : <br/>
     * N1SF & SWShield
     */
    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());
        getRoot().getChildren().add((SWShield) tfs.getShield());
    }

    /**
     * Spawn sole TFS enemy unit for TPM3
     */
    @Override
    public void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(tfs);
        }
    }

    /**
     * Checks if TPM3 is over if either
     * <ul>
     *     <li>user-controlled plane is destroyed, results in LOSS</li>
     *     <li>user has killed TFS, results in Transition Screen</li>
     * </ul>
     */
    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (tfs.isDestroyed()) {
            winGame();
            TPMWinScreenTransition();
        }
    }

    /**
     * Handles TPM Win Screen transition after TFS is defeated <br/>
     * Proceeds to Win Screen after
     */
    private void TPMWinScreenTransition() {
        Transition transition = new Transition(
                getStage(),
                "/dev/ccr/dmscw2024/images/TPM/naboo.png",
                "Naboo's invasion has been stopped! \n WE WIN!",
                () -> {
                    WinScreen();
                }
        );
        transition.display();
    }

    /**
     * Displays Win Screen after TPM3 completion
     */
    private void WinScreen() {
        Win win = new Win(getStage(), controller);
        win.display();
    }
}
