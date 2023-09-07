package akiratto.rtslikegame.screen;

import akiratto.rtslikegame.renderer.Renderer;
import akiratto.rtslikegame.simulation.Simulation;
import akiratto.rtslikegame.status.GameStatus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author akira shimada
 */
public class GameLoop extends RtsLikeGameScreen implements InputProcessor {
    private final GameStatus gameStatus;
    private final Simulation simulation;
    private final Renderer   renderer;
    private boolean playBegan;
    
    public GameLoop() {
        super();
        Gdx.input.setInputProcessor(this);
        this.gameStatus = new GameStatus();
        this.simulation = new Simulation();
        this.renderer   = new Renderer();
        this.playBegan  = true;
        
        simulation.initialize(gameStatus);
        renderer.initialize(gameStatus);
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) playBegan = !playBegan;
        if (playBegan) {
            simulation.update(gameStatus, delta);
        }
    }

    @Override
    public void draw(float delta) {
        if(playBegan) {
            renderer.render(gameStatus, simulation, delta);
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void dispose() {
        super.dispose();
        simulation.dispose();
        renderer.dispose();
    }


    Vector3 tp = new Vector3();
    boolean dragging;
    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }
    
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public void resize(int width, int height) {
        renderer.updateViewport(width, height);
    }
    
    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean scrolled(float f, float f1) {
        return false;
    }
}
