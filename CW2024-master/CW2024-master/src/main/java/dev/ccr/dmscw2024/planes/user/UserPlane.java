package dev.ccr.dmscw2024.planes.user;

import dev.ccr.dmscw2024.fundamentals.*;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.planes.Movable;
import dev.ccr.dmscw2024.projectile.ProjectileFactory;

public class UserPlane extends FighterPlane implements Movable {

	private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_X_POSITION_OFFSET = 80;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int velocityMultiplier;
	private int numberOfKills;

	private boolean movingUp = false;
	private boolean movingDown = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;

	private static final double X_LEFT_BOUND = 0.0;
	private static final double X_RIGHT_BOUND = 500;
	private int horizontalVelocityMultiplier = 0;
	private static final int HORIZONTAL_VELOCITY = 8;

	public UserPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int initialHealth) {
		super(imageName, imageHeight, initialXPos, initialYPos, initialHealth);
		velocityMultiplier = 0;
	}

	/**
	 * position updating method
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
		if (isMovingHorizontally()) {
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
		}
	}

	/**
	 * user-controlled plane updating method
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		double projectile_X_Position = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
		double projectile_Y_Position = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
        return ProjectileFactory.createProjectile("User", projectile_X_Position, projectile_Y_Position);
	}

	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	private boolean isMovingHorizontally() {
		return horizontalVelocityMultiplier != 0;
	}

	/**
	 * allows for horizontal movement along x-axis
	 * @param deltaX x-axis positioning variable
	 *
	 */
	public void moveHorizontally (double deltaX) {
		double newTranslateX = getTranslateX() + deltaX;

		if (newTranslateX + getLayoutX() >= X_LEFT_BOUND && newTranslateX + getLayoutX() <= X_RIGHT_BOUND) {
			setTranslateX(newTranslateX);
		}
	}

	public void moveUp() {
		movingUp = true;
		movingDown = false;
		velocityMultiplier = -1;
	}

	public void moveDown() {
		movingUp = false;
		movingDown = true;
		velocityMultiplier = 1;
	}

	public void moveLeft() {
		movingLeft = true;
		movingRight = false;
		horizontalVelocityMultiplier = -1;
	}

	public void moveRight() {
		movingLeft = false;
		movingRight = true;
		horizontalVelocityMultiplier = 1;
	}

	public void stopVertically() {
		movingUp = false;
		movingDown = false;
		velocityMultiplier = 0;
	}

	public void stopHorizontal() {
		movingLeft = false;
		movingRight = false;
		horizontalVelocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

}
