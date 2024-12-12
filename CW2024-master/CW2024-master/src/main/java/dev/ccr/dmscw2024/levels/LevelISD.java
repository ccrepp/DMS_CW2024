package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.bosses.ISD;
import dev.ccr.dmscw2024.specials.shield.SWShield;

import javafx.stage.Stage;

public class LevelISD extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/backgroundsw.jpg";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelBoss";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final ISD isd;
    private LevelView levelView;

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
        System.out.println("LevelISD: Base Constructor COMPLETE");
        isd = new ISD();
        System.out.println("LevelISD: Boss CREATED");
    }

    @Override
    public void initialiseFriendlyUnits() {
        System.out.println("LevelISD: Adding UserPlane to Root");
//        getRoot().getChildren().add(getUser());
        addFriendlyUnit(getUser());
        System.out.println("LevelISD: UserPlane CREATED");

        System.out.println("LevelISD: Adding ISD Shield to Root");
        getRoot().getChildren().add((SWShield) isd.getShield());
        System.out.println("LevelISD: ISD Shield CREATED");

    }

    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (isd.isDestroyed()) {
            ISDScreenTransition();
        }
    }

    @Override
    public void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(isd);
        }
    }

    @Override
    public LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

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
