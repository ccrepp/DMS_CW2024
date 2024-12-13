package dev.ccr.dmscw2024.fundamentals;

import dev.ccr.dmscw2024.interfaces.Destructible;
import dev.ccr.dmscw2024.interfaces.Collidable;

/**
 * Active Actor Destructible - an extension of ActiveActor, those that can be destroyed
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible, Collidable {

	private boolean isDestroyed;

	/**
	 * ActiveActorDestructible constructor
	 * @param imageName file name of image to be loaded
	 * @param imageHeight height of image
	 * @param initialXPos initial X-axis positioning
	 * @param initialYPos initial Y-axis positioning
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	// Destructible Methods

	/**
	 * updates position
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * updates actor
	 */
	public abstract void updateActor();

	/**
	 * when taking damage
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * marks actor as destroyed, updates actor's state as destroyed
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * sets destruction state of actor
	 * @param isDestroyed {@code true} if actor is destroyed, {@code false} if otherwise
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * checks if actor is destroyed
	 * @return {@code true} if actor is destroyed, {@code false} if otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

	// Collidable Methods

	/**
	 * checks if actor has collided with another collidable
	 * @param other other {@link Collidable} object
	 * @return {@code true} if collision detected, {@code false} if otherwise
	 */
	@Override
	public boolean checkCollision(Collidable other) {
		if (other instanceof ActiveActorDestructible) {
			return this.getBoundsInParent().intersects(
					((ActiveActorDestructible) other).getBoundsInParent()
			);
		}
		return false;
	}

	/**
	 * handles logic for when actor collides with other collidable
	 * @param other other {@link Collidable} object
	 */
	@Override
	public void onCollision(Collidable other) {
		this.takeDamage();
	}
}