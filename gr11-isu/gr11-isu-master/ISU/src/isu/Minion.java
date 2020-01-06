/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isu;

import java.awt.Rectangle;

/**
 *
 * @author 348550369
 */
public class Minion {

    int health;
    int damage;
    double minionX;
    double minionY;
    int minionType;
    Rectangle bounds;
    boolean targetReached = false;

    public Minion(int type, int health, int damage, double minionX, double minionY) {
        this.health = health;
        this.damage = damage;
        this.minionX = minionX;
        this.minionY = minionY;
        bounds = new Rectangle((int)minionX,(int) minionY, 10, 10);
        // add different sizes for each type

    }

    public int getType() {
        return this.minionType;
    }

    public int getX() {
        return (int)this.minionX;
    }

    public void setX(double change) {
        this.minionX += change;
        bounds = new Rectangle((int)minionX, (int)minionY, 10, 10);
    }

    public int getY() {
        return (int)this.minionY;
    }

    public void setY(double change) {
        this.minionY += change;
        bounds = new Rectangle((int)minionX, (int)minionY, 10, 10);
    }

    public boolean isDead() {
        if (this.health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void target(int x, int y) {
        double x_diff = x - this.minionX;
        double y_diff = y - this.minionY;
        double distance = Math.sqrt(Math.pow(x_diff, 2) + Math.pow(y_diff, 2));
        if (distance>=5){
        this.minionX += (x_diff / distance);
        this.minionY += (y_diff / distance);  
        bounds = new Rectangle((int)minionX, (int)minionY, 10, 10);
        }  
    }


    public Rectangle getBounds() {
        //add a switch case for different sizes for each minion type
        return bounds;
    }

}
