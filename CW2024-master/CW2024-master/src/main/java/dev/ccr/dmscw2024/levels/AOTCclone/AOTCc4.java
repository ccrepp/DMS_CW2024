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

/**
 * AOTC c(lone) 4
 */
public class AOTCc4 extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/Battle_of_Geonosis.jpg";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 15;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Controller controller;

	/**
	 * AOTCc2 Constructor
	 * @param screenHeight Game Screen Height
	 * @param screenWidth Game Screen Width
	 * @param stage Game JavaFX stage
	 * @param controller Game controller
	 */
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

	/**
	 * Create and Return {@link LevelView} for AOTCc4
	 * @return {@link LevelView} for AOTCc4
	 */
	@Override
	public LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Initialises user-controlled plane for AOTCc4 : <br/>
	 * RepublicGunship
	 */
	@Override
	public void initialiseFriendlyUnits() {
		addFriendlyUnit(getUser());
    }

	/**
	 * Spawns HomingSpiderDroids for AOTCc4 <br/>
	 * uses probability-based spawning <br/>
	 * ensures total number of enemies on screen does NOT exceed {@code TOTAL_ENEMIES}
	 */
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

	/**
	 * Checks if AOTCc4 is over if either
	 * <ul>
	 *     <li>user-controlled plane is destroyed, results in LOSS</li>
	 *     <li>user has reached required kill-count to advance, results in Transition Screen</li>
	 * </ul>
	 */
	@Override
    public void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			winGame();
			AOTCc4ScreenTransition();
		}
	}

	/**
	 * Checks if user has reached required kill-count for advancement
	 * @return {@code true} if number of kills is greater than or equal to {@code KILLS_TO_ADVANCE},
	 * {@code false} otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Handles AOTCc4 Win Screen transition after kill-count is reached <br/>
	 * Proceeds to Win Screen after
	 */
	private void AOTCc4ScreenTransition() {
		Transition transition = new Transition(
				getStage(),
				"/dev/ccr/dmscw2024/images/AOTC/bg/ClonesWin.jpg",
				"that'll show them! \n the Clones win!",
				this::WinScreen
		);
		transition.display();
	}

	/**
	 * Displays Win Screen after AOTCc4 completion
	 */
	private void WinScreen() {
		Win win = new Win(getStage(), controller);
		win.display();
	}
}
