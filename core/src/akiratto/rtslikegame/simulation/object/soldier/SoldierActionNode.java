/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package akiratto.rtslikegame.simulation.object.soldier;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author akira shimada
 */
public class SoldierActionNode {
    public SoldierAction action;
    
    public final Array<SoldierActionNode> actionNodes = new Array<>();
}
