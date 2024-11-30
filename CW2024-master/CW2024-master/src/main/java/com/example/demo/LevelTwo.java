package com.example.demo;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(
				BACKGROUND_IMAGE_NAME,
				screenHeight,
				screenWidth,
				() -> createCustomUserPlane(
						"com/example/demo/images/userplane.png",
						50,
						50.0,
						screenHeight / 2,
						5
				)
		);
		System.out.println("LevelTwo: Base Constructor COMPLETE");
		boss = new Boss();
		System.out.println("LevelTwo: Boss CREATED");
	}

	@Override
	protected void initializeFriendlyUnits() {
		System.out.println("LevelTwo: Adding UserPlane to Root");
		getRoot().getChildren().add(getUser());
		System.out.println("LevelTwo: UserPlane CREATED");

		System.out.println("LevelTwo: Adding Boss Shield to Root");
		try{
			getRoot().getChildren().add(boss.getShieldImage());
			System.out.println("LevelTwo: Boss Shield CREATED");
		} catch(Exception e){
			System.out.println("LevelTwo: FAILED to Add Boss Shield : " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

}
