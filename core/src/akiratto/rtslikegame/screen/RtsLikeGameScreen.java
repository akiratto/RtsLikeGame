package akiratto.rtslikegame.screen;

import com.badlogic.gdx.Screen;

/**
 *
 * @author akira shimada
 */
public abstract class RtsLikeGameScreen implements Screen {
    
    public RtsLikeGameScreen()
    {
    }
    
    public abstract void update(float delta);
    public abstract void draw(float delta);
    public abstract boolean isDone();
    
    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }
    
    @Override
    public void show() {
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
}
