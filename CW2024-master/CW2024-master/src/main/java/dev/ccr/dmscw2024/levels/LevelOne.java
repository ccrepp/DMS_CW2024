package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.PlaneFactory;
import dev.ccr.dmscw2024.planes.enemies.EnemyPlane;

public class LevelOne extends LevelParent {
	
	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/background1.jpg";
	private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelSW";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/*@Override
	protected LevelParent instantiateLevel(double screenHeight, double screenWidth) {
		return new LevelParent(
				BACKGROUND_IMAGE_NAME,
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createDefaultUserPlane(screenHeight)
		);
	}

	@Override
	protected void initializeFriendlyUnits(LevelParent level) {
		System.out.println("Initialising UserPlane for LevelOne");
		System.out.println("getUser(): " + level.getUser());
		level.getRoot().getChildren().add(level.getUser());
	}

	@Override
	protected void spawnEnemyUnits(LevelParent level) {
		int currentNumberOfEnemies = level.getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * level.getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(level.getScreenWidth(), newEnemyInitialYPosition);
				level.addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView(LevelParent level) {
		return new LevelView(level.getRoot(), PLAYER_INITIAL_HEALTH);
	}

	@Override
	public void checkIfGameOver(LevelParent level) {
		if (level.userIsDestroyed()) {
			level.loseGame();
		}
		else if (level.getUser().getNumberOfKills() >= KILLS_TO_ADVANCE) {}
			level.goToNextLevel(NEXT_LEVEL);
	}
*/

	public LevelOne(double screenHeight, double screenWidth) {
		super(
				BACKGROUND_IMAGE_NAME,
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createDefaultUserPlane(screenHeight)
		);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget())
			goToNextLevel(NEXT_LEVEL);
	}

	@Override
	protected void initializeFriendlyUnits() {
		System.out.println("Initialising UserPlane for LevelOne");
		System.out.println("getUser(): " + getUser());
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}



}
