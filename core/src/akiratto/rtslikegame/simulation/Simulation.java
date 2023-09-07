package akiratto.rtslikegame.simulation;

import akiratto.rtslikegame.simulation.object.BuildingSimu;
import akiratto.rtslikegame.simulation.object.SoldierSimu;
import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.Building;
import akiratto.rtslikegame.status.object.Soldier;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

/**
 *
 * @author akira shimada
 */
public class Simulation implements Disposable {
    
    public SoldierSimu soldierSimu;
    public BuildingSimu buildingSimu;

    public Simulation() {
        this.soldierSimu = new SoldierSimu();
        this.buildingSimu = new BuildingSimu();
    }
    
    public void initialize(GameStatus gameStatus)
    {
        initializeSoldier(gameStatus);
        initializeBuilding(gameStatus);
    }
    
    private void initializeSoldier(GameStatus gameStatus)
    {
        ObjectMap<String, Soldier> soldiers = gameStatus.soldiers;
        Soldier a1 = new Soldier();
        a1.name = "a1";
        a1.team = 1;
        a1.x = 100;
        a1.y = 100;
        a1.degree = 45;
        a1.currentVelocity = 100;
        a1.currentRotationVelocity = 100;
        a1.hpGauge.point = 100;
        a1.hpGauge.maxPoint = 100;
        a1.hpGauge.width = 32;
        a1.hpGauge.height = 4;
               
        Soldier a2 = new Soldier();
        a2.name = "a2";
        a2.team = 2;
        a2.x = 140;
        a2.y = 140;
        a2.degree = 225;
        a2.currentVelocity = 100;
        a2.currentRotationVelocity = 200;
        a2.hpGauge.point = 100;
        a2.hpGauge.maxPoint = 100;
        a2.hpGauge.width = 32;
        a2.hpGauge.height = 4;
        
        soldiers.put(a1.name, a1);
        soldiers.put(a2.name, a2);
    }
    
    private void initializeBuilding(GameStatus gameStatus)
    {
        Array<Building> buildings = gameStatus.buildings;
        
        Building castle1 = new Building();
        castle1.name = "castle1";
        castle1.x = 100;
        castle1.y = 300;
        castle1.width = 100;
        castle1.height = 60;
        
        Building castle2  = new Building();
        castle2.name = "castle2";
        castle2.x = 600;
        castle2.y = 300;
        castle2.width = 80;
        castle2.height = 40;
        
        buildings.add(castle1);
        buildings.add(castle2);
    }
       
    public void update(GameStatus gameStatus, float delta)
    {
        soldierSimu.update(gameStatus, delta);
        buildingSimu.update(gameStatus, delta);
    }

    @Override
    public void dispose() {
        
    }
}
