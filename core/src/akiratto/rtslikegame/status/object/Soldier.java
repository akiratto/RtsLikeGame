package akiratto.rtslikegame.status.object;

import akiratto.rtslikegame.status.object.common.Gauge;
import akiratto.rtslikegame.simulation.object.soldier.SoldierAction;
import akiratto.rtslikegame.util.RotationDirection;


/**
 *
 * @author akira shimada
 */
public class Soldier implements Cloneable {
    public String name;
    public int team;
    public float x;
    public float y;
    public float degree;
    public SoldierAction action = null;
    public Soldier target = null;
    public double currentVelocity = 0;
    public double currentRotationVelocity = 0; ///deg/s
    public RotationDirection currentRotationDirection = RotationDirection.CLOCKWISE;
    
    public double searchDistance = 100;
    public double attackDistance = 30;
    
    public final Gauge hpGauge = new Gauge();
    
    public float fireAngleInDegree = 0;
    public float fireAngleRangeInDegree = 30;
    
    public boolean dead = false;

    @Override
    public Soldier clone()
    {
        try {
            Soldier soldierClone = (Soldier)super.clone();
            soldierClone.action = null;
            return soldierClone;
        } catch(CloneNotSupportedException ex) {
            throw new InternalError(ex);
        }
    }
    
    
}
