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

public class TPM3 extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/spacebg.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final TFS tfs;
    private final Controller controller;
    private LevelView levelView;

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
        System.out.println("TPM3: Base Constructor COMPLETE");
        tfs = new TFS();
        System.out.println("TPM3: Boss CREATED");
        this.controller = controller;
    }

    @Override
    public void initialiseFriendlyUnits() {
        System.out.println("TPM3: Adding UserPlane to Root");
        addFriendlyUnit(getUser());
        System.out.println("TPM3: UserPlane CREATED");

        System.out.println("TPM3: Adding ISD Shield to Root");
        getRoot().getChildren().add((SWShield) tfs.getShield());
        System.out.println("TPM3: ISD Shield CREATED");

    }

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

    @Override
    public void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(tfs);
        }
    }

    @Override
    public LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    private void TPMWinScreenTransition() {
        System.out.println("TPMWinScreenTransition");
        Transition transition = new Transition(
                getStage(),
                "/dev/ccr/dmscw2024/images/TPM/naboo.png",
                "Naboo's invasion has been stopped! \n WE WIN!",
                () -> {
                    System.out.println("WinScreen");
                    WinScreen();
                }
        );
        transition.display();
    }

    private void WinScreen() {
        System.out.println("WinScreen!");
        Win win = new Win(getStage(), controller);
        win.display();
    }
}
