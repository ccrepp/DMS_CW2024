package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.user.UserPlane;

import java.util.List;

/**
 * Game State - maintains and updates game's state
 */
public class GameState {

    /**
     * updates LevelView
     * @param levelView current LevelView
     * @param user player
     */
    public static void updateLevelView(LevelView levelView, UserPlane user) {
        levelView.removeHearts(user.getHealth());
    }

    /**
     * updates Kill Count
     * @param user player
     * @param currentNumberOfEnemies current number of enemies
     * @param enemyUnits list of enemy units
     */
    public static void updateKillCount(UserPlane user, int currentNumberOfEnemies, List<ActiveActorDestructible> enemyUnits) {
        int killsToIncrement = currentNumberOfEnemies - enemyUnits.size();
        for (int i = 0; i < killsToIncrement; i++)
        {
            user.incrementKillCount();
            System.out.println("LevelParent: Kill Count Incremented, Total Kills: " + user.getNumberOfKills());
        }
    }

    /**
     * checks if enemy has entered left bound of screen
     * @param enemy enemy unit to be checked
     * @param screenWidth width of screen, since boundary is to be checked
     * @return {@code true} if enemy has entered left bound, {@code false} otherwise
     */
    public static boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    /**
     * updates and returns number of remaining enemies
     * @param enemyUnits
     * @return
     */
    public static int updateNumberOfEnemies (List<ActiveActorDestructible> enemyUnits) {
        return enemyUnits.size();
    }


}
