package akiratto.rtslikegame.renderer.object;

import akiratto.rtslikegame.renderer.object.common.FontDraw;
import akiratto.rtslikegame.status.GameStatus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author owner
 */
public class FpsDraw extends Draw {
    
    private final FontDraw fontDraw;

    public FpsDraw() {
        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.size = 32;
        freeTypeFontParameter.color = Color.WHITE;
        this.fontDraw = new FontDraw(Gdx.files.local("font/NotoSansJP-Bold.ttf"),freeTypeFontParameter);
    }
    
    @Override
    public void drawSprite(SpriteBatch spriteBatch, GameStatus gameStatus) {
        fontDraw.draw(spriteBatch, "fps=" + Gdx.graphics.getFramesPerSecond(), 0, 48);
    }

    @Override
    public void drawShape(ShapeRenderer shapeRenderer, GameStatus gameStatus) {
    }

    @Override
    public void dispose() {
        fontDraw.dispose();
    }
    
}
