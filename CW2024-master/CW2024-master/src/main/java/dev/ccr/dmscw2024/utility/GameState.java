package dev.ccr.dmscw2024.utility;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.levels.LevelView;
import dev.ccr.dmscw2024.planes.user.UserPlane;

import java.util.List;

public class GameState {

    public static void updateLevelView(LevelView levelView, UserPlane user) {
        levelView.removeHearts(user.getHealth());
    }

    public static void updateKillCount(UserPlane user, int currentNumberOfEnemies, List<ActiveActorDestructible> enemyUnits) {
        int killsToIncrement = currentNumberOfEnemies - enemyUnits.size();
        for (int i = 0; i < killsToIncrement; i++)
        {
            user.incrementKillCount();
            System.out.println("LevelParent: Kill Count Incremented, Total Kills: " + user.getNumberOfKills());
        }
    }

    public static boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    public static int updateNumberOfEnemies (List<ActiveActorDestructible> enemyUnits) {
        return enemyUnits.size();
    }


}
