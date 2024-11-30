package dev.ccr.dmscw2024;

public class XWing extends UserPlane{

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/xwing.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int PROJECTILE_X_POSITION_OFFSET = 80;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    public XWing(String imageName, int imageHeight, double initialXPos, double initialYPos, int initialHealth){
        super(imageName, imageHeight, initialXPos, initialYPos, initialHealth);
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return new XWingProjectile(getProjectileXPosition(80), getProjectileYPosition(20));
    }

}
