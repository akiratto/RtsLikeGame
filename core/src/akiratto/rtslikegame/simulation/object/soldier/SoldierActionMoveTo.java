package akiratto.rtslikegame.simulation.object.soldier;

import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.Soldier;
import akiratto.rtslikegame.util.MathHelper;
import akiratto.rtslikegame.util.Point;

/**
 *
 * @author akira shimada
 */
public class SoldierActionMoveTo implements SoldierAction {

    private final Soldier owner;
    private final Soldier target;

    public SoldierActionMoveTo(Soldier owner, Soldier target) {
        this.owner = owner;
        this.target = target;

    }
    
    @Override
    public SoldierAction execute(GameStatus gameStatus, float deltaTime) {
        if(target == null) {
            return new SoldierActionIdle(owner, 0);
        }
        double distance = MathHelper.getDistanceBetweenPoints(owner.x, owner.y, target.x, target.y);
        if(distance - owner.attackDistance <= 0) {
            return new SoldierActionAttack(owner);
        }
        
        double angleBetweenPointsInRadians = MathHelper.getAngleBetweenPointsInRadians(owner.x, owner.y, target.x, target.y);
        double cosAngleBetweenPointsInRadians = Math.cos(angleBetweenPointsInRadians);
        double sinAngleBetweenPointsInRadians = Math.sin(angleBetweenPointsInRadians);
        
        owner.x += owner.currentVelocity * cosAngleBetweenPointsInRadians * deltaTime;
//        if(cosAngleBetweenPointsInRadians < 0 && owner.x < target.x) {
//            owner.x = target.x;
//        }
//        if(cosAngleBetweenPointsInRadians > 0 && owner.x > target.x) {
//            owner.x = target.x;
//        }
        
        owner.y += owner.currentVelocity * sinAngleBetweenPointsInRadians * deltaTime;
//        if(sinAngleBetweenPointsInRadians < 0 && owner.x < target.x) {
//            owner.y = target.y;
//        }
//        if(sinAngleBetweenPointsInRadians > 0 && owner.y > target.y) {
//            owner.y = target.y;
//        }        
//        if(owner.x == target.x && owner.y == target.y) {
//            return new SoldierActionIdle(owner, 0);
//        }
        

        
        return new SoldierActionMoveTo(owner, target);
    }
    
}
