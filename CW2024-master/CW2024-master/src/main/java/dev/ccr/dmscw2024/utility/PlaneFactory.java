package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.planes.user.AOTC.*;
import dev.ccr.dmscw2024.planes.user.N1SF;
import dev.ccr.dmscw2024.planes.user.UserPlane;
import dev.ccr.dmscw2024.planes.user.XWing;

/**
 * Plane Factory - a factory for (user-controlled) plane production
 */
public class PlaneFactory {

    public static UserPlane createDefaultUserPlane(double screenHeight) {
        return new UserPlane(
                "/dev/ccr/dmscw2024/images/default/userplane.png",
                150,
                50.0,
                screenHeight/2,
                5);
    }

    public static UserPlane createCustomUserPlane(String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new UserPlane(imageName, imageHeight, initialX, initialY, health);
    }

    public static XWing createDefaultXWing(double screenHeight) {
        return new XWing(
                "/dev/ccr/dmscw2024/images/SW/xwing.png",
                75,
                50.0,
                screenHeight/2,
                5);
    }

    public static XWing createCustomXWing(String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new XWing(imageName, imageHeight, initialX, initialY, health);
    }

    public static N1SF createDefaultN1SF(double screenHeight) {
        return new N1SF(
                "/dev/ccr/dmscw2024/images/TPM/n1sf.png",
                75,
                50.0,
                screenHeight/2,
                5);
    }

    public static N1SF createCustomN1SF(String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new N1SF(imageName, imageHeight, initialX, initialY, health);
    }

    public static Slave1 createDefaultSlave1(double screenHeight) {
        return new Slave1(
                "/dev/ccr/dmscw2024/images/AOTC/ships/Slave1.png",
                150,
                50.0,
                screenHeight/2,
                5);
    }

    public static Slave1 createCustomSlave1(String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new Slave1(imageName, imageHeight, initialX, initialY, health);
    }

    public static BattleDroid createDefaultBattleDroid(double screenHeight) {
        return new BattleDroid(
                "/dev/ccr/dmscw2024/images/AOTC/droids/BattleDroid.png",
                150,
                50.0,
                screenHeight/2,
                5);
    }

    public static BattleDroid createCustomBattleDroid(String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new BattleDroid(imageName, imageHeight, initialX, initialY, health);
    }

    public static B1Droid createDefaultB1Droid(double screenHeight) {
        return new B1Droid(
                "/dev/ccr/dmscw2024/images/AOTC/droids/B1Droid.png",
                175,
                50.0,
                screenHeight/2,
                5);
    }

    public static B1Droid createCustomB1Droid(String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new B1Droid(imageName, imageHeight, initialX, initialY, health);
    }

    public static ATTE createDefaultATTE (double screenHeight) {
        return new ATTE (
                "/dev/ccr/dmscw2024/images/AOTC/ships/ATTE.png",
                125,
                50.0,
                screenHeight/2,
                5);
    }

    public static ATTE  createCustomATTE (String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new ATTE (imageName, imageHeight, initialX, initialY, health);
    }

    public static RepublicGunship createDefaultRepublicGunship (double screenHeight) {
        return new RepublicGunship (
                "/dev/ccr/dmscw2024/images/AOTC/ships/RepublicGunship.png",
                125,
                50.0,
                screenHeight/2,
                5);
    }

    public static RepublicGunship  createCustomRepublicGunship (String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new RepublicGunship (imageName, imageHeight, initialX, initialY, health);
    }
}
