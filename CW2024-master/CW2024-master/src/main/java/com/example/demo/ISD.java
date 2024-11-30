package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ISD extends FighterPlane{

    private static final String IMAGE_NAME = "/com/example/demo/images/ISD.png";
    private static final double INITIAL_X_POSITION = 900.0;
    private static final double INITIAL_Y_POSITION = 300;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double ISD_FIRE_RATE = .02;
    private static final double ISD_SHIELD_PROBABILITY = 0.005;
    private static final int IMAGE_HEIGHT = 275;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 15;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 3;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 20;
    private static final int Y_POSITION_UPPER_BOUND = -100;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_FRAMES_WITH_SHIELD = 250;
    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;

    private final ISDShield isdShield;

    public ISD() {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        System.out.println("ISD: Base Constructor COMPLETE");
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        initializeMovePattern();

        isdShield = new ISDShield(INITIAL_X_POSITION, INITIAL_Y_POSITION);
        System.out.println("ISD: ISDShield CREATED");
    }

    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());

        double currentX = getLayoutY() + getTranslateY();
        double currentY = getLayoutX() + getTranslateX();

        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }

        isdShield.setLayoutX(getLayoutX() + getTranslateX() + (IMAGE_HEIGHT / 2.0) - (isdShield.getFitWidth() / 2.0) - 300);
        isdShield.setLayoutY(getLayoutY() + getTranslateY() + (IMAGE_HEIGHT / 2.0) - (isdShield.getFitHeight() / 2.0));
        //System.out.println("SHIELD POSITION UPDATED");
    }

    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
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

    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
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

    private boolean ISDFiresInCurrentFrame() {
        return Math.random() < ISD_FIRE_RATE;
    }

    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    private boolean shieldShouldBeActivated() {
        return Math.random() < ISD_SHIELD_PROBABILITY;
    }

    private boolean shieldExhausted() {
        //System.out.println("SHIELD EXHAUSTED");
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    private void activateShield() {
        isShielded = true;
        System.out.println("ACTIVATE SHIELD");
    }

    private void deactivateShield() {
        isShielded = false;
        System.out.println("DEACTIVATE SHIELD");
        framesWithShieldActivated = 0;
    }

    public ISDShield getISDShield() {
        return isdShield;
    }
}
