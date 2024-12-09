package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.planes.user.N1SF;
import dev.ccr.dmscw2024.planes.user.UserPlane;
import dev.ccr.dmscw2024.planes.user.XWing;

public class PlaneFactory {

    public static UserPlane createDefaultUserPlane(double screenHeight) {
        return new UserPlane(
                "/dev/ccr/dmscw2024/images/userplane.png",
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
                "/dev/ccr/dmscw2024/images/xwing.png",
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
                "/dev/ccr/dmscw2024/images/n1sf.png",
                75,
                50.0,
                screenHeight/2,
                5);
    }

    public static N1SF createCustomN1SF(String imageName, int imageHeight, double initialX, double initialY, int health) {
        return new N1SF(imageName, imageHeight, initialX, initialY, health);
    }
}
