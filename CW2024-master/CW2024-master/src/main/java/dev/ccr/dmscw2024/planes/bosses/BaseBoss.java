package dev.ccr.dmscw2024.planes.bosses;


import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.interfaces.Shield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base Boss abstract class - to be utilised in the creation of every boss enemy in game
 */
public abstract class BaseBoss extends FighterPlane {

    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    protected final Shield shield;

    /**
     * BaseBoss constructor
     * @param imageName file name of image to be used
     * @param imageHeight height of image
     * @param initialX initial X-axis position
     * @param initialY initial Y-axis position
     * @param health initial health
     * @param shield shield to be used
     */
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

    /**
     * updates boss unit
     */
    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
    }

    /* Movement Logic */

    /**
     * updates boss unit's positioning <br/>
     * updates shield to position dynamically
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());

        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < getYPUB() || currentPosition > getYPLB()) {
            setTranslateY(initialTranslateY);
        }

        shield.setPosition(getShieldXOffset(),getShieldYOffset());
    }

    /**
     * initialises movement pattern to be followed by boss unit
     */
    private void initializeMovePattern() {
        for (int i = 0; i < getMFPC(); i++) {
            movePattern.add(getVV());
            movePattern.add(-getVV());
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * calculates next move to be taken by boss
     * @return move to be taken
     */
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

    /**
     * fires projectile from boss unit
     * @return projectile fired by boss unit, but if none, return {@code null}
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return shouldFireProjectile() ? createProjectile() : null;
    }

    protected abstract boolean shouldFireProjectile();
    protected abstract ActiveActorDestructible createProjectile();

    /* Shield Logic */

    /**
     * retrieves shield to be used by boss unit
     * @return shield
     */
    public Shield getShield() {
        return shield;
    }

    /**
     * updates shield's state <br/>
     * utilises shield probability for activation
     */
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

    /**
     * activates shield, displaying shield visually, negate boss taken damage
     */
    private void activateShield() {
        isShielded = true;
        shield.showShield();
    }

    /**
     * deactivates shield, no more shield visual, boss can take damage
     */
    private void deactivateShield() {
        isShielded = false;
        shield.hideShield();
        framesWithShieldActivated = 0;
    }

    /**
     * when shielded, boss cannot take damage
     */
    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    protected abstract double getSP();
}
