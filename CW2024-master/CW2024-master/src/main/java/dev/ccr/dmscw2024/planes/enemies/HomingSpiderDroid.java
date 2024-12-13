package dev.ccr.dmscw2024.planes.enemies;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.utility.ProjectileFactory;

/**
 * Homing Spider Droid - representing enemy unit
 */
public class HomingSpiderDroid extends FighterPlane {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/droids/HomingSpiderDroid.png";
    private static final int IMAGE_HEIGHT = 125;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .01;

    /**
     * HomingSpiderDroid constructor
     * @param initialXPos initial X-axis position
     * @param initialYPos initial Y-axis position
     */
    public HomingSpiderDroid(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    /**
     * updates position of Homing Spider Droid via horizontal movement based on {@link #HORIZONTAL_VELOCITY}
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * fires projectile from HomingSpiderDroid
     * @return HomingSpiderDroid projectile if to be fired
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return ProjectileFactory.createProjectile("HomingSpiderDroid", projectileXPosition, projectileYPosition);
        }
        return null;
    }

    /**
     * updates HomingSpiderDroid positioning
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}