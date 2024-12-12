package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.projectile.Projectile;

public class ProjectileProduction extends Projectile {
    private final double velocity;

    private final String projectileAudioFile;
    private final double volume;

    public ProjectileProduction (String imageName, int imageHeight, double initialXPos, double initialYPos, double velocity,
                                 String projectileAudioFile, double volume) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.velocity = velocity;

        this.projectileAudioFile = projectileAudioFile;
        this.volume = volume;
    }

    @Override
    public void updatePosition() {
        moveHorizontally(velocity);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    public String getProjectileAudioFile() {
        return projectileAudioFile;
    }

    public double getVolume() {
        return volume;
    }
}
