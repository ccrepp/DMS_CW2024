package dev.ccr.dmscw2024.fundamentals;

import javafx.scene.image.*;

/**
 * Active Actor - represents moving objects in the game
 */
public abstract class ActiveActor extends ImageView {

	/**
	 * ActiveActor constructor
	 * @param imageName file name of image to be loaded
	 * @param imageHeight image height for image
	 * @param initialXPos initial X-coordinate position
	 * @param initialYPos initial Y-coordinate position
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * updates position
	 */
	public abstract void updatePosition();

	/**
	 * allows for horizontal movement (X-axis)
	 * @param horizontalMove amount to be moved horizontally
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * allows for vertical movement (Y-axis)
	 * @param verticalMove amount to be moved vertically
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
