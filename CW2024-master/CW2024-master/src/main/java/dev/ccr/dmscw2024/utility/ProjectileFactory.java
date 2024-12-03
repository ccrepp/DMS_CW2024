package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.projectile.Projectile;

public class ProjectileFactory {
    public static Projectile createProjectile (String type, double initialXPos, double initialYPos){
        switch (type) {
            case "User" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/userfire.png", 125, initialXPos, initialYPos, 10);
            case "Enemy" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/enemyfire.png", 50, initialXPos, initialYPos, -5);
            case "XWing" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/bluelaser.png", 15, initialXPos, initialYPos, 10);
            case "TieFighter" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/greenlaser.png", 25, initialXPos, initialYPos, -5);
            case "Boss" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/fireball.png", 75, initialXPos, initialYPos, -3);
            case "ISD" :
                return new ProjectileProduction("/dev/ccr/dmscw2024/images/redlaser.png", 50, initialXPos, initialYPos, -3);

            default :
                throw new IllegalArgumentException("Unsupported Projectile type: " + type);
        }
    }
}
