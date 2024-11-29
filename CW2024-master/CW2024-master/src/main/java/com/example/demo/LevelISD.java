package com.example.demo;

public class LevelISD extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/backgroundsw.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final ISD isd;
    private LevelViewLevelTwo levelView;

    public LevelISD(double screenHeight, double screenWidth) {
        super(
                BACKGROUND_IMAGE_NAME,
                screenHeight,
                screenWidth,
                () -> new XWing("/com/example/demo/images/xwing.png", 150, 50.0, screenHeight/2,PLAYER_INITIAL_HEALTH));
        System.out.println("LevelISD: Base Constructor COMPLETE");
        isd = new ISD();
        System.out.println("LevelISD: Boss CREATED");
    }

    @Override
    protected void initializeFriendlyUnits() {
        System.out.println("LevelISD: Adding UserPlane to Root");
        getRoot().getChildren().add(getUser());
        System.out.println("LevelISD: UserPlane CREATED");

        System.out.println("LevelISD: Adding ISD Shield to Root");
        getRoot().getChildren().add(isd.getISDShield());
        System.out.println("LevelISD: ISD Shield CREATED");

//        System.out.println("LevelISD: Adding ISD to Root");
//        getRoot().getChildren().add(isd);
//        System.out.println("LevelISD: ISD CREATED");


    }

    @Override
    protected void checkIfGameOver() {
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
            addEnemyUnit(isd);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
