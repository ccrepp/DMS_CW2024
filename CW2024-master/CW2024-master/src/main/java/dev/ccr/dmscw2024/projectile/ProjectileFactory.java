package dev.ccr.dmscw2024.projectile;

public class ProjectileFactory {
    public static Projectile createProjectile (String type, double initialXPos, double initialYPos){
        switch (type) {
            case "User" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/userfire.png", 125, initialXPos, initialYPos, 15);
            case "Enemy" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/enemyfire.png", 50, initialXPos, initialYPos, -10);
            case "XWing" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/bluelaser.png", 15, initialXPos, initialYPos, 15);
            case "TieFighter" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/greenlaser.png", 25, initialXPos, initialYPos, -10);
            default :
                throw new IllegalArgumentException("Unsupported Projectile type: " + type);
        }
    }
}
