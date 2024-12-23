package dev.ccr.dmscw2024.planes.user;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.utility.ProjectileFactory;

/**
 * XWing - a user-controlled plane
 */
public class XWing extends UserPlane {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/xwing.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int PROJECTILE_X_POSITION_OFFSET = 80;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    /**
     * XWing constructor
     * @param imageName file name of image
     * @param imageHeight image height
     * @param initialXPos initial X-axis positioning
     * @param initialYPos initial Y-axis positioning
     * @param initialHealth initial player health
     */
    public XWing(String imageName, int imageHeight, double initialXPos, double initialYPos, int initialHealth){
        super(imageName, imageHeight, initialXPos, initialYPos, initialHealth);
    }

    /**
     * positioning update
     */
    @Override
    public void updatePosition() {
        super.updatePosition();
    }

    /**
     * firing of XWing projectiles
     * @return XWing projectiles
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        double projectile_X_Position = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
        double projectile_Y_Position = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
        return ProjectileFactory.createProjectile("XWing", projectile_X_Position, projectile_Y_Position);
    }

}
