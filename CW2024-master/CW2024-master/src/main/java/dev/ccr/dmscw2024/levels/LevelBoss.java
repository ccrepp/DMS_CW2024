package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.controller.Controller;
import dev.ccr.dmscw2024.screens.Transition;
import dev.ccr.dmscw2024.screens.Win;
import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.bosses.Boss;
import dev.ccr.dmscw2024.specials.shield.ShieldImage;

import javafx.stage.Stage;

public class LevelBoss extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/default/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private final Controller controller;
	private LevelView levelView;

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
		System.out.println("LevelTwo: Base Constructor COMPLETE");
		boss = new Boss();
		System.out.println("LevelTwo: Boss CREATED");
		this.controller = controller;
	}

	@Override
    public void initialiseFriendlyUnits() {
		System.out.println("LevelTwo: Adding UserPlane to Root");
//		getRoot().getChildren().add(getUser());
		addFriendlyUnit(getUser());
		System.out.println("LevelTwo: UserPlane CREATED");

		System.out.println("LevelTwo: Adding Boss Shield to Root");
		try{
			getRoot().getChildren().add((ShieldImage) boss.getShield());
			System.out.println("LevelTwo: Boss Shield CREATED");
		} catch(Exception e){
			System.out.println("LevelTwo: FAILED to Add Boss Shield : " + e.getMessage());
			e.printStackTrace();
		}
	}

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

	@Override
	public void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnits(boss);
		}
	}

	@Override
	public LevelView instantiateLevelView() {
		levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

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

	private void WinScreen() {
		System.out.println("WinScreen!");
		Win win = new Win(getStage(), controller);
		win.display();
	}
}
