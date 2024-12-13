package dev.ccr.dmscw2024.projectile;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;

/**
 * Projectile - base abstract class for all projectiles
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Projectile constructor
	 * @param imageName image of projectile
	 * @param imageHeight image height
	 * @param initialXPos initial X-axis position
	 * @param initialYPos initial Y-axis position
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * handles projectile destruction upon taking damage <br/>
	 * projectiles are destroyed when damaged
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * updates projectile position
	 */
	@Override
	public abstract void updatePosition();

}
