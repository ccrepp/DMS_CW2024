package dev.ccr.dmscw2024.levels.AOTCdroid;

import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.bosses.ObiWanShip;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.specials.shield.SWShield;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

public class AOTCd1 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/geonosis.png";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.AOTCdroid.AOTCd2";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final ObiWanShip OWS;
    private LevelView levelView;

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

    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());

        getRoot().getChildren().add((SWShield) OWS.getShield());

    }

    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (OWS.isDestroyed()) {
            AOTCd1ScreenTransition();
        }
    }

    @Override
    public void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(OWS);
        }
    }

    @Override
    public LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

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
