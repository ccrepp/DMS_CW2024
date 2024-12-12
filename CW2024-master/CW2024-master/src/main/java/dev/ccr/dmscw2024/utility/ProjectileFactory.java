package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.projectile.Projectile;

public class ProjectileFactory {
    public static Projectile createProjectile (String type, double initialXPos, double initialYPos){
        return switch (type) {
            case "User" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/default/userfire.png",
                            125, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/projectile/UserFire.mp3", 0.1);
            case "Enemy" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/default/enemyfire.png",
                            50, initialXPos, initialYPos, -10,
                            "/dev/ccr/dmscw2024/audio/projectile/UserFire.mp3", 0.05);
            case "XWing" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/bluelaser.png",
                            15, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/projectile/XWingFire.mp3", 0.25);
            case "TieFighter" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/greenlaser.png",
                            25, initialXPos, initialYPos, -10,
                            "/dev/ccr/dmscw2024/audio/projectile/TieFighterFire.mp3", 0.2);
            case "Boss" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/default/fireball.png",
                            75, initialXPos, initialYPos, -7,
                            "/dev/ccr/dmscw2024/audio/projectile/BossFire.mp3",0.25);
            case "ISD" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            50, initialXPos, initialYPos, -5,
                            "/dev/ccr/dmscw2024/audio/projectile/ISDFire.mp3", 0.4);

            case "N1SF" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/greenlaser.png",
                            15, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/projectile/XWingFire.mp3", 0.25);
            case "AAT" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            30, initialXPos, initialYPos, -10,
                            "/dev/ccr/dmscw2024/audio/projectile/AATFire.mp3", 0.2);
            case "VultureDroid" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            15, initialXPos, initialYPos, -10,
                            "/dev/ccr/dmscw2024/audio/projectile/TieFighterFire.mp3", 0.2);
            case "TFS" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            40, initialXPos, initialYPos, -5,
                            "/dev/ccr/dmscw2024/audio/projectile/ISDFire.mp3", 0.4);

            case "Slave1" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            10, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/projectile/Slave1Fire.mp3", 0.3);
            case "OWS" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/greenlaser.png",
                            15, initialXPos, initialYPos, -15,
                            "/dev/ccr/dmscw2024/audio/projectile/XWingFire.mp3", 0.25);
            case "Padme" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/bluelaser.png",
                            10, initialXPos, initialYPos, -15,
                            "/dev/ccr/dmscw2024/audio/projectile/BlasterFire2.mp3", 0.3);
            case "BattleDroid" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            10, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/projectile/BlasterFire1.mp3", 0.2);
            case "B1Droid" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            15, initialXPos, initialYPos, 20,
                            "/dev/ccr/dmscw2024/audio/projectile/BlasterFire1.mp3", 0.25);

            case "ATTE" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/greenlaser.png",
                            20, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/projectile/ATTEFire.mp3", 0.25);
            case "RepublicGunship" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/greenlaser.png",
                            15, initialXPos, initialYPos, 15,
                            "/dev/ccr/dmscw2024/audio/projectile/ATTEFire.mp3", 0.25);
            case "HailfireDroid" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            15, initialXPos, initialYPos, -10,
                            "/dev/ccr/dmscw2024/audio/projectile/HailFire.mp3", 0.2);
            case "HomingSpiderDroid" ->
                    new ProjectileProduction("/dev/ccr/dmscw2024/images/SW/redlaser.png",
                            15, initialXPos, initialYPos, -10,
                            "/dev/ccr/dmscw2024/audio/projectile/SpiderFire.mp3", 0.2);

            default -> throw new IllegalArgumentException("Unsupported Projectile type: " + type);
        };
    }
}
