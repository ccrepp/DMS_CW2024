package dev.ccr.dmscw2024.fundamentals;

import javafx.scene.image.*;

public abstract class ActiveActor extends ImageView {

	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	public abstract void updatePosition();

	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
