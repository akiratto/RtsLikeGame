package akiratto.rtslikegame.renderer;

import akiratto.rtslikegame.renderer.object.BuildingDraw;
import akiratto.rtslikegame.renderer.object.FpsDraw;
import akiratto.rtslikegame.renderer.object.SoldierDraw;
import akiratto.rtslikegame.simulation.Simulation;
import akiratto.rtslikegame.status.GameStatus;
import akiratto.rtslikegame.util.ShapeRendererEx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 *
 * @author akira shimada
 */
public class Renderer implements Disposable {
    private final OrthographicCamera gameCamera;
    private final ExtendViewport gameCameraViewport;
    private final SpriteBatch gameSpriteBatch;
    private final PolygonSpriteBatch gamePolygonSpriteBatch;
    private final ShapeRenderer gameShapeRenderer;
    
    private final OrthographicCamera hudCamera;
    private final ExtendViewport hudCameraViewport;
    private final SpriteBatch hudSpriteBatch; 
    
    private final FpsDraw fpsDraw;
    
    private final SoldierDraw soldierDraw;
    private final BuildingDraw buildingDraw;

    public Renderer() {
        this.gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.gameCameraViewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCamera);
        this.gameSpriteBatch = new SpriteBatch();
        this.gamePolygonSpriteBatch = new PolygonSpriteBatch();
        this.gameShapeRenderer = new ShapeRendererEx();
        
        this.hudCamera  = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.hudCameraViewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), hudCamera);
        this.hudSpriteBatch = new SpriteBatch();
        
        this.fpsDraw = new FpsDraw();

        this.soldierDraw = new SoldierDraw();
        this.buildingDraw = new BuildingDraw();
    }
    
    public void initialize(GameStatus gameStatus)
    {
        soldierDraw.initialize(gameStatus);
    }
    
    public void render (GameStatus gameStatus, Simulation simulation, float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameCamera.update();
        gameSpriteBatch.setProjectionMatrix(gameCamera.combined);
        gameSpriteBatch.begin();
        buildingDraw.drawSprite(gameSpriteBatch, gameStatus);
        soldierDraw.drawSprite(gameSpriteBatch, gameStatus);
        gameSpriteBatch.end();
        
        gamePolygonSpriteBatch.setProjectionMatrix(gameCamera.combined);
        gamePolygonSpriteBatch.begin();
        soldierDraw.drawPolygonSprite(gamePolygonSpriteBatch, gameStatus);
        gamePolygonSpriteBatch.end();
        
        gameShapeRenderer.setProjectionMatrix(gameCamera.combined);
        gameShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        soldierDraw.drawShape(gameShapeRenderer, gameStatus);
        buildingDraw.drawShape(gameShapeRenderer, gameStatus);
        gameShapeRenderer.end();
        
        gameShapeRenderer.setProjectionMatrix(gameCamera.combined);
        gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        soldierDraw.drawShapeFill(gameShapeRenderer, gameStatus);
        gameShapeRenderer.end();
        
        hudSpriteBatch.setProjectionMatrix(hudCamera.combined);
        hudSpriteBatch.begin();
        fpsDraw.drawSprite(hudSpriteBatch, gameStatus);
        hudSpriteBatch.end();
    }
    
    public void updateViewport(int width, int height)
    {
        gameCameraViewport.update(width, height, true);
        hudCameraViewport.update(width, height, true);
    }

    @Override
    public void dispose() {        
        gameSpriteBatch.dispose();
        gamePolygonSpriteBatch.dispose();
        gameShapeRenderer.dispose();
        
        hudSpriteBatch.dispose();
        
        fpsDraw.dispose();
        
        soldierDraw.dispose();
        buildingDraw.dispose();
    }
}
