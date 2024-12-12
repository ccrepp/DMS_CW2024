package dev.ccr.dmscw2024.levels.AOTCclone;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.enemies.HailfireDroid;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

public class AOTCc1 extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/Battle_of_Geonosis.jpg";
	private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.AOTCclone.AOTCc2";
	private static final int TOTAL_ENEMIES = 3;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public AOTCc1(double screenHeight, double screenWidth, Stage stage) {
		super(
				BACKGROUND_IMAGE_NAME,
				"/dev/ccr/dmscw2024/audio/bgm/GeonosisBattle.mp3",
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createDefaultATTE(screenHeight),
				stage
		);
	}

	@Override
	public LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	@Override
	public void initialiseFriendlyUnits() {
		addFriendlyUnit(getUser());
    }

	@Override
	public void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new HailfireDroid(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnits(newEnemy);
			}
		}
	}

	@Override
    public void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			showTransitionScreen();
		}
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	private void showTransitionScreen() {
		Transition transition = new Transition(
				getStage(),
				"/dev/ccr/dmscw2024/images/AOTC/bg/Battle_of_Geonosis.jpg",
				"there's more to this army! \n take them down!",
				() -> goToNextLevel(NEXT_LEVEL)
		);
		transition.display();
	}
}
