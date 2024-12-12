package dev.ccr.dmscw2024.planes.enemies;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.utility.ProjectileFactory;

public class HailfireDroid extends FighterPlane {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/droids/HailfireDroid.png";
    private static final int IMAGE_HEIGHT = 125;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .01;

    public HailfireDroid(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return ProjectileFactory.createProjectile("HailfireDroid", projectileXPosition, projectileYPosition);
        }
        return null;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
