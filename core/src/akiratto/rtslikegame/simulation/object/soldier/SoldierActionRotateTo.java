package akiratto.rtslikegame.simulation.object.soldier;

import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.Soldier;
import akiratto.rtslikegame.util.MathHelper;
import akiratto.rtslikegame.util.RotationDirection;

/**
 *
 * @author akira shimada
 */
public class SoldierActionRotateTo implements SoldierAction {
    
    private final Soldier owner;
    private final Soldier target;
    private final double angleBetweenPointsInRadians;
    private final float  angleBetweenPointsInDegree;
    private final RotationDirection rotationDirection;
    
    public SoldierActionRotateTo(Soldier owner, Soldier target)
    {
        this.owner = owner;
        this.target = target;
        this.angleBetweenPointsInRadians = MathHelper.getAngleBetweenPointsInRadians(owner.x, owner.y, target.x, target.y);
        this.angleBetweenPointsInDegree  = (float)Math.toDegrees(angleBetweenPointsInRadians);
        this.rotationDirection = MathHelper.getFasterTurningDirection(Math.toRadians(owner.degree), angleBetweenPointsInRadians);
    }

    public SoldierActionRotateTo(Soldier owner, Soldier target, double angleBetweenPointsInRadians, float angleBetweenPointsInDegree, RotationDirection rotationDirection) {
        this.owner = owner;
        this.target = target;
        this.angleBetweenPointsInRadians = angleBetweenPointsInRadians;
        this.angleBetweenPointsInDegree = angleBetweenPointsInDegree;
        this.rotationDirection = rotationDirection;
    }
    
    @Override
    public SoldierAction execute(GameStatus gameStatus, float deltaTime) {
        if(target == null) {
            return new SoldierActionIdle(owner, 0);
        }
        if(rotationDirection == RotationDirection.CLOCKWISE) {
            owner.degree -= (float)(deltaTime * owner.currentRotationVelocity);
            owner.degree  = owner.degree < angleBetweenPointsInDegree ? angleBetweenPointsInDegree   : owner.degree;
            owner.degree  = owner.degree < 0                          ? 360 - Math.abs(owner.degree) : owner.degree;
           
        } else if(rotationDirection == RotationDirection.COUNTERCLOCKWISE) {
            owner.degree += (float)(deltaTime * owner.currentRotationVelocity);
            owner.degree  = owner.degree > angleBetweenPointsInDegree ? angleBetweenPointsInDegree   : owner.degree;
            owner.degree  = owner.degree > 360                        ? owner.degree - 360           : owner.degree;
        }
        if(owner.degree == angleBetweenPointsInDegree) {
            return new SoldierActionMoveTo(owner, target);
        }
        return new SoldierActionRotateTo(owner, target, angleBetweenPointsInRadians, angleBetweenPointsInDegree, rotationDirection);
    }
    
    private void rotateSoldier(Soldier soldier, final float angleInDegree, final float limitAngleInDegree) {
        if (angleInDegree != 0) {
            soldier.degree += angleInDegree;
            if(angleInDegree < 0) {
                if(soldier.degree < limitAngleInDegree) {
                    soldier.degree = limitAngleInDegree;
                }
            }
            if(angleInDegree > 0) {
                if(soldier.degree > limitAngleInDegree) {
                    soldier.degree = limitAngleInDegree;
                }
            }
            
            if (soldier.degree < 0) {
                soldier.degree = 360 - Math.abs(soldier.degree);
            }

            if (soldier.degree > 360) {
                soldier.degree = 0 + soldier.degree - 360;
            }
        }
    }
    
}
