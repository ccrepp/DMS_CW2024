package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.bosses.ISD;
import dev.ccr.dmscw2024.specials.shield.ISDShield;

public class LevelISD extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/backgroundsw.jpg";
    private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelTwo";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final ISD isd;
    private LevelView levelView;

    public LevelISD(double screenHeight, double screenWidth) {
        super(
                BACKGROUND_IMAGE_NAME,
                screenHeight,
                screenWidth,
                () -> PlaneFactory.createCustomXWing(
                        "/dev/ccr/dmscw2024/images/xwing.png",
                        50,
                        50.0,
                        screenHeight / 2,
                        5
                )
        );
        System.out.println("LevelISD: Base Constructor COMPLETE");
        isd = new ISD();
        System.out.println("LevelISD: Boss CREATED");
    }

    @Override
    protected void initialiseFriendlyUnits() {
        System.out.println("LevelISD: Adding UserPlane to Root");
        getRoot().getChildren().add(getUser());
        System.out.println("LevelISD: UserPlane CREATED");

        System.out.println("LevelISD: Adding ISD Shield to Root");
        getRoot().getChildren().add((ISDShield) isd.getShield());
        System.out.println("LevelISD: ISD Shield CREATED");

    }

    @Override
    public void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (isd.isDestroyed()) {
            goToNextLevel(NEXT_LEVEL);
            //winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnits(isd);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
