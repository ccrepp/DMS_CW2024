package dev.ccr.dmscw2024.levels.AOTCdroid;

import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.bosses.Padme;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.specials.shield.Anakin;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

public class AOTCd2 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/GeonosisArena.jpg";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.AOTCdroid.AOTCd3";
    private static final int PLAYER_INITIAL_HEALTH = 3;
    private final Padme Padme;
    private LevelView levelView;

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

    @Override
    public void initialiseFriendlyUnits() {
        addFriendlyUnit(getUser());

        getRoot().getChildren().add((Anakin) Padme.getShield());

    }

    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            AOTCScreenTransition();
        }
        else if (Padme.isDestroyed()) {
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
                "that Jedi keeps protecting her,\n take her out!",
                () -> goToNextLevel(NEXT_LEVEL)
        );
        transition.display();
    }
}
