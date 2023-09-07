package akiratto.rtslikegame.simulation.object.soldier;

import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.Soldier;
import akiratto.rtslikegame.util.MathHelper;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

/**
 *
 * @author akira shimada
 */
public class SoldierActionAttack implements SoldierAction {
    
    private final Soldier owner;

    public SoldierActionAttack(Soldier owner) {
        this.owner = owner;
    }
    
    @Override
    public SoldierAction execute(GameStatus gameStatus, float deltaTime) {
        ObjectMap<String, Soldier> soldiers = gameStatus.soldiers;
        for(Entry<String, Soldier> targetEntry : soldiers) {
            Soldier target = targetEntry.value;
            if(owner == target) continue;
            if(owner.team == target.team) continue;
            
            double distanceBetweenPoints = MathHelper.getDistanceBetweenPoints(owner.x, owner.y, target.x, target.y);
            if(owner.attackDistance >= distanceBetweenPoints) {
                double angleBetweenPointsInRadians = MathHelper.getAngleBetweenPointsInRadians(owner.x, owner.y, target.x, target.y);
                float startInDegree = owner.degree + owner.fireAngleInDegree - owner.fireAngleRangeInDegree/2;
                float endInDegree = startInDegree + owner.fireAngleRangeInDegree - 1;
                double startInRadians = Math.toRadians(startInDegree);
                double endInRadians = Math.toRadians(endInDegree);
                
                if(startInRadians <= angleBetweenPointsInRadians && angleBetweenPointsInRadians <= endInRadians) {
                    target.hpGauge.point -= 1;
                    target.hpGauge.updateGague = true;
                    if(target.hpGauge.point < 0) {
                        target.dead = true;
                    }
                }
            }
        }
        return new SoldierActionIdle(owner, 0);
    }
    
}
