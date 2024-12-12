package dev.ccr.dmscw2024.levels.AOTCclone;

import dev.ccr.dmscw2024.controller.Controller;
import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.enemies.HomingSpiderDroid;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.screens.Win;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

public class AOTCc4 extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/Battle_of_Geonosis.jpg";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 15;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Controller controller;

	public AOTCc4(double screenHeight, double screenWidth, Stage stage, Controller controller) {
		super(
				BACKGROUND_IMAGE_NAME,
				"/dev/ccr/dmscw2024/audio/bgm/GeonosisBattle.mp3",
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createDefaultRepublicGunship(screenHeight),
				stage
		);
		this.controller = controller;
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
				ActiveActorDestructible newEnemy = new HomingSpiderDroid(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnits(newEnemy);
			}
		}
	}

	@Override
    public void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			winGame();
			AOTCScreenTransition();
		}
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	private void AOTCScreenTransition() {
		Transition transition = new Transition(
				getStage(),
				"/dev/ccr/dmscw2024/images/AOTC/bg/ClonesWin.jpg",
				"that'll show them! \n the Clones win!",
				this::WinScreen
		);
		transition.display();
	}

	private void WinScreen() {
		System.out.println("WinScreen!");
		Win win = new Win(getStage(), controller);
		win.display();
	}
}
