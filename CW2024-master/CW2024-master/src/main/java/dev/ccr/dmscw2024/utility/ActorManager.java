package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import javafx.scene.Group;

import java.util.List;

public class ActorManager {
    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;

    private final Group root;

    public ActorManager(Group root, List<ActiveActorDestructible> friendlyUnits, List<ActiveActorDestructible> enemyUnits) {
        this.root = root;
        this.friendlyUnits = friendlyUnits;
        this.enemyUnits = enemyUnits;
    }

    public void addFriendlyUnits(ActiveActorDestructible friendly) {
        friendlyUnits.add(friendly);
        root.getChildren().add(friendly);
    }

    public void addEnemyUnits(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    public void initialiseFriendlyUnits(List <ActiveActorDestructible> initialFriendlyUnits) {
        friendlyUnits.addAll(initialFriendlyUnits);
        root.getChildren().addAll(initialFriendlyUnits);
    }

    public void initialiseEnemyUnits(List <ActiveActorDestructible> initialEnemyUnits) {
        enemyUnits.addAll(initialEnemyUnits);
        root.getChildren().addAll(initialEnemyUnits);
    }

    public void updateActors() {
        friendlyUnits.forEach(ActiveActorDestructible::updateActor);
        enemyUnits.forEach(ActiveActorDestructible::updateActor);
    }

    public void removeDestroyedActors(List <ActiveActorDestructible> projectiles) {
        friendlyUnits.removeIf(ActiveActorDestructible::isDestroyed);
        enemyUnits.removeIf(ActiveActorDestructible::isDestroyed);
        projectiles.removeIf(ActiveActorDestructible::isDestroyed);
        root.getChildren().removeIf(node ->
                node instanceof ActiveActorDestructible && ((ActiveActorDestructible) node).isDestroyed());
    }
}
