/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package akiratto.rtslikegame.renderer.object;

import akiratto.rtslikegame.status.GameStatus;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author akira shimada
 */
public abstract class Draw implements Disposable {
    abstract public void drawSprite(SpriteBatch spriteBatch, GameStatus gameStatus);
    abstract public void drawShape(ShapeRenderer shapeRenderer, GameStatus gameStatus);
    abstract public void dispose();
}
