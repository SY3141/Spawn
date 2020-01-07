/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isu;

import java.awt.Rectangle;

/**
 *
 * @author Yao
 */
public class Resource {

   // int health;
    int size;
    int type;
    int x;
    int y;
    int reward;
    boolean selected;

    public Resource(int reward,int size, int type, int x, int y) {
        this.reward = reward;
        this.size = size;
       // health = size;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }
    public int getSize(){
        return this.size;
    }

    public int sideLength() {
        return (int) Math.round(Math.sqrt(size));
    }

    public void damage(int damage) {
        if (size >= 0) {
        size -= damage;
        }      
    }

    public Rectangle resourceTarget() {
        return new Rectangle(x, y, sideLength(), sideLength());
    }

    public int reward() {
        return this.reward;

    }
}
