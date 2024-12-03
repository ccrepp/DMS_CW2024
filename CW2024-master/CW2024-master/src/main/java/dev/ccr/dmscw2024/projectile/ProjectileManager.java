package dev.ccr.dmscw2024.projectile;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;

import javafx.scene.Group;

import java.util.*;

public class ProjectileManager {

    private final List<ActiveActorDestructible> projectiles;
    private final Group root;

    public ProjectileManager(Group root) {
        this.projectiles = new ArrayList<>();
        this.root = root;
    }

    public void addProjectile(ActiveActorDestructible projectile, List<ActiveActorDestructible> projectileList) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            projectileList.add(projectile);
        }
    }

    public void updateProjectiles(List<ActiveActorDestructible> userProjectiles, List<ActiveActorDestructible> enemyProjectiles) {
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);

        removeDestroyedProjectiles(userProjectiles);
        removeDestroyedProjectiles(enemyProjectiles);

    }

    private void removeDestroyedProjectiles(List<ActiveActorDestructible> projectiles) {
        List<ActiveActorDestructible> destroyed = new ArrayList<>();
        for (ActiveActorDestructible p : projectiles) {
            if (p.isDestroyed()) {
                destroyed.add(p);
                root.getChildren().remove(p);
            }
        }
        projectiles.removeAll(destroyed);
    }

    public List<ActiveActorDestructible> getProjectiles() {
        return projectiles;
    }
}
