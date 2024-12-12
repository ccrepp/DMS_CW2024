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

public class AOTCd3 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/GeonosisArena.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Padme Padme;
    private LevelView levelView;
    private Controller controller;

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

    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());

        getRoot().getChildren().add((Anakin) Padme.getShield());

    }

    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (Padme.isDestroyed()) {
            winGame();
            AOTCScreenTransition();
        }
    }

    @Override
    public void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(Padme);
        }
    }

    @Override
    public LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    private void AOTCScreenTransition() {
        Transition transition = new Transition(
                getStage(),
                "/dev/ccr/dmscw2024/images/AOTC/bg/geonosisbg.jpeg",
                "they're surrounded! \n it's our victory!",
                this::WinScreen
        );
        transition.display();

    }

    private void WinScreen() {
        System.out.println("WinScreen!");
        Win win = new Win(getStage(), controller);
        win.display();
    }
}
