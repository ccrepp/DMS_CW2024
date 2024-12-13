package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.controller.Controller;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.screens.Win;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.bosses.Boss;
import dev.ccr.dmscw2024.specials.shield.ShieldImage;

import javafx.stage.Stage;

/**
 * Level Boss
 */
public class LevelBoss extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/default/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private final Controller controller;
	private LevelView levelView;

	/**
	 * Level Boss Constructor
	 * @param screenHeight Game Screen Height
	 * @param screenWidth Game Screen Width
	 * @param stage Game JavaFX stage
	 * @param controller Game controller
	 */
	public LevelBoss(double screenHeight, double screenWidth, Stage stage, Controller controller) {
		super(
				BACKGROUND_IMAGE_NAME,
                "/dev/ccr/dmscw2024/audio/bgm/BGM.mp3",
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createCustomUserPlane(
						"/dev/ccr/dmscw2024/images/default/userplane.png",
						150,
						50.0,
						screenHeight / 2,
						5
				),
				stage
		);
		boss = new Boss();
		this.controller = controller;
	}

	/**
	 * Create and Return {@link LevelView} for Level Boss
	 * @return {@link LevelView} for Level Boss
	 */
	@Override
	public LevelView instantiateLevelView() {
		return levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Initialises user-controlled plane and shield for Level Boss : <br/>
	 * UserPlane & ShieldImage
	 */
	@Override
    public void initialiseFriendlyUnits() {
		addFriendlyUnit(getUser());
		getRoot().getChildren().add((ShieldImage) boss.getShield());
	}

	/**
	 * Spawn sole Boss enemy unit for Level Boss
	 */
	@Override
	public void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnits(boss);
		}
	}

	/**
	 * Checks if Level Boss is over if either
	 * <ul>
	 *     <li>user-controlled plane is destroyed, results in LOSS</li>
	 *     <li>user has killed Boss, results in Transition Screen</li>
	 * </ul>
	 */
	@Override
    public void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
			BossWinScreenTransition();
		}
	}

	/**
	 * Handles Boss Win Screen transition after Boss is defeated <br/>
	 * Proceeds to Win Screen after
	 */
	private void BossWinScreenTransition() {
		System.out.println("BossWinScreenTransition");
		Transition transition = new Transition(
				getStage(),
				"/dev/ccr/dmscw2024/images/background/bgstart.jpg",
				"YOU WIN!",
				() -> {
					System.out.println("WinScreen");
					WinScreen();
				}
		);
		transition.display();
	}

	/**
	 * Displays Win Screen after Level Boss completion
	 */
	private void WinScreen() {
		System.out.println("WinScreen!");
		Win win = new Win(getStage(), controller);
		win.display();
	}
}
