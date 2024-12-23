package dev.ccr.dmscw2024.specials;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Game Over Image - displayed when player loses
 */
public class GameOverImage extends ImageView {
	
	private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/default/gameover.png";

	/**
	 * GameOverImage constructor
	 * @param xPosition x-axis position
	 * @param yPosition y-axis position
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()) );
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
