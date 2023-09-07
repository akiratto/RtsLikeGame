package akiratto.rtslikegame;

import akiratto.rtslikegame.screen.GameLoop;
import akiratto.rtslikegame.screen.RtsLikeGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class RtsLikeGame extends Game {

    
    @Override
    public void create() {
        setScreen(new GameLoop());
    }
    
    @Override
    public void render() {
        RtsLikeGameScreen currentScreen = getScreen();
        
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public RtsLikeGameScreen getScreen() {
        return (RtsLikeGameScreen)super.getScreen();
    }

    @Override
    public void resize(int width, int height) {
        RtsLikeGameScreen screen = getScreen();
        if(screen instanceof GameLoop) {
            GameLoop gameLoop = (GameLoop)screen;
            gameLoop.resize(width, height);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        getScreen().dispose();
    }
}
