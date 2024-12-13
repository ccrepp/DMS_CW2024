package dev.ccr.dmscw2024.planes.enemies;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.utility.ProjectileFactory;

/**
 * Tie Fighter - representing enemy unit
 */
public class TieFighter extends FighterPlane {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/tiefighter.png";
    private static final int IMAGE_HEIGHT = 150;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .01;

    /**
     * TieFighter constructor
     * @param initialXPos initial X-axis position
     * @param initialYPos initial Y-axis position
     */
    public TieFighter(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    /**
     * updates position of Tie Fighter via horizontal movement based on {@link #HORIZONTAL_VELOCITY}
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * fires projectile from TieFighter
     * @return TieFighter projectile if to be fired
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return ProjectileFactory.createProjectile("TieFighter", projectileXPosition, projectileYPosition);
        }
        return null;
    }

    /**
     * updates VultureDroid positioning
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
