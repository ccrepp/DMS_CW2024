package dev.ccr.dmscw2024.levels.AOTCclone;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.levels.LevelParent;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.enemies.HailfireDroid;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import javafx.stage.Stage;

/**
 * AOTC c(lone) 3
 */
public class AOTCc3 extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/bg/Battle_of_Geonosis.jpg";
	private static final String NEXT_LEVEL = "dev.ccr.dmscw2024.levels.AOTCclone.AOTCc4";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 15;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * AOTCc3 Constructor
	 * @param screenHeight Game Screen Height
	 * @param screenWidth Game Screen Width
	 * @param stage Game JavaFX stage
	 */
	public AOTCc3(double screenHeight, double screenWidth, Stage stage) {
		super(
				BACKGROUND_IMAGE_NAME,
				"/dev/ccr/dmscw2024/audio/bgm/GeonosisBattle.mp3",
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createDefaultRepublicGunship(screenHeight),
				stage
		);
	}

	/**
	 * Create and Return {@link LevelView} for AOTCc3
	 * @return {@link LevelView} for AOTCc3
	 */
	@Override
	public LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Initialises user-controlled plane for AOTCc3 : <br/>
	 * RepublicGunship
	 */
	@Override
	public void initialiseFriendlyUnits() {
		addFriendlyUnit(getUser());
    }

	/**
	 * Spawns HailfireDroids for AOTCc3 <br/>
	 * uses probability-based spawning <br/>
	 * ensures total number of enemies on screen does NOT exceed {@code TOTAL_ENEMIES}
	 */
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

	/**
	 * Checks if AOTCc3 is over if either
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
			AOTCc3TransitionScreen();
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
	 * Displays a transition screen
	 * This method creates a {@link Transition} object configured with specific
	 * display properties and navigates to the next game level when the transition is completed.
	 * Utilizes {@link Transition#display()} to render the transition screen and
	 * {@link #goToNextLevel(String)} to proceed to the designated level, as specified
	 * by the class field {@code NEXT_LEVEL}.
	 */
	private void AOTCc3TransitionScreen() {
		Transition transition = new Transition(
				getStage(),
				"/dev/ccr/dmscw2024/images/AOTC/bg/Battle_of_Geonosis.jpg",
				"keep going! \n their numbers are dwindling!",
				() -> goToNextLevel(NEXT_LEVEL)
		);
		transition.display();
	}
}
