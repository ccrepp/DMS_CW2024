package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import javafx.scene.Group;

import java.util.List;

/**
 * Actor Manager - manages all actors
 */
public class ActorManager {
    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;

    private final Group root;

    /**
     * ActorManager constructor
     * @param root root for displaying actors
     * @param friendlyUnits list of friendly units
     * @param enemyUnits list of enemy units
     */
    public ActorManager(Group root, List<ActiveActorDestructible> friendlyUnits, List<ActiveActorDestructible> enemyUnits) {
        this.root = root;
        this.friendlyUnits = friendlyUnits;
        this.enemyUnits = enemyUnits;
    }

    /**
     * adds friendly unit to list and displays it into game
     * @param friendly friendly unit to add
     */
    public void addFriendlyUnits(ActiveActorDestructible friendly) {
        friendlyUnits.add(friendly);
        root.getChildren().add(friendly);
    }

    /**
     * adds enemy unit to list and displays it into game
     * @param enemy enemy unit to add
     */
    public void addEnemyUnits(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * initialises list of friendly units and displays them
     * @param initialFriendlyUnits initial list of friendly units
     */
    public void initialiseFriendlyUnits(List <ActiveActorDestructible> initialFriendlyUnits) {
        friendlyUnits.addAll(initialFriendlyUnits);
        root.getChildren().addAll(initialFriendlyUnits);
    }

    /**
     * initialises list of enemy units and displays them
     * @param initialEnemyUnits initial list of enemy units
     */
    public void initialiseEnemyUnits(List <ActiveActorDestructible> initialEnemyUnits) {
        enemyUnits.addAll(initialEnemyUnits);
        root.getChildren().addAll(initialEnemyUnits);
    }

    /**
     * updates all actors
     */
    public void updateActors() {
        friendlyUnits.forEach(ActiveActorDestructible::updateActor);
        enemyUnits.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * removes destroyed actors from the game
     * @param projectiles list of projectiles to check for removal
     */
    public void removeDestroyedActors(List <ActiveActorDestructible> projectiles) {
        friendlyUnits.removeIf(ActiveActorDestructible::isDestroyed);
        enemyUnits.removeIf(ActiveActorDestructible::isDestroyed);
        projectiles.removeIf(ActiveActorDestructible::isDestroyed);
        root.getChildren().removeIf(node ->
                node instanceof ActiveActorDestructible && ((ActiveActorDestructible) node).isDestroyed());
    }
}
