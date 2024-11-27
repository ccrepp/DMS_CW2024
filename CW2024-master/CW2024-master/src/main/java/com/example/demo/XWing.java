package com.example.demo;

public class XWing extends UserPlane{

    private static final String IMAGE_NAME = "/com/example/demo/images/xwing.png";
    private static final int IMAGE_HEIGHT = 150;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int PROJECTILE_X_POSITION_OFFSET = 80;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

    public XWing(int initialHealth){
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
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
