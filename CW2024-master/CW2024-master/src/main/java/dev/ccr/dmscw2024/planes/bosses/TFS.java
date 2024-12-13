package dev.ccr.dmscw2024.planes.bosses;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.specials.shield.SWShield;
import dev.ccr.dmscw2024.utility.ProjectileFactory;

public class TFS extends BaseBoss {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/TPM/TFS.png";
    private static final double INITIAL_X_POSITION = 900.0;
    private static final double INITIAL_Y_POSITION = 300;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double ISD_FIRE_RATE = 0.025;
    private static final double ISD_SHIELD_PROBABILITY = 0.005;
    private static final int IMAGE_HEIGHT = 275;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 20;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 1;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int Y_POSITION_UPPER_BOUND = 50;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_FRAMES_WITH_SHIELD = 250;

    public TFS() {
        super (
                "/dev/ccr/dmscw2024/images/TPM/TFS.png",
                IMAGE_HEIGHT,
                INITIAL_X_POSITION,
                INITIAL_Y_POSITION,
                HEALTH,
                new SWShield(900.0, 300.0)
        );
    }

    /* Movement Logic */
    @Override
    protected int getYPUB() {
        return Y_POSITION_UPPER_BOUND;
    }

    @Override
    protected int getYPLB() {
        return Y_POSITION_LOWER_BOUND;
    }

    @Override
    protected double getShieldXOffset() {
        return getLayoutX() + getTranslateX() - shield.getFitWidth();
    }

    @Override
    protected double getShieldYOffset() {
        return getLayoutY() + getTranslateY() + (getFitHeight() / 2.0) - (shield.getFitHeight() / 2.0 );
    }

    @Override
    protected int getVV() {
        return VERTICAL_VELOCITY;
    }

    @Override
    protected int getMFPC() {
        return MOVE_FREQUENCY_PER_CYCLE;
    }

    @Override
    protected int getMFWSM() {
        return MAX_FRAMES_WITH_SAME_MOVE;
    }

    /* Projectile Logic */

    @Override
    protected boolean shouldFireProjectile() {
        return Math.random() < ISD_FIRE_RATE;
    }

    @Override
    protected ActiveActorDestructible createProjectile() {
        double projectileXPos = getLayoutX() + getTranslateX() + (getFitWidth() / 2.0);
        double projectileYPos = getLayoutY() + getTranslateY() + (getFitHeight() / 4.0);
        return ProjectileFactory.createProjectile("TFS", projectileXPos, projectileYPos);
    }

    /* Shield Logic */
    @Override
    protected double getSP() {
        return ISD_SHIELD_PROBABILITY;
    }
}