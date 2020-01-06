/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isu;

/**
 *
 * @author Yao
 */
public class Resource {

    int health;
    int size;
    int type;
    int x;
    int y;

    public Resource(int size, int type,int x,int y) {
        
        this.size = size;
        health = size;
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
public int getX(){
    return x;
}
public int getY(){
    return y;
}
public int getType(){
    return type;
}
    public int sideLength(){
        return (int) Math.round(Math.sqrt(size));
    }
    public void damage(int damage) {
        health -= damage;
        if (health <= 0) {
            reward();
        }
    }

    public int reward() {
        return this.size;
        /*
        switch (type) {
            case 0:
                //gives rubble
                break;
            case 1:
                //gives wood
                break;
            case 2:
                break;
                //gives life
        }
    */
    }
}
