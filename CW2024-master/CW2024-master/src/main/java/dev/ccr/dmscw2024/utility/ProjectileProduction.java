package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.projectile.Projectile;

public class ProjectileProduction extends Projectile {
    private final double velocity;

    public ProjectileProduction (String imageName, int imageHeight, double initialXPos, double initialYPos, double velocity) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.velocity = velocity;
    }

    @Override
    public void updatePosition() {
        moveHorizontally(velocity);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
