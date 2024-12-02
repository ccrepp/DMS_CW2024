package dev.ccr.dmscw2024.planes;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import javafx.scene.image.Image;

public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;

	/**
	 * Fighter Plane covers all planes displayed within the game
	 * @param imageName Image Name
	 * @param imageHeight Image Height
	 * @param initialXPos Initial x-axis position
	 * @param initialYPos Initial y-axis position
	 * @param health Health
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.setImage(new Image(getClass().getResource(imageName).toExternalForm()));
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.health = health;
	}

	public abstract ActiveActorDestructible fireProjectile();
	
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	private boolean healthAtZero() {
		return health == 0;
	}

	public int getHealth() {
		return health;
	}
		
}
