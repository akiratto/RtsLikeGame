package akiratto.rtslikegame.status;

import akiratto.rtslikegame.status.object.Building;
import akiratto.rtslikegame.status.object.Soldier;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

/**
 *
 * @author akira shimada
 */
public class GameStatus {

    public GameStatus() {
        this.soldiers = new ObjectMap<>();
        this.buildings = new Array<>();
    }    
    
    public ObjectMap<String, Soldier> soldiers;
    public Array<Building> buildings;
}
