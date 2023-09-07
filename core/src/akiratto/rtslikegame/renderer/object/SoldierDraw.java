package akiratto.rtslikegame.renderer.object;

import akiratto.rtslikegame.renderer.object.common.GaugeDraw;
import akiratto.rtslikegame.renderer.object.soldier.TriangleUnitDrawer;
import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.status.object.common.Gauge;
import akiratto.rtslikegame.status.object.Soldier;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

/**
 *
 * @author akira shimada
 */
public class SoldierDraw extends Draw { 
    private TriangleUnitDrawer redUnitDrawer;
    private TriangleUnitDrawer greenUnitDrawer;
    private GaugeDraw gaugeDraw;

    public SoldierDraw() {
        this.redUnitDrawer = new TriangleUnitDrawer(Color.RED);
        this.greenUnitDrawer = new TriangleUnitDrawer(Color.GREEN);
        this.gaugeDraw = new GaugeDraw();
    }
    
    public void initialize(GameStatus gameStatus)
    {
        ObjectMap<String, Soldier> soldiers = gameStatus.soldiers;
        for(Entry<String, Soldier> soldierEntry : soldiers) {
            Soldier soldier = soldierEntry.value;
            Gauge   gauge   = soldier.hpGauge;
            gaugeDraw.addGauge(gauge);
        }
    }
    
    @Override
    public void drawSprite(SpriteBatch spriteBatch, GameStatus gameStatus) {
        gaugeDraw.drawGauges(spriteBatch);
    }
    
    public void drawPolygonSprite(PolygonSpriteBatch polygonSpriteBatch, GameStatus gameStatus)
    {
        ObjectMap<String, Soldier> soldiers = gameStatus.soldiers;
        for(Entry<String, Soldier> soldierEntry : soldiers.entries()) {
            Soldier soldier = soldierEntry.value;
            switch(soldier.team) {
                case 1:
                    redUnitDrawer.drawPolygonSprite(polygonSpriteBatch, soldier);
                    break;
                case 2:
                    greenUnitDrawer.drawPolygonSprite(polygonSpriteBatch, soldier);
                    break;
                default:
                    redUnitDrawer.drawPolygonSprite(polygonSpriteBatch, soldier);
                    break;
            }
        }
    }

    @Override
    public void drawShape(ShapeRenderer shapeRenderer, GameStatus gameStatus) {
        ObjectMap<String, Soldier> soldiers = gameStatus.soldiers;
        for(Entry<String, Soldier> soldierEntry : soldiers.entries()) {
            Soldier soldier = soldierEntry.value;
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.circle(soldier.x, soldier.y, 1);
            shapeRenderer.circle(soldier.x, soldier.y, (float)soldier.searchDistance);
            
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(soldier.x, soldier.y, 1);
            shapeRenderer.circle(soldier.x, soldier.y, (float)soldier.attackDistance);
            
            shapeRenderer.setColor(Color.YELLOW);
            float start = soldier.degree + soldier.fireAngleInDegree - soldier.fireAngleRangeInDegree/2;
            shapeRenderer.arc(soldier.x, soldier.y, (float)soldier.attackDistance,  start, soldier.fireAngleRangeInDegree);
        }
    }
    
    public void drawShapeFill(ShapeRenderer shapeRenderer, GameStatus gameStatus) {
    }

    @Override
    public void dispose() {
        redUnitDrawer.dispose();
        greenUnitDrawer.dispose();
        gaugeDraw.dispose();
    }
    
}
