/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isu;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

/**
 *
 * @author 348550369
 */
public class mapArea extends javax.swing.JPanel {

    /**
     * Creates new form mapArea
     */
    int minionNum = 5;  // declares number of minions
    Minion[] minionList = new Minion[100];
    int resourceNum = 20;
    Resource[] resourceList = new Resource[resourceNum];
    int keyCode;
    boolean keys[] = new boolean[4];
    public int playerx = 500;
    public int playery = 500;
    Rectangle playerBound = new Rectangle(playerx, playery, 25, 25);
    int targetX;
    int targetY;
    boolean keepTarget;

    public mapArea() {

        initComponents();
        setFocusable(true);
        addKeyListener(new mapArea.AL());
        Timer t1 = new Timer(5, new TimerListener());
        t1.start();
        //    KeyListener listener = new KeyAdapter();
        // mapArea map = new mapArea();
        // System.out.println(map.isFocusable());
        for (int i = 0; i < 5; i++) {    // initializes 5 minions
            minionList[i] = new Minion(1, 100, 5, playerx + i * 30, playery); // initializes a standard minion at the player's position
        }
        for (int i = 0; i < resourceNum; i++) {    // initializes 5 minions
            int resourceSize = (int) (Math.random() * 201) + 300; // generates a size value between 200 and 500
            int resourceType = (int) (Math.random() * 3);
            int resourceX = (int) (Math.random() * 900);  // generates x coordinate between 0 and 900
            int resourceY = (int) (Math.random() * 500) + 200; // generates y coordinate between 200 and 800
            resourceList[i] = new Resource(resourceSize, resourceType, resourceX, resourceY); // initializes a standard minion at the player's position
        }
        for (int i = 5; i < minionList.length; i++) {    // initializes placeholders for minions
            // minionList[i] = new Minion(1, 0, 0, 0, 0); // initializes placholders off screen
            minionList[i] = null; // initializes null placeholders in array that will be filled with more minions

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        requestFocus();
        for (int i = 0; i < minionNum; i++) {
            g.drawRect(minionList[i].getX(), minionList[i].getY(), 10, 10);
            // REPLACE WITH AN IMAGE
        }
        for (int i = 0; i < resourceNum; i++) {
            switch (resourceList[i].getType()) { // switch case for creating resource types
                case 0:  // resource type that drops rubble
                    g.setColor(Color.DARK_GRAY);
                    break;
                case 1: // resource type that drops wood
                    g.setColor(Color.GREEN);
                    break;
                case 2:  // resource type that drops "life"
                    g.setColor(Color.RED);
                    break;
            }
            g.fillRect(resourceList[i].getX(), resourceList[i].getY(), resourceList[i].sideLength(), resourceList[i].sideLength());
            // REPLACE WITH AN IMAGE
        }
        Image player = Toolkit.getDefaultToolkit().getImage("H://Documents//ICS3U_YaoSunny//ISU//src//resources//player.png");
        g.drawImage(player, playerx, playery, this);
        g.setColor(Color.BLUE);
        g.drawRect(playerx, playery, 25, 25);
        // g.setColor(Color.MAGENTA);
        //   g.drawRect(playerx, playery, 20, 20);
        this.enableEvents(AWTEvent.MOUSE_EVENT_MASK
                | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK
                | AWTEvent.FOCUS_EVENT_MASK | AWTEvent.COMPONENT_EVENT_MASK
                | AWTEvent.WINDOW_EVENT_MASK);

    }

    public void spawn() {
        if(minionNum < 100){
        minionList[minionNum] = new Minion(1, 100, 5, playerx, playery);  // creates a new minion object on top of the player
        minionNum++;   // updates the number of minions in existence
        }
    }

    public class AL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            // System.out.println("release");
            keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_A:
                    keys[0] = false;
                    break;
                case KeyEvent.VK_D:
                    keys[1] = false;
                    break;
                case KeyEvent.VK_W:
                    keys[2] = false;
                    break;
                case KeyEvent.VK_S:
                    keys[3] = false;
                    break;
                default:
                    e.consume();
                    System.out.println(keyCode);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //   System.out.println("Key held");
            keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_A:
                    keys[0] = true;
                    break;
                case KeyEvent.VK_D:
                    keys[1] = true;
                    break;
                case KeyEvent.VK_W:
                    keys[2] = true;
                    break;
                case KeyEvent.VK_S:
                    keys[3] = true;
                    break;
                case 32:       // detects space bar click
                    spawn();  // meat n' taters of this game, spawns a new minion
                default:
                    e.consume();
                    System.out.println(keyCode);
            }
        }

        public void keyTyped(KeyEvent e) {
            e.consume();
        }
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            playerBound = new Rectangle(playerx, playery, 25, 25); // updates player collision box with new coordinate values
            for (int i = 0; i < minionNum; i++) {
                //  if (keepTarget){  // checks if the minions are intended to follow
                minionList[i].target(targetX, targetY);  // targets where the mouse clicks
                //  }
                if (minionList[i].getBounds().intersects(playerBound)) {
                    if (minionList[i].getX() >= playerx) {
                        minionList[i].setX(2);
                    } else {
                        minionList[i].setX(-2);
                    }
                    if (minionList[i].getY() >= playery) {
                        minionList[i].setY(2);
                    } else {
                        minionList[i].setY(-2);
                    }
                }
                for (int j = 0; j < minionNum; j++) {  // checks collisions with other minions
                    if (j != i) {  // stops every minion from colliding with itself
                        if (minionList[i].getBounds().intersects(minionList[j].getBounds())) {
                            if (minionList[i].getX() > minionList[j].getX()) {
                                minionList[j].setX(-1);
                            } else {
                                minionList[j].setX(1);
                            }
                            if (minionList[i].getY() > minionList[j].getY()) {
                                minionList[j].setY(-1);
                            } else {
                                minionList[j].setY(1);
                            }
                        }
            
                    }
                }
                 for (int j = 0; j < resourceList.length; j++) {
                         //   if((minionList[i].getX-resource)){
                            
                       // }
            }
            if (keys[0]) {
                playerx -= 1;
            }
            if (keys[1]) {
                playerx += 1;
            }
            if (keys[2]) {
                playery -= 1;
            }
            if (keys[3]) {
                playery += 1;
            }
            repaint();
        }
    }

    public void processMouseEvent(MouseEvent e) {
        String type = null;
        switch (e.getID()) {
            case MouseEvent.MOUSE_PRESSED:
                type = "MOUSE_PRESSED";
                System.out.println(e.getX() + " " + e.getY());// (x,y) IN THE PANEL
                keepTarget = true; // minions follow when targeting is true
                targetX = e.getX(); // sets target coordinates for minions to follow
                targetY = e.getY();

                break;
            case MouseEvent.MOUSE_RELEASED:
                type = "MOUSE_RELEASED";
                keepTarget = false; // stops the minions from targeting when mouse is released
                break;
            /*
             case MouseEvent.MOUSE_CLICKED:
             type = "MOUSE_CLICKED";
             break;
             case MouseEvent.MOUSE_ENTERED:
             type = "MOUSE_ENTERED";
             break;
             case MouseEvent.MOUSE_EXITED:
             type = "MOUSE_EXITED";
             break;
             */
        }
   //     System.out.println(type);

        // When the mouse enters the component, request keyboard focus so
        // we can receive and respond to keyboard events
        if (e.getID() == MouseEvent.MOUSE_ENTERED) {
            requestFocus();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        /* PointerInfo a = MouseInfo.getPointerInfo();
         Point b = a.getLocation();
         targetX = (int) b.getX();
         targetY = (int) b.getY();
         System.out.println(" clicked at: x = " + targetX + " y = " + targetY);
         */
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
