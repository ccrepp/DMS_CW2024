package dev.ccr.dmscw2024.levels;

import dev.ccr.dmscw2024.specials.GameOverImage;
import dev.ccr.dmscw2024.specials.HeartDisplay;
import dev.ccr.dmscw2024.specials.WinImage;
import javafx.scene.Group;

/**
 * Level View - responsible for levels' visual layers
 */
public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -55;
	private static final int LOSS_SCREEN_Y_POSITION = -335;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;

	/**
	 * LevelView constructor
	 * @param root root visual container for level
	 * @param heartsToDisplay according to player initial health
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
	}

	/**
	 * Displays user's health with Heart Display <br/>
	 * Adds the {@link HeartDisplay} container to the level's {@code root} group
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays Win Image on screen
	 * Adds the {@link WinImage} to the level's {@code root} group and calls it
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays Game Over Image on screen
	 * Adds the {@link GameOverImage} to the level's {@code root} group and calls it
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Updates player's Heart Display according to current remaining health <br/>
	 * Removes excess hearts from {@link HeartDisplay}'s container based on differential between current and remainder
	 * @param heartsRemaining number of hearts to be displayed
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
