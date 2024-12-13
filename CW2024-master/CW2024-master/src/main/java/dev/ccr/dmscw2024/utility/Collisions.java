package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;

import java.util.List;

/**
 * Collisions - handles collisions
 */
public class Collisions {
    /**
     * handles collisions between 2 lists of actors
     * @param actors1 1st list of actors
     * @param actors2 2nd list of actors
     */
    public static void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }
}
