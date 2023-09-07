package akiratto.rtslikegame.simulation.object;

import akiratto.rtslikegame.simulation.object.soldier.SoldierActionIdle;
import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.common.Gauge;
import akiratto.rtslikegame.status.object.Soldier;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akira shimada
 */
public class SoldierSimu implements Simu {
    
    @Override
    public void update(GameStatus gameStatus, float delta) {
        ObjectMap<String, Soldier> soldiers = gameStatus.soldiers;
        deleteSoldiers(soldiers);
        Array<Soldier> soldiersArray = soldiers.values().toArray();
        for(int i = 0; i < soldiersArray.size; i++) {
            Soldier soldier = soldiersArray.get(i);
            updateSoldierAction(soldier, gameStatus, delta);
            updateHpGaugePosition(soldier);
        }
    }
    
    private void deleteSoldiers(ObjectMap<String, Soldier> soldiers)
    {
        List<String> deleteSoldierNames = new ArrayList<>();
        for(Entry<String, Soldier> soldierEntry : soldiers.entries()) {
            String  soldierName = soldierEntry.key;
            Soldier soldier = soldierEntry.value;
            if(soldier.dead) {
                soldier.hpGauge.dead = true;
                deleteSoldierNames.add(soldierName);
            }
        }
        
        for(String name : deleteSoldierNames) {
            soldiers.remove(name);
        }
    }
    
    private void updateSoldierAction(Soldier soldier, GameStatus gameStatus, float deltaTime)
    {
        if(soldier.action == null) {
            soldier.action = new SoldierActionIdle(soldier, 0);
        }
        soldier.action = soldier.action.execute(gameStatus, deltaTime);
    }
    
    private void updateHpGaugePosition(Soldier soldier)
    {
        Gauge hpGauge = soldier.hpGauge;
        hpGauge.x = soldier.x - hpGauge.width/2;
        hpGauge.y = soldier.y + 8 + hpGauge.height;
    }
}
