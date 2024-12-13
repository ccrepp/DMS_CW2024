package dev.ccr.dmscw2024.specials;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Win Image - displayed at the end of a series of levels
 */
public class WinImage extends ImageView {
	
	private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/default/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	/**
	 * WinImage constructor
	 * @param xPosition x-axis position
	 * @param yPosition y-axis position
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	/**
	 * displays win image by enabling visibility
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}
