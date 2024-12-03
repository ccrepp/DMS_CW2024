package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;

import java.util.List;

public class Collisions {
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
