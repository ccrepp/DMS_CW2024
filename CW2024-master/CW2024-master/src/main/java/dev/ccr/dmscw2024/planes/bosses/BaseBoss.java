package dev.ccr.dmscw2024.planes.bosses;


import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.specials.shield.Shield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseBoss extends FighterPlane {

    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    protected final Shield shield;

    public BaseBoss(String imageName, int imageHeight, double initialX, double initialY, int health, Shield shield) {
        super(imageName, imageHeight, initialX, initialY, health);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        this.shield = shield;
        initializeMovePattern();
    }

    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
    }

    /* Movement Logic */

    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());

        double currentX = getLayoutX() + getTranslateX();
        double currentY = getLayoutY() + getTranslateY();

        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < getYPUB() || currentPosition > getYPLB()) {
            setTranslateY(initialTranslateY);
        }

        shield.setPosition(getShieldXOffset(),getShieldYOffset());
    }

    private void initializeMovePattern() {
        for (int i = 0; i < getMFPC(); i++) {
            movePattern.add(getVV());
            movePattern.add(-getVV());
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == getMFWSM()) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    protected abstract int getYPUB();
    protected abstract int getYPLB();
    protected abstract double getShieldXOffset();
    protected abstract double getShieldYOffset();
    protected abstract int getVV();
    protected abstract int getMFPC();
    protected abstract int getMFWSM();

    /* Projectile Logic */

    @Override
    public ActiveActorDestructible fireProjectile() {
        return shouldFireProjectile() ? createProjectile() : null;
    }

    protected abstract boolean shouldFireProjectile();
    protected abstract ActiveActorDestructible createProjectile();

    /* Shield Logic */

    public Shield getShield() {
        return shield;
    }

    private void updateShield() {
        if (isShielded) {
            framesWithShieldActivated++;
        }
        else if (Math.random() < getSP()) {
            activateShield();
        }
        if (framesWithShieldActivated >= getMFWSM()) {
            deactivateShield();
        }
    }

    private void activateShield() {
        isShielded = true;
        shield.showShield();
    }

    private void deactivateShield() {
        isShielded = false;
        shield.hideShield();
        framesWithShieldActivated = 0;
    }

    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    protected abstract double getSP();
}
