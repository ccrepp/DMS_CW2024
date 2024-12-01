package dev.ccr.dmscw2024.enemies;

import dev.ccr.dmscw2024.fundamentals.Projectile;

public class TieFighterProjectile extends Projectile {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/greenlaser1.png";
    private static final int IMAGE_HEIGHT = 25;
    private static final int HORIZONTAL_VELOCITY = -10;

    public TieFighterProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
