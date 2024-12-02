package dev.ccr.dmscw2024.fundamentals;

import dev.ccr.dmscw2024.levels.Collidable;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible, Collidable {

	private boolean isDestroyed;

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	// Destructible Methods

	@Override
	public abstract void updatePosition();

	public abstract void updateActor();

	@Override
	public abstract void takeDamage();

	@Override
	public void destroy() {
		setDestroyed(true);
	}

	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	// Collidable Methods


	@Override
	public boolean checkCollision(Collidable other) {
		if (other instanceof ActiveActorDestructible) {
			return this.getBoundsInParent().intersects(
					((ActiveActorDestructible) other).getBoundsInParent()
			);
		}
		return false;
	}

	@Override
	public void onCollision(Collidable other) {
		this.takeDamage();
	}
}