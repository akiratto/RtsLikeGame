package akiratto.rtslikegame.simulation.object.soldier;

import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.Soldier;
import com.badlogic.gdx.utils.ObjectMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akira shimada
 */
public class SoldierActionIdle implements SoldierAction {
    
    private final Soldier owner;
    private final float intervalMs;
    
    public SoldierActionIdle(Soldier owner, float interval)
    {
        this.owner = owner;
        this.intervalMs = interval;
    }

    @Override
    public SoldierAction execute(GameStatus gameStatus, float deltaTime) {
        if(intervalMs >= 50) {
            Soldier closetEnemySoldier = searchClosetEnemySoldier(owner, gameStatus.soldiers);
            if(closetEnemySoldier == null) {
                return new SoldierActionIdle(owner, 0);
            }
            owner.target = closetEnemySoldier;
            return new SoldierActionRotateTo(owner, owner.target);
        }
        return new SoldierActionIdle(owner, intervalMs + deltaTime * 1000);
    }
    
    private Soldier searchClosetEnemySoldier(Soldier owner, ObjectMap<String, Soldier> targetSoldiers)
    {
        List<Soldier> soldiersInRange = new ArrayList<>();
        for(int i = 0; i < targetSoldiers.size ; i++) {
            Soldier target = targetSoldiers.values().toArray().get(i);
            if(owner == target) continue;
            if(owner.team == target.team) continue;
            
            float x1 = owner.x;
            float y1 = owner.y;
            float x2 = target.x;
            float y2 = target.y;
            double distance = Math.sqrt((Math.pow(x2 - x1, 2)) + (Math.pow(y1 - y2, 2)));
            if(distance <= owner.searchDistance) {
                soldiersInRange.add(target);
            }
        }
        
        double closetDistance = Double.MAX_VALUE;
        Soldier closetEnemy = null;
        for(Soldier target : soldiersInRange) {
            float x1 = owner.x;
            float y1 = owner.y;
            float x2 = target.x;
            float y2 = target.y;
            double distance = Math.sqrt((Math.pow(x2 - x1, 2)) + (Math.pow(y1 - y2, 2)));
            if(closetDistance > distance) {
                closetDistance = distance;
                closetEnemy = target;
            }
        }
        return closetEnemy;
    }
}
