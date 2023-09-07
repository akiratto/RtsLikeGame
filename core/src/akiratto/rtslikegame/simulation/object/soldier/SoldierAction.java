package akiratto.rtslikegame.simulation.object.soldier;

import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.Soldier;

/**
 *
 * @author akira shimada
 */
public interface SoldierAction {
    SoldierAction execute(GameStatus gameStatus, float deltaTime);
}
