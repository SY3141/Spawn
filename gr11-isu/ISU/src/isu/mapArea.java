/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 *
 * @author 348550369
 */
public class mapArea extends javax.swing.JPanel {

    /**
     * Creates new form mapArea
     */
    public mapArea() {
        initComponents();
        setFocusable(true);
        addKeyListener(new mapArea.AL());
        Timer t1 = new Timer(5,new TimerListener());
        t1.start();
        //    KeyListener listener = new KeyAdapter();
        // mapArea map = new mapArea();
        // System.out.println(map.isFocusable());
        
    }
    int keyCode;
    boolean keys[] = new boolean[4];
    public int playerx = 500;
    public int playery = 500;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image player = Toolkit.getDefaultToolkit().getImage("H://Documents//ICS3U_YaoSunny//ISU//src//resources//player.png");
        g.drawImage(player, playerx, playery, this);
        requestFocus();
        g.setColor(Color.MAGENTA);
        g.drawRect(playerx, playery, 20, 20);
  
    }

    public class AL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("release");
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
            System.out.println("Key held");
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
            System.out.println(playerx+" "+ playery);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
