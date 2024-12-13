package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.bosses.ISD;
import dev.ccr.dmscw2024.specials.shield.SWShield;

import javafx.stage.Stage;

/**
 * Level ISD
 */
public class LevelISD extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/backgroundsw.jpg";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelBoss";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final ISD isd;
    private LevelView levelView;

    /**
     * Level ISD Constructor
     * @param screenHeight Game Screen Height
     * @param screenWidth Game Screen Width
     * @param stage Game JavaFX stage
     */
    public LevelISD(double screenHeight, double screenWidth, Stage stage) {
        super(
                BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/bgm/SWBossBGM.mp3",
                screenHeight,
                screenWidth,
                () -> PlaneFactory.createCustomXWing(
                        "/dev/ccr/dmscw2024/images/SW/xwing.png",
                        50,
                        50.0,
                        screenHeight / 2,
                        5
                ),
                stage
        );
        isd = new ISD();
    }

    /**
     * Create and Return {@link LevelView} for Level ISD
     * @return {@link LevelView} for Level ISD
     */
    @Override
    public LevelView instantiateLevelView() {
        return levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Initialises user-controlled plane and shield for Level ISD : <br/>
     * XWing & SWShield
     */
    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());
        getRoot().getChildren().add((SWShield) isd.getShield());

    }

    /**
     * Spawn sole ISD enemy unit for Level ISD
     */
    @Override
    public void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(isd);
        }
    }

    /**
     * Checks if Level ISD is over if either
     * <ul>
     *     <li>user-controlled plane is destroyed, results in LOSS</li>
     *     <li>user has killed ISD, results in Transition Screen</li>
     * </ul>
     */
    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (isd.isDestroyed()) {
            ISDScreenTransition();
        }
    }

    /**
     * Handles ISD transition after ISD is defeated <br/>
     * Proceeds to next level after
     */
    private void ISDScreenTransition() {
        System.out.println("TPMWinScreenTransition");
        Transition transition = new Transition(
                getStage(),
                "/dev/ccr/dmscw2024/images/SW/hyperspace.jpg",
                "WOAH! \n Where to now, I'm ready!",
                () -> {
                    System.out.println("back to normal...");
                    goToNextLevel(NEXT_LEVEL);
                }
        );
        transition.display();
    }
}
