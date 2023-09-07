package akiratto.rtslikegame.renderer.object.common;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author owner
 */
public class FontDraw implements Disposable {
    private final FreeTypeFontGenerator fontGenerator;
    private final BitmapFont bitmapFont;

    public FontDraw(FileHandle fontFile, FreeTypeFontParameter freeTypeFontParameter) {
        this.fontGenerator = new FreeTypeFontGenerator(fontFile);
        this.bitmapFont = fontGenerator.generateFont(freeTypeFontParameter);
    }
    
    public void draw(SpriteBatch spriteBatch, String text, float x, float y)
    {
        bitmapFont.draw(spriteBatch, text, x, y);
    }
    
    
    
    @Override
    public void dispose() {
        bitmapFont.dispose();
        fontGenerator.dispose();
    }
    
}
