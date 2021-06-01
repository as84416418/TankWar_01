package com.study.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame {

    MyPanel mp = null;

    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }

    public BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400, 300);
        //窗口JFrame 对象可以监听键盘时间，即可以监听到面板上发生的键盘事件
        this.addKeyListener(mp);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

//面板，可以画出小球
//KeyListener 是键盘监听器，可以监听键盘事件
class MyPanel extends JPanel implements KeyListener {

    //为了让小球可以以哦那个，把他左上角的坐标(x,y)设置成变量值
    int x = 10;
    int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }

    //当有键盘输入的时候，调用该方法
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //当有键盘被按下时，调用该方法
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println((char)e.getKeyCode() + "  被按下了！");
        //根据用户按下的不同键，来处理小球的移动(上下左右)
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x++;
        }


        //让面板重绘，达到小球移动的目的
        repaint();
    }

    //当有键盘被松开时，调用该方法
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
