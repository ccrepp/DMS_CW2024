package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.enemies.EnemyPlane;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

public class Level2 extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/default/background1.jpg";
	private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.LevelSW1";
	private static final int TOTAL_ENEMIES = 6;
	private static final int KILLS_TO_ADVANCE = 15;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public Level2(double screenHeight, double screenWidth, Stage stage) {
		super(
				BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/bgm/BGM.mp3",
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createDefaultUserPlane(screenHeight),
				stage
		);
	}

	@Override
	public LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	@Override
	public void initialiseFriendlyUnits() {
		System.out.println("Initialising UserPlane for Level2");
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
			System.out.println("Level2: BEAT BY LEVEL - User is dead");
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			System.out.println("Level2: LEVEL BEAT - User has reached kill target");
			showTransitionScreen();
		}
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	private void showTransitionScreen() {
		Transition transition = new Transition(
				getStage(),
				"/dev/ccr/dmscw2024/images/background/transitionbackground.jpeg",
				"WHAT'S GOING ON!",
				() -> goToNextLevel(NEXT_LEVEL)
		);
		transition.display();
	}
}
