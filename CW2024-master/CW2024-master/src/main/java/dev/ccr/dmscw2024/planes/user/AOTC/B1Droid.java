package dev.ccr.dmscw2024.planes.user.AOTC;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.user.UserPlane;
import dev.ccr.dmscw2024.utility.ProjectileFactory;

public class B1Droid extends UserPlane {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/B1Droid.png";
    private static final int PROJECTILE_X_POSITION_OFFSET = 80;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    public B1Droid(String imageName, int imageHeight, double initialXPos, double initialYPos, int initialHealth){
        super(imageName, imageHeight, initialXPos, initialYPos, initialHealth);
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        double projectile_X_Position = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
        double projectile_Y_Position = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
        return ProjectileFactory.createProjectile("B1Droid", projectile_X_Position, projectile_Y_Position);
    }

}
