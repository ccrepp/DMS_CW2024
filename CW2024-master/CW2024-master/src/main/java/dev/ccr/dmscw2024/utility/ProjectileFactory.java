package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.projectile.Projectile;

public class ProjectileFactory {
    public static Projectile createProjectile (String type, double initialXPos, double initialYPos){
        return switch (type) {
            case "User" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/userfire.png",
                            125, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/UserFire.mp3", 0.1);
            case "Enemy" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/enemyfire.png",
                            50, initialXPos, initialYPos, -10,
                            "/dev/ccr/dmscw2024/audio/UserFire.mp3", 0.05);
            case "XWing" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/bluelaser.png",
                            15, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/XWingFire.mp3", 0.25);
            case "TieFighter" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/greenlaser.png",
                            25, initialXPos, initialYPos, -10,
                            "/dev/ccr/dmscw2024/audio/TieFighterFire.mp3", 0.2);
            case "Boss" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/fireball.png",
                            75, initialXPos, initialYPos, -7,
                            "/dev/ccr/dmscw2024/audio/BossFire.mp3",0.25);
            case "ISD" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/redlaser.png",
                            50, initialXPos, initialYPos, -5,
                            "/dev/ccr/dmscw2024/audio/ISDFire.mp3", 0.4);
            default -> throw new IllegalArgumentException("Unsupported Projectile type: " + type);
        };
    }
}
