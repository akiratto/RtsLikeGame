package akiratto.rtslikegame.simulation.object;

import akiratto.rtslikegame.status.GameStatus;

/**
 *
 * @author akira shimada
 */
public interface Simu {
    public void update(GameStatus gameStatus, float delta);
}
