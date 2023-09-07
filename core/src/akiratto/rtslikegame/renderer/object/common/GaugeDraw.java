package akiratto.rtslikegame.renderer.object.common;

import akiratto.rtslikegame.status.object.common.Gauge;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

/**
 *
 * @author akira shimada
 */
public class GaugeDraw implements Disposable {
    private final Array<Gauge> gauges;
    private final ObjectMap<Gauge, Pixmap>  gaugePixmaps;
    private final ObjectMap<Gauge, Texture> gaugeTextures;
    private final FontDraw fontDraw;

    public GaugeDraw() {
        this.gauges = new Array<>();
        this.gaugePixmaps = new ObjectMap<>();
        this.gaugeTextures = new ObjectMap<>();
        
        FileHandle fontFileHandle = Gdx.files.local("font/NotoSansJP-Bold.ttf");
        FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontParameter();
        freeTypeFontParameter.size = 8;
        freeTypeFontParameter.color = Color.WHITE;
        this.fontDraw = new FontDraw(fontFileHandle, freeTypeFontParameter);
    }
    
    public void addGauge(Gauge gauge)
    {
        if(gauges.contains(gauge, true)) {
            throw new IllegalArgumentException("This gauge already added!");
        }
        Pixmap  gaugePixmap  = new Pixmap(gauge.width, gauge.height, Pixmap.Format.RGBA8888);
        Texture gaugeTexture = new Texture(gaugePixmap);
        renderGaugeTexture(gauge, gaugePixmap, gaugeTexture);
        
        gauges.add(gauge);
        gaugePixmaps.put(gauge, gaugePixmap);
        gaugeTextures.put(gauge, gaugeTexture);
    }
    
    public void removeGauge(Gauge gauge)
    {
        if(!gauges.contains(gauge, true)) {
            throw new IllegalArgumentException("This gauge not exist!");
        }
        Pixmap  gaugePixmap  = gaugePixmaps.get(gauge);
        Texture gaugeTexture = gaugeTextures.get(gauge);
        
        gauges.removeValue(gauge, true);
        gaugePixmaps.remove(gauge);
        gaugeTextures.remove(gauge);
        gaugePixmap.dispose();
        gaugeTexture.dispose();        
    }
    
    public void drawGauges(SpriteBatch spriteBatch) {
        deleteDeadGauges();
        for(Entry<Gauge, Texture> gaugeTextureEntry : gaugeTextures.entries()) {
            Gauge gauge = gaugeTextureEntry.key;
            if(gauge.updateGague) {
                updateGaugeTexutre(gauge);
                gauge.updateGague = false;
            }
            Texture gaugeTexture = gaugeTextureEntry.value;
            spriteBatch.draw(gaugeTexture, gauge.x, gauge.y);
            
            fontDraw.draw(spriteBatch, Integer.toString(gauge.point), gauge.x, gauge.y + 16);
        }
    }

    @Override
    public void dispose() {
        for(Entry<Gauge, Pixmap>  gaugeEntry : gaugePixmaps.entries()) {
            Pixmap gaugePixmap = gaugeEntry.value;
            gaugePixmap.dispose();
        }
        for(Entry<Gauge, Texture> gaugeEntry : gaugeTextures.entries()) {
            Texture gaugeTexture = gaugeEntry.value;
            gaugeTexture.dispose();
        }
        gaugePixmaps.clear();
        gaugeTextures.clear();
        gauges.clear();
        fontDraw.dispose();
    }
    
    public void updateGaugeTexutre(Gauge gauge)
    {
        if(!gauges.contains(gauge, true)) {
            throw new IllegalArgumentException("This gauge not exist!");
        }
        Pixmap  gaugePixmap  = gaugePixmaps.get(gauge);
        Texture gaugeTexture = gaugeTextures.get(gauge);
        renderGaugeTexture(gauge, gaugePixmap, gaugeTexture);
    }
    
    private void deleteDeadGauges()
    {
        Array<Gauge> deadGauges = new Array<>();
        for(Gauge gauge : gauges) {
            if(gauge.dead) {
                Pixmap gaugePixmap = gaugePixmaps.get(gauge);
                gaugePixmap.dispose();
                gaugePixmaps.remove(gauge);
                
                Texture gaugeTexture = gaugeTextures.get(gauge);
                gaugeTexture.dispose();
                gaugeTextures.remove(gauge);
                
                deadGauges.add(gauge);
            }
        }
        gauges.removeAll(deadGauges, true);        
    }
    
    private void renderGaugeTexture(Gauge gauge, Pixmap gaugePixmap, Texture gaugeTexture)
    {
        gaugePixmap.setColor(Color.BLACK);
        gaugePixmap.fill();
        gaugePixmap.setColor(Color.YELLOW);
        gaugePixmap.fillRectangle(0, 0, Math.round(gauge.width * (float)gauge.point/gauge.maxPoint), gauge.height);
        gaugePixmap.setColor(Color.RED);
        gaugePixmap.drawRectangle(0, 0, gauge.width, gauge.height);
        gaugeTexture.draw(gaugePixmap, 0, 0);
    }
}
