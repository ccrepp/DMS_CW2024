package dev.ccr.dmscw2024.bosses;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.fundamentals.FighterPlane;
import dev.ccr.dmscw2024.specials.shield.ISDShield;
import dev.ccr.dmscw2024.fundamentals.BaseBoss;
import dev.ccr.dmscw2024.specials.shield.ShieldImage;

import java.util.*;

public class ISD extends BaseBoss {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/ISD.png";
    private static final double INITIAL_X_POSITION = 900.0;
    private static final double INITIAL_Y_POSITION = 300;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double ISD_FIRE_RATE = .02;
    private static final double ISD_SHIELD_PROBABILITY = 0.05;
    private static final int IMAGE_HEIGHT = 275;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 15;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 3;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 20;
    private static final int Y_POSITION_UPPER_BOUND = -100;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_FRAMES_WITH_SHIELD = 250;
    //private final List<Integer> movePattern;

    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;

    //private final ISDShield isdShield;

    //private final LevelParent levelParent;

    public ISD() {


        /*
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        System.out.println("ISD: Base Constructor COMPLETE");
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        initializeMovePattern();

        isdShield = new ISDShield(INITIAL_X_POSITION, INITIAL_Y_POSITION);
        System.out.println("ISD: ISDShield CREATED at " + INITIAL_X_POSITION + ", " + INITIAL_Y_POSITION);
    }

    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
    }

    /* Movement Logic

    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());

        double currentX = getLayoutX() + getTranslateX();
        double currentY = getLayoutY() + getTranslateY();

        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }

        isdShield.setLayoutX(currentX + (IMAGE_HEIGHT / 2.0) - (isdShield.getFitWidth() / 2.0) - 275);
        isdShield.setLayoutY(currentY + (IMAGE_HEIGHT / 2.0) - (isdShield.getFitHeight() / 2.0));
    }

    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    /* Projectile Logic

    private boolean ISDFiresInCurrentFrame() {
        return Math.random() < ISD_FIRE_RATE;
    }

    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return ISDFiresInCurrentFrame() ? new ISDProjectile(getProjectileInitialPosition()) : null;
    }

    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
        if (getHealth() <= 0){
            destroy();
        }
    }

    public boolean isDestroyed(){
        return getHealth() <= 0;
    }

    /* Shield Logic

    public ISDShield getISDShield() {
        return isdShield;
    }

    private void updateShield() {
        if (isShielded) {
            framesWithShieldActivated++;
        }
        else if (shieldShouldBeActivated()) {
            activateShield();
            isdShield.showShield();
        }
        if (shieldExhausted()) {
            deactivateShield();
            isdShield.hideShield();
        }
    }

    private boolean shieldShouldBeActivated() {
        return Math.random() < ISD_SHIELD_PROBABILITY;
    }

    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    private void activateShield() {
        isShielded = true;
        System.out.println("ACTIVATE SHIELD");
        System.out.println("ISD: ISD currently at " + (getLayoutX() + getTranslateX()) + ", " + (getLayoutY() + getTranslateY()));
        System.out.println("ISD: ISDShield currently at " + isdShield.getLayoutX() + ", " + isdShield.getLayoutY());
    }

    private void deactivateShield() {
        isShielded = false;
        System.out.println("DEACTIVATE SHIELD");
        System.out.println("ISD: ISD currently at " + (getLayoutX() + getTranslateX()) + ", " + (getLayoutY() + getTranslateY()));
        System.out.println("ISD: ISDShield currently at " + isdShield.getLayoutX() + ", " + isdShield.getLayoutY());
        framesWithShieldActivated = 0;
    }
*/
        super (
                "/dev/ccr/dmscw2024/images/ISD.png",
                IMAGE_HEIGHT,
                INITIAL_X_POSITION,
                INITIAL_Y_POSITION,
                HEALTH,
                new ISDShield(900.0, 300.0)
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
        double projectileYPos = getLayoutY() + getTranslateY() + getFitHeight();
        return new ISDProjectile(projectileXPos, projectileYPos);
    }

    /* Shield Logic */
    @Override
    protected double getSP() {
        return ISD_SHIELD_PROBABILITY;
    }
}
