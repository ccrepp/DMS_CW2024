package dev.ccr.dmscw2024.projectile;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;

import dev.ccr.dmscw2024.utility.AudioManager;
import dev.ccr.dmscw2024.utility.ProjectileProduction;
import javafx.scene.Group;

import java.util.*;

/**
 * Projectile Manager - manages all projectile behaviours
 */
public class ProjectileManager {

    private final List<ActiveActorDestructible> projectiles;
    private final Group root;

    /**
     * ProjectileManager constructor
     * @param root root node of scene graph which projectile is to be added to
     */
    public ProjectileManager(Group root) {
        this.projectiles = new ArrayList<>();
        this.root = root;
    }

    /**
     * adds projectile to specified list and scene graph
     * @param projectile projectile to be added
     * @param projectileList list to which projectile should be added to
     */
    public void addProjectile(ActiveActorDestructible projectile, List<ActiveActorDestructible> projectileList) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            projectileList.add(projectile);

            if (projectile instanceof  ProjectileProduction projectileProduction) {
                String projectileAudioFile = projectileProduction.getProjectileAudioFile();
                double volume = projectileProduction.getVolume();
                if (projectileAudioFile != null) {
                    AudioManager.playAudioEffect(projectileAudioFile, volume);
                }
            }
        }
    }

    /**
     * updates projectile positions and removes destroyed projectiles
     * @param userProjectiles list of projectiles user has fired
     * @param enemyProjectiles list of projectiles enemy has fired
     */
    public void updateProjectiles(List<ActiveActorDestructible> userProjectiles, List<ActiveActorDestructible> enemyProjectiles) {
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);

        removeDestroyedProjectiles(userProjectiles);
        removeDestroyedProjectiles(enemyProjectiles);

    }

    /**
     * removes destroyed projectiles from scene graph and specified list
     * @param projectiles list of projectiles to check and update
     */
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

    /**
     * retrieves list of all active projectiles
     * @return list of all active projectiles
     */
    public List<ActiveActorDestructible> getProjectiles() {
        return projectiles;
    }
}
