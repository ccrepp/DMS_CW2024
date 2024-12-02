package dev.ccr.dmscw2024.bosses;

import dev.ccr.dmscw2024.projectile.Projectile;

public class ISDProjectile extends Projectile {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/redlaser.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -15;
    private static final int INITIAL_X_POSITION = 950;

    public ISDProjectile(double initialYPos, double projectileYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
        if (getLayoutX() < 100) {
            destroy();
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
