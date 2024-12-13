package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.projectile.Projectile;

/**
 * Projectile Production - middleman for Projectile and ProjectileFactory
 */
public class ProjectileProduction extends Projectile {
    private final double velocity;

    private final String projectileAudioFile;
    private final double volume;

    /**
     * ProjectileProduction constructor
     * @param imageName projectile image name
     * @param imageHeight image height
     * @param initialXPos initial x-axis position
     * @param initialYPos initial y-axis position
     * @param velocity velocity of projectile travel
     * @param projectileAudioFile audio file of projectile
     * @param volume volume of audio
     */
    public ProjectileProduction (String imageName, int imageHeight, double initialXPos, double initialYPos, double velocity,
                                 String projectileAudioFile, double volume) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.velocity = velocity;

        this.projectileAudioFile = projectileAudioFile;
        this.volume = volume;
    }

    /**
     * updates position of projectile (with horizontal movement based on velocity)
     */
    @Override
    public void updatePosition() {
        moveHorizontally(velocity);
    }

    /**
     * refer to{@code updatePosition}
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * retrieves audio file for projectile
     * @return projectile sound effect
     */
    public String getProjectileAudioFile() {
        return projectileAudioFile;
    }

    /**
     * retrieves volume at which audio file for projectile is to be played at
     * @return volume
     */
    public double getVolume() {
        return volume;
    }
}
