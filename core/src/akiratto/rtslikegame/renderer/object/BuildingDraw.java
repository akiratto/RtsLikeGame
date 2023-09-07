package akiratto.rtslikegame.renderer.object;

import akiratto.rtslikegame.renderer.object.common.FontDraw;
import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.Building;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author akira shimada
 */
public class BuildingDraw extends Draw {
    
    private final FontDraw fontDraw;

    public BuildingDraw() {
        FileHandle fontFileHandle = Gdx.files.local("font/NotoSansJP-Bold.ttf");
        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.size = 8;
        freeTypeFontParameter.color = Color.WHITE;
        this.fontDraw = new FontDraw(fontFileHandle, freeTypeFontParameter);
    }
    
    @Override
    public void drawSprite(SpriteBatch spriteBatch, GameStatus gameStatus) {
        Array<Building> buildings = gameStatus.buildings;
        for(Building building : buildings) {
            float x = building.x - building.width/2 + 1;
            float y = building.y + building.height/2 - 1;
            fontDraw.draw(spriteBatch, building.name, x+4, y-4);
        }
    }

    @Override
    public void drawShape(ShapeRenderer shapeRenderer, GameStatus gameStatus) {
        Array<Building> buildings = gameStatus.buildings;
        for(Building building : buildings) {
            float x = building.x - building.width/2 + 1;
            float y = building.y - building.height/2 + 1;
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(building.x, building.y, 4);
            shapeRenderer.circle(x, y, 4);
            shapeRenderer.rect(x, y, building.width, building.height);
        }
    }

    @Override
    public void dispose() {
        fontDraw.dispose();
    }
    
}
