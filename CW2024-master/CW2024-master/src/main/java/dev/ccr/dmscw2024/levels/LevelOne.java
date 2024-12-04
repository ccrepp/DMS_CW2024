package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.enemies.EnemyPlane;

public class LevelOne extends LevelParent {
	
	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/background1.jpg";
	private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelSW";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public LevelOne(double screenHeight, double screenWidth) {
		super(
				BACKGROUND_IMAGE_NAME,
				"/dev/ccr/dmscw2024/audio/BGM.mp3",
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createDefaultUserPlane(screenHeight)
		);
	}

	@Override
	public LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	@Override
	public void initialiseFriendlyUnits() {
		System.out.println("Initialising UserPlane for LevelOne");
//		getRoot().getChildren().add(getUser());

//		ActiveActorDestructible userPlane = createUserPlane();
		addFriendlyUnit(getUser());

    }

	private ActiveActorDestructible createUserPlane() {
		return PlaneFactory.createDefaultUserPlane(getScreenHeight());
	}

	@Override
	public void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnits(newEnemy);
			}
		}
	}

	@Override
    public void checkIfGameOver() {
		if (userIsDestroyed()) {
			System.out.println("LevelOne: BEAT BY LEVEL - User is dead");
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			System.out.println("LevelOne: LEVEL BEAT - User has reached kill target");
			goToNextLevel(NEXT_LEVEL);
		}
//		else {
//			System.out.println("LevelOne: Kill Count: " + getUser().getNumberOfKills());
//			System.out.println("LevelOne: Remaining Enemies: " + getCurrentNumberOfEnemies());


	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
