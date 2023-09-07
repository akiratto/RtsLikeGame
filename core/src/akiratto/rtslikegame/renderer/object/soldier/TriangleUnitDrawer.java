package akiratto.rtslikegame.renderer.object.soldier;

import akiratto.rtslikegame.status.object.Soldier;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author akira shimada
 */
public class TriangleUnitDrawer implements Disposable {
    private final Texture textureSolid;
    private final PolygonSprite polygonSprite;

    public TriangleUnitDrawer(Color unitColor) {
        //参考:https://stackoverflow.com/questions/15733442/drawing-filled-polygon-with-libgdx
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixmap.setColor(unitColor);
        pixmap.fill();
        textureSolid = new Texture(pixmap);
        PolygonRegion polygonRegion = new PolygonRegion(new TextureRegion(textureSolid), 
                new float[] {
                    23,7,  //頂点 0
                    0, 0,  //頂点 1
                    11,7,  //頂点 2
                    0, 15, //頂点 3
                }, new short[] {
                    0,1,2, //頂点0 → 1 → 2
                    0,3,2  //頂点0 → 3 → 2
                });
        polygonSprite = new PolygonSprite(polygonRegion);
        polygonSprite.setOrigin(11, 7);
    }
    
    public void drawPolygonSprite(PolygonSpriteBatch polygonSpriteBatch, Soldier soldier)
    {
        polygonSprite.setX(soldier.x - 11); //頂点2(11,7)が中心になるよう描画位置をずらす
        polygonSprite.setY(soldier.y - 7);
        polygonSprite.setRotation(soldier.degree);
        polygonSprite.draw(polygonSpriteBatch);
    }

    @Override
    public void dispose() {
        textureSolid.dispose();
    }
}
