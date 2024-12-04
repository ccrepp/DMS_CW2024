package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.utility.PlaneFactory;
import dev.ccr.dmscw2024.planes.bosses.Boss;
import dev.ccr.dmscw2024.specials.shield.ShieldImage;

import javafx.stage.Stage;

public class LevelBoss extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelView levelView;

	public LevelBoss(double screenHeight, double screenWidth, Stage stage) {
		super(
				BACKGROUND_IMAGE_NAME,
				"/dev/ccr/dmscw2024/audio/BGM.mp3",
				screenHeight,
				screenWidth,
				() -> PlaneFactory.createCustomUserPlane(
                        "/dev/ccr/dmscw2024/images/userplane.png",
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

}